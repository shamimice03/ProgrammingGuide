package com.ice.shamim.programmingguide.userAuth.Uva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.Codeforces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UVa extends AppCompatActivity implements View.OnClickListener{

    EditText UVaUserName;
    AppCompatButton UVaIdSubmit;
    RelativeLayout nextPage;
    String User_ID;
    private List<SubmissionDetailsModelClass> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uva);

        UVaUserName = findViewById(R.id.UVaUserNameEditText);
        UVaIdSubmit = findViewById(R.id.submitUvaId);
        nextPage = findViewById(R.id.nextUVa);

        nextPage.setOnClickListener(this);
        UVaIdSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.nextUVa){
            startActivity(new Intent(this, Codeforces.class));
        }
        if(view.getId() == R.id.submitUvaId){

           ParsingUserNameToUserID();

        }

    }



    private void ParsingUserNameToUserID() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiForUserID.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String uname = UVaUserName.getText().toString().trim();

        ApiForUserID api;
        api = retrofit.create(ApiForUserID.class);
        Call<Integer> call = (Call<Integer>) api.getValue(uname);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                User_ID = response.body().toString();

                if(User_ID.equals("0")){
                    UVaUserName.setText("Invalid Username !!!");
                }
                else UVaUserName.setText("User ID : "+response.body().toString());


                ParseingSubmissionDetails();

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                Toast.makeText(UVa.this, t.toString(), Toast.LENGTH_LONG).show();
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
                    //Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();

                    String verdict = data[i][2].toString();

                    if(verdict.equals("90")){

                        Accepted_problems.add(data[i][1]);
                    }

                }

                Toast.makeText(UVa.this, "Accepted :"+Accepted_problems.size(), Toast.LENGTH_SHORT).show();

                UVaUserName.setText( "Total Submission :" + String.valueOf(data.length) +
                        "\n Total Accepted : "+String.valueOf(Accepted_problems.size()));


            }

            @Override
            public void onFailure(Call<SubmissionDetailsModelClass> call, Throwable t) {

            }
        });


    }

}
