package com.ice.shamim.programmingguide.userAuth.Codeforces_Connect;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ice.shamim.programmingguide.Menu.MenuChoice;
import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.StatusDetails.ApiForUserStatusDetails;
import com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.StatusDetails.UserSubmissionDetails;
import com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.UserDetails.ApiForUserDetails;
import com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.UserDetails.Codeforces;
import com.ice.shamim.programmingguide.userAuth.Uva_Connect.UVa_Connect;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Codeforces_Connect extends AppCompatActivity implements View.OnClickListener{

    EditText cf_handle;
    AppCompatButton CfIdSubmit;
    RelativeLayout nextPage;
    String handle_name;
    Dialog verifiedDialog;
    ConstraintLayout mConstrainLayout;
    int check_field = 0;
    Snackbar snackbar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeforces);

        cf_handle = findViewById(R.id.CfHandleEditText);
        CfIdSubmit = findViewById(R.id.submitCfId);
        nextPage = findViewById(R.id.nextCodeforces);
        mConstrainLayout = findViewById(R.id.Codeforces_page);

        verifiedDialog = new Dialog(Codeforces_Connect.this);


        nextPage.setVisibility(View.INVISIBLE);
        nextPage.setOnClickListener(this);
        CfIdSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.nextCodeforces){

            startActivity(new Intent(this,MenuChoice.class));
        }

        if(view.getId() == R.id.submitCfId){

             ParseingUserDetails();
        }


    }





    ///UserDetails///////////////////////////
    private void ParseingUserDetails() {


        progressDialog = new ProgressDialog(Codeforces_Connect.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Connecting ...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        handle_name = cf_handle.getText().toString();



        if(handle_name.isEmpty()){
            check_field = 0;
            snackbar();
            return;
        }

        String first_half="user.info?handles="+handle_name;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiForUserDetails.BASE_URL_Submission_details)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiForUserDetails api = retrofit.create(ApiForUserDetails.class);



        Call<Codeforces> call = api.getValue(first_half);

        call.enqueue(new Callback<Codeforces>() {
            @Override
            public void onResponse(Call<Codeforces> call, Response<Codeforces> response) {



                try{
                    Codeforces codeforces = response.body();
                    String data = codeforces.getStatus().toString();
                    com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.UserDetails.Result[] arr = codeforces.getResult();

                    //Toast.makeText(Codeforces_Connect.this, "Done", Toast.LENGTH_SHORT).show();

                    ParsingUserSubmissionDetails();


                }catch (Exception e){

                    //Toast.makeText(Codeforces_Connect.this, e.toString(), Toast.LENGTH_SHORT).show();
                        check_field = 1;
                        snackbar();
                        return;

                }



            }

            @Override
            public void onFailure(Call<Codeforces> call, Throwable t) {

                Toast.makeText(Codeforces_Connect.this, t.toString(), Toast.LENGTH_SHORT).show();

            }

        });


    }


    ////////////////////////////   *  //////////////////////



    //UserSubmission Details/////////////////////////////////////



    private void ParsingUserSubmissionDetails() {


        handle_name = cf_handle.getText().toString();

        String method_With_handle = "user.status?handle="+handle_name;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiForUserStatusDetails.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiForUserStatusDetails api = retrofit.create(ApiForUserStatusDetails.class);

        Call<UserSubmissionDetails> call = api.getValue(method_With_handle);

        call.enqueue(new Callback<UserSubmissionDetails>() {
            @Override
            public void onResponse(Call<UserSubmissionDetails> call, Response<UserSubmissionDetails> response) {


                try{

                    UserSubmissionDetails userSubmissionDetails = response.body();
                    String data = userSubmissionDetails.getStatus().toString();
                    com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.StatusDetails.Result[] result = userSubmissionDetails.getResult();
                    Set<String> solve_Cf = new HashSet<>();

                    for(int i=0; i<result.length; i++) {

                        String ContestId = result[i].getProblem().getContestId();
                        String problemIndex = result[i].getProblem().getIndex();
                        String verdict = result[i].getVerdict();

                        String problemNumber = ContestId + problemIndex;

                        if(verdict.equals("OK")){

                            solve_Cf.add(problemNumber);  ///Including GYM solved Also
                        }


                    }



                    //Showing Success Dialog
                    progressDialog.dismiss();
                    ShowVerificationDialog();


                }catch (Exception e){

                   // Toast.makeText(Codeforces_Connect.this, , Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<UserSubmissionDetails> call, Throwable t) {

                Toast.makeText(Codeforces_Connect.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    /////////////////////////////////////// * //////////////////////////////



    public void ShowVerificationDialog() {

        verifiedDialog.setContentView(R.layout.custom_dialog_verification);
        TextView vfText = verifiedDialog.findViewById(R.id.verifiedText);
        ImageView vfDone = verifiedDialog.findViewById(R.id.checkmark);
        Button Donebtn = verifiedDialog.findViewById(R.id.btnDone);


        vfText.setText("Codeforces is Connected.");
        verifiedDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        verifiedDialog.setCanceledOnTouchOutside(false);
        verifiedDialog.show();

        Donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifiedDialog.dismiss();
                startActivity(new Intent(Codeforces_Connect.this, MenuChoice.class));
                finish();
            }
        });


    }


    private void snackbar() {

        progressDialog.dismiss();
        if(check_field == 0) {
            snackbar = Snackbar.make(mConstrainLayout, "All fields are mandatory.", Snackbar.LENGTH_LONG);
        }
        else if(check_field == 1) {
            snackbar = Snackbar.make(mConstrainLayout, "Invalid Username !!!.", Snackbar.LENGTH_LONG);
        }


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(this.getResources().getColor(R.color.GoogleColor));

        snackbar.show();
    }


}
