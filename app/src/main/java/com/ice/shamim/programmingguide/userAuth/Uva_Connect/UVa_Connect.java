package com.ice.shamim.programmingguide.userAuth.Uva_Connect;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.Codeforces_Connect;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UVa_Connect extends AppCompatActivity implements View.OnClickListener{

    EditText UVaUserName;
    AppCompatButton UVaIdSubmit;
    RelativeLayout nextPage;
    String User_ID;
    Dialog verifiedDialog;
    ConstraintLayout mConstrainLayout;
    int check_field = 0;
    Snackbar snackbar;
    ProgressDialog progressDialog;
    private List<SubmissionDetailsModelClass> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uva);

        UVaUserName = findViewById(R.id.UVaUserNameEditText);
        UVaIdSubmit = findViewById(R.id.submitUvaId);
        nextPage = findViewById(R.id.nextUVa);
        mConstrainLayout = findViewById(R.id.uvaPage);

        verifiedDialog = new Dialog(UVa_Connect.this);

        nextPage.setVisibility(View.INVISIBLE);

        nextPage.setOnClickListener(this);
        UVaIdSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.nextUVa){
            startActivity(new Intent(this, Codeforces_Connect.class));
        }
        if(view.getId() == R.id.submitUvaId){

           ParsingUserNameToUserID();

        }

    }



    private void ParsingUserNameToUserID() {

        progressDialog = new ProgressDialog(UVa_Connect.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Connecting ...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiForUserID.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String uname = UVaUserName.getText().toString().trim();

        if(uname.isEmpty()){
            check_field = 0;
            snackbar();
            return;
        }

        ApiForUserID api;
        api = retrofit.create(ApiForUserID.class);
        Call<Integer> call = (Call<Integer>) api.getValue(uname);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                User_ID = response.body().toString();

                if(User_ID.equals("0")){
                    check_field = 1;
                    snackbar();
                    return;
                }
                else {
                    //Successful
                    ParseingSubmissionDetails();
                }


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                Toast.makeText(UVa_Connect.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });


    }




    private void ParseingSubmissionDetails() {

        listItems = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiForSubmissionDetails.BASE_URL_Submission_details)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiForSubmissionDetails api = retrofit.create(ApiForSubmissionDetails.class);



        Call<SubmissionDetailsModelClass> call = api.getValue(User_ID);

        call.enqueue(new Callback<SubmissionDetailsModelClass>() {
            @Override
            public void onResponse(Call<SubmissionDetailsModelClass> call, Response<SubmissionDetailsModelClass> response) {


                SubmissionDetailsModelClass submissionDetails = response.body();

                Set<String> Accepted_problems = new HashSet<>();

                String[][] data = submissionDetails.getSubs();

                for(int i=0; i<data.length; i++){

                    Log.d("Problem Num : ",data[i][1].toString());
                    Log.d("Verdict : ",data[i][2].toString());
                    Log.d("submission : ", String.valueOf(data.length));

                    String verdict = data[i][2].toString();

                    if(verdict.equals("90")){

                        Accepted_problems.add(data[i][1]);
                    }

                }

               // Toast.makeText(UVa_Connect.this, "Accepted :"+Accepted_problems.size(), Toast.LENGTH_SHORT).show();

/*
                UVaUserName.setText( "Total Submission :" + String.valueOf(data.length) +
                        "\n Total Accepted : "+String.valueOf(Accepted_problems.size()));
*/



                //Showing Success Dialog
                progressDialog.dismiss();
                ShowVerificationDialog();

            }

            @Override
            public void onFailure(Call<SubmissionDetailsModelClass> call, Throwable t) {

            }
        });


    }




    public void ShowVerificationDialog() {

        verifiedDialog.setContentView(R.layout.custom_dialog_verification);
        TextView vfText = verifiedDialog.findViewById(R.id.verifiedText);
        ImageView vfDone = verifiedDialog.findViewById(R.id.checkmark);
        Button Donebtn = verifiedDialog.findViewById(R.id.btnDone);


        vfText.setText("UVa is Connected.");
        verifiedDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        verifiedDialog.setCanceledOnTouchOutside(false);
        verifiedDialog.show();

        Donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifiedDialog.dismiss();
                startActivity(new Intent(UVa_Connect.this, Codeforces_Connect.class));
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
