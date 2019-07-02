package com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.APIs.EventTestApi;
import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventMenuItem;
import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventGamePlay extends AppCompatActivity implements View.OnClickListener {


    TextView CountDownText;
    int numtest;
    TextView Question,Option_A,Option_B,Option_C,Option_D,Marks;
    AppCompatCheckBox OptionA_CheckBox,OptionB_CheckBox,OptionC_CheckBox,OptionD_CheckBox,CheckBox;
    AppCompatButton submitAnsBtn;
    LinearLayout linearLayoutOPtionA,linearLayoutOPtionB,linearLayoutOPtionC,linearLayoutOPtionD;

    ProgressDialog progressDialog;
    private Result[] TestData;
    private String deadline;
    private String round;
    private String lasthalf;
    private int TotalScore = 0;
    private  int CurrentScore = 0;


    public int SizeOfTestData,CurrentTestNumber;
    Dialog CorrectDialog,DoneDialog;
    private CountDownTimer mDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_game_play);

        final Activity activity = EventGamePlay.this;

        CountDownText = findViewById(R.id.countDown);

        /*Initialization Of Dialog*/
        CorrectDialog = new Dialog(this);

        /*Layer*/
        linearLayoutOPtionA = (LinearLayout)findViewById(R.id.optionA_LayoutGame);
        linearLayoutOPtionB= (LinearLayout) findViewById(R.id.optionB_LayoutGame);
        linearLayoutOPtionC= (LinearLayout) findViewById(R.id.optionC_LayoutGame);
        linearLayoutOPtionD= (LinearLayout) findViewById(R.id.optionD_LayoutGame);


        /*TextView Initialization*/
        Question = (TextView) findViewById(R.id.questionGame);
        Option_A = (TextView) findViewById(R.id.optionA_textViewGame);
        Option_B = (TextView) findViewById(R.id.optionB_textViewGame);
        Option_C = (TextView) findViewById(R.id.optionC_textViewGame);
        Option_D = (TextView) findViewById(R.id.optionD_textViewGame);
        Marks    =  (TextView) findViewById(R.id.marksGame);

        /*CheckBox Initialization*/

        OptionA_CheckBox = (AppCompatCheckBox) findViewById(R.id.optionA_checkBoxGame);
        OptionB_CheckBox = (AppCompatCheckBox) findViewById(R.id.optionB_checkBoxGame);
        OptionC_CheckBox = (AppCompatCheckBox) findViewById(R.id.optionC_checkBoxGame);
        OptionD_CheckBox = (AppCompatCheckBox) findViewById(R.id.optionD_checkBoxGame);

        /*Submit Ans*/
        submitAnsBtn = (AppCompatButton) findViewById(R.id.submitAnsBtnGame);


        /*Taking input from radio button*/
        linearLayoutOPtionA.setOnClickListener(this);
        linearLayoutOPtionB.setOnClickListener(this);
        linearLayoutOPtionC.setOnClickListener(this);
        linearLayoutOPtionD.setOnClickListener(this);
        submitAnsBtn.setOnClickListener(this);

        numtest = getIntent().getExtras().getInt("testnumber");
       // Toast.makeText(activity, String.valueOf(numtest), Toast.LENGTH_SHORT).show();




        /*Changing the status bar and navigation bar color using Gradient*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(ContextCompat.getDrawable(EventGamePlay.this,
                    R.drawable.gradient_test_main));
        }


        ParsingDetailsOfData();


    }

    /*Parsing data from Rest Api using Retrofit*/
    private void ParsingDetailsOfData() {

        progressDialog = new ProgressDialog(EventGamePlay.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Getting ready...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();


        if (numtest == 1) {
            /*QuizTest*/
            lasthalf = "l53gx";
        }
        else if(numtest==2){
            /*CodingTest*/
            lasthalf = "fs70x";
        }
        else if(numtest==3){
            /*InterviewTest*/
            lasthalf = "nitw1";;
        }
        else if(numtest==4){
            /*RandomTest*/
            lasthalf = "u4qpd";
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EventTestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EventTestApi api = retrofit.create(EventTestApi.class);
        Call<EventTestModel> call = api.getValue(lasthalf);

        call.enqueue(new Callback<EventTestModel>() {

            @Override
            public void onResponse(Call<EventTestModel> call, Response<EventTestModel> response) {

                /*Getting response from server*/
                EventTestModel eventTestModel = response.body();
                deadline = eventTestModel.getDeadline() + " " + "00:00:00";
                round = eventTestModel.getRound();
                TestData = eventTestModel.getResult();
                String question = TestData[0].getQuestion();
                for (int i = 0; i < TestData.length; i++) {
                    Log.i("Data", TestData[i].getQuestion());
                    Log.i("Data", TestData[i].getOption_a());
                    Log.i("Data", TestData[i].getOption_b());
                }
                Log.i("Deadline", deadline);
                Log.i("round", round);

                progressDialog.dismiss();

                SizeOfTestData = TestData.length;
                CurrentTestNumber = 0;
                TestImplementation(CurrentTestNumber);

            }
            @Override
            public void onFailure(Call<EventTestModel> call, Throwable t) {

            }
        });

    }

    /*Setting Textview with server data*/
    private void TestImplementation(int i) {

        if(i<SizeOfTestData) {
            CountDownTimer(i);
            Question.setText(TestData[i].getQuestion());
            Option_A.setText(TestData[i].getOption_a());
            Option_B.setText(TestData[i].getOption_b());
            Option_C.setText(TestData[i].getOption_c());
            Option_D.setText(TestData[i].getOption_d());
            Marks.setTextColor(getResources().getColor(android.R.color.white));
            Marks.setText("Marks : "+TestData[i].getMarks());
            int marks = Integer.valueOf(TestData[i].getMarks());
            CurrentScore = marks;

        }

    }


    /*Setting CountDown Timer*/
    public void CountDownTimer(int i){
        /*Setting the CountDown Timer*/
        /*
        1 min = 60000
        0.5 min = 30000
        1.5 min = 90000
        2.0 min = 120000
        */
        int time = 0;

        String timer = TestData[i].getTime();
        if(timer.equals("30sec")){
            time = 39000;

        }
        else if(timer.equals("60sec")){
            time = 69000;
        }
        else if(timer.equals("90sec")){
            time = 99000;
        }
        else if(timer.equals("120sec")){
            time = 129000;
        }

        mDownTimer = new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                CountDownText.setText("Time Left - " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                CountDownText.setText("Time up.");
                //ShowCorrectDialog(2);


            }
        }.start();

    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.optionA_LayoutGame){
            OptionA_CheckBox.setChecked(true);
            OptionB_CheckBox.setChecked(false);
            OptionC_CheckBox.setChecked(false);
            OptionD_CheckBox.setChecked(false);
        }
        else if(view.getId() == R.id.optionB_LayoutGame){
            OptionA_CheckBox.setChecked(false);
            OptionB_CheckBox.setChecked(true);
            OptionC_CheckBox.setChecked(false);
            OptionD_CheckBox.setChecked(false);
        }
        else if(view.getId() == R.id.optionC_LayoutGame){
            OptionA_CheckBox.setChecked(false);
            OptionB_CheckBox.setChecked(false);
            OptionC_CheckBox.setChecked(true);
            OptionD_CheckBox.setChecked(false);
        }
        else if(view.getId() == R.id.optionD_LayoutGame){
            OptionA_CheckBox.setChecked(false);
            OptionB_CheckBox.setChecked(false);
            OptionC_CheckBox.setChecked(false);
            OptionD_CheckBox.setChecked(true);
        }

        /*Checking ans is correct or not*/

        if(view.getId() == R.id.submitAnsBtnGame){


            if(OptionA_CheckBox.isChecked()) {
                if (TestData[CurrentTestNumber].getCorrect_option().equals("A")) {

                    if(CurrentScore != 0){
                        TotalScore += CurrentScore;
                    }
                   // Toast.makeText(EventGamePlay.this,"Score :" +TotalScore, Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog(0);
                    OptionA_CheckBox.setChecked(false);
                }
                else{
                    CurrentScore = 0;
                    snackbar(1);
                }
            }
            else if(OptionB_CheckBox.isChecked()) {
                if (TestData[CurrentTestNumber].getCorrect_option().equals("B")) {

                    if(CurrentScore != 0){
                        TotalScore += CurrentScore;
                    }
                    //Toast.makeText(EventGamePlay.this,"Score :" +TotalScore, Toast.LENGTH_SHORT).show();

                    ShowCorrectDialog(0);
                    OptionB_CheckBox.setChecked(false);
                    //CurrentTestNumber++;
                    //TestImplementation(CurrentTestNumber);
                }
                else{
                    CurrentScore = 0;
                    snackbar(1);
                }
            }
            else if(OptionC_CheckBox.isChecked()) {
                if (TestData[CurrentTestNumber].getCorrect_option().equals("C")) {
                    if(CurrentScore != 0){
                        TotalScore += CurrentScore;
                    }
                    //Toast.makeText(EventGamePlay.this,"Score :" +TotalScore, Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog(0);
                    OptionC_CheckBox.setChecked(false);
                    //CurrentTestNumber++;
                    //TestImplementation(CurrentTestNumber);
                }
                else{
                   CurrentScore = 0;
                   snackbar(1);
                }
            }
            else if(OptionD_CheckBox.isChecked()) {
                if (TestData[CurrentTestNumber].getCorrect_option().equals("D")) {
                    if(CurrentScore != 0){
                        TotalScore += CurrentScore;
                    }
                   // Toast.makeText(EventGamePlay.this,"Score :" +TotalScore, Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog(0);
                    OptionD_CheckBox.setChecked(false);
                   // CurrentTestNumber++;
                    //TestImplementation(CurrentTestNumber);
                }
                else{
                    CurrentScore = 0;
                    snackbar(1);
                }
            }
            else{
                snackbar(0);
            }

        }
        /**************************************************/

    }


    /*Showing Custom dialog for Correct Ans , Times up, Ending of test */
    public void ShowCorrectDialog(int i) {

        CorrectDialog.setContentView(R.layout.custom_correct_dialog);
        TextView vfText = CorrectDialog.findViewById(R.id.CorrectText);
        ImageView vfDone = CorrectDialog.findViewById(R.id.checkmarkCorrect);
        Button Donebtn = CorrectDialog.findViewById(R.id.btnDoneSuccess);
        mDownTimer.cancel();

        if(CurrentTestNumber == (SizeOfTestData-1)){
            vfText.setTextColor(getResources().getColor(android.R.color.black));
            Donebtn.setVisibility(View.VISIBLE);
            vfDone.setVisibility(View.VISIBLE);
            vfText.setText("Test successfully completed\nScore: "+TotalScore);
            CorrectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            CorrectDialog.setCanceledOnTouchOutside(false);
            CorrectDialog.show();
            Donebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CorrectDialog.dismiss();
                    Intent intent = new Intent(EventGamePlay.this, EventMenuItem.class);
                    intent.putExtra("number", MainMenu.value);
                    startActivity(intent);
                    finish();
                }
            });

        }
        else if(i==0){
            vfText.setTextColor(getResources().getColor(android.R.color.black));
            vfDone.setVisibility(View.VISIBLE);
            CorrectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            CorrectDialog.setCanceledOnTouchOutside(false);
            CorrectDialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    CorrectDialog.dismiss();

                }
            }, 2000);
            CurrentTestNumber++;
            TestImplementation(CurrentTestNumber);

        }
        else if(i==2){
            vfDone.setVisibility(View.GONE);
            vfText.setTextColor(getResources().getColor(R.color.GoogleColor));
            vfText.setText("Times Up");
            CorrectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            CorrectDialog.setCanceledOnTouchOutside(false);
            CorrectDialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    CorrectDialog.dismiss();
                }
            }, 2000);

            CurrentTestNumber++;
            TestImplementation(CurrentTestNumber);

        }
        //Toast.makeText(this, String.valueOf(CurrentTestNumber), Toast.LENGTH_SHORT).show();
    }


    /*Snack bar for showing error*/
    private void snackbar(int i) {
        Snackbar snackbar;
        if(i==0) {
            snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Select one option", Snackbar.LENGTH_LONG);
        }else{
            snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Wrong ans, Try again !!!", Snackbar.LENGTH_LONG);
            Marks.setTextColor(getResources().getColor(R.color.GoogleColor));
            Marks.setText("Marks : "+String.valueOf(0));
        }
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(this.getResources().getColor(R.color.GoogleColor));

        snackbar.show();
    }





}
