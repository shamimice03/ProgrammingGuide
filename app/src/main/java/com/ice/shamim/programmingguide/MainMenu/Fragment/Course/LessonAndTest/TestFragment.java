package com.ice.shamim.programmingguide.MainMenu.Fragment.Course.LessonAndTest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Html;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ice.shamim.programmingguide.MainMenu.Fragment.Course.ProductAdapter;
import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.SignUp;
import com.ice.shamim.programmingguide.userAuth.Uva_Connect.UVa_Connect;

import java.util.ArrayList;
import java.util.List;


public class TestFragment extends Fragment implements View.OnClickListener {


    private List<TestModel> looplist;
    private FirebaseFirestore db;
    String Course_name;
    public int SizeOfTestData,CurrentTestNumber;
    Dialog CorrectDialog,DoneDialog;


    TextView Question,Option_A,Option_B,Option_C,Option_D;
    AppCompatCheckBox OptionA_CheckBox,OptionB_CheckBox,OptionC_CheckBox,OptionD_CheckBox,CheckBox;
    AppCompatButton submitAnsBtn;
    LinearLayout linearLayoutOPtionA,linearLayoutOPtionB,linearLayoutOPtionC,linearLayoutOPtionD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_test, container, false);

        /*Initialization Of Dialog*/
        CorrectDialog = new Dialog(getActivity());

        /*Layer*/
        linearLayoutOPtionA = (LinearLayout) view.findViewById(R.id.optionA_Layout);
        linearLayoutOPtionB= (LinearLayout) view.findViewById(R.id.optionB_Layout);
        linearLayoutOPtionC= (LinearLayout) view.findViewById(R.id.optionC_Layout);
        linearLayoutOPtionD= (LinearLayout) view.findViewById(R.id.optionD_Layout);


        /*TextView Initialization*/
        Question = (TextView) view.findViewById(R.id.question);
        Option_A = (TextView) view.findViewById(R.id.optionA_textView);
        Option_B = (TextView) view.findViewById(R.id.optionB_textView);
        Option_C = (TextView) view.findViewById(R.id.optionC_textView);
        Option_D = (TextView) view.findViewById(R.id.optionD_textView);

        /*CheckBox Initialization*/

        OptionA_CheckBox = (AppCompatCheckBox) view.findViewById(R.id.optionA_checkBox);
        OptionB_CheckBox = (AppCompatCheckBox) view.findViewById(R.id.optionB_checkBox);
        OptionC_CheckBox = (AppCompatCheckBox) view.findViewById(R.id.optionC_checkBox);
        OptionD_CheckBox = (AppCompatCheckBox) view.findViewById(R.id.optionD_checkBox);

        /*Submit Ans*/
        submitAnsBtn = (AppCompatButton) view.findViewById(R.id.submitAnsBtn);


        /*Taking input from radio button*/
        linearLayoutOPtionA.setOnClickListener(this);
        linearLayoutOPtionB.setOnClickListener(this);
        linearLayoutOPtionC.setOnClickListener(this);
        linearLayoutOPtionD.setOnClickListener(this);
        submitAnsBtn.setOnClickListener(this);

        /*Fetching data from Firebase firestore*/
        int course = MainMenu.value;
        String topic = ProductAdapter.title;
        String Course_Id = getCourseName(course);
        Toast.makeText(getActivity(), topic+" "+Course_Id, Toast.LENGTH_SHORT).show();
        db = FirebaseFirestore.getInstance();
        CollectionReference next = db.collection("courses")
                .document(Course_Id).collection(topic);

        next.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    Toast.makeText(getActivity(), "list" + list.size(), Toast.LENGTH_SHORT).show();
                    looplist = new ArrayList<>();
                    for (DocumentSnapshot d : list) {
                        TestModel testData = d.toObject(TestModel.class);
                        looplist.add(testData);
                    }
                    SizeOfTestData = looplist.size();
                    CurrentTestNumber = 0;
                    TestImplementation(CurrentTestNumber);


                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        /***********************************************************/




        /*returning view*/
        return  view;
    }

    private void TestImplementation(int i) {

        //String ans = looplist.get(0).getQuestion();
        // String ans1 = looplist.get(1).getQuestion();
        // Toast.makeText(getActivity(), size +" "+ ans +" "+ans1, Toast.LENGTH_SHORT).show();


        if(i<SizeOfTestData) {

            Question.setText(looplist.get(i).getQuestion());
            Option_A.setText(looplist.get(i).getOptionA());
            Option_B.setText(looplist.get(i).getOptionB());
            Option_C.setText(looplist.get(i).getOptionC());
            Option_D.setText(looplist.get(i).getOptionD());
            // CorrectDialog.dismiss();
        }


    }



    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.optionA_Layout){
            OptionA_CheckBox.setChecked(true);
            OptionB_CheckBox.setChecked(false);
            OptionC_CheckBox.setChecked(false);
            OptionD_CheckBox.setChecked(false);
        }
        else if(view.getId() == R.id.optionB_Layout){
            OptionA_CheckBox.setChecked(false);
            OptionB_CheckBox.setChecked(true);
            OptionC_CheckBox.setChecked(false);
            OptionD_CheckBox.setChecked(false);
        }
        else if(view.getId() == R.id.optionC_Layout){
            OptionA_CheckBox.setChecked(false);
            OptionB_CheckBox.setChecked(false);
            OptionC_CheckBox.setChecked(true);
            OptionD_CheckBox.setChecked(false);
        }
        else if(view.getId() == R.id.optionD_Layout){
            OptionA_CheckBox.setChecked(false);
            OptionB_CheckBox.setChecked(false);
            OptionC_CheckBox.setChecked(false);
            OptionD_CheckBox.setChecked(true);
        }

        /*Checking ans is correct or not*/

        if(view.getId() == R.id.submitAnsBtn){

            String check1="1";
            String check2="2";
            String check3="3";
            String check4="4";

            if(OptionA_CheckBox.isChecked()) {
                if (looplist.get(CurrentTestNumber).getAns().equals("1")) {
                    Toast.makeText(getActivity(),"Done", Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog();
                    OptionA_CheckBox.setChecked(false);
                    CurrentTestNumber++;
                    TestImplementation(CurrentTestNumber);
                }
                else{
                    snackbar(1);
                }
            }
            else if(OptionB_CheckBox.isChecked()) {
                if (looplist.get(CurrentTestNumber).getAns().equals("2")) {
                    Toast.makeText(getActivity(),"Done", Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog();
                    OptionB_CheckBox.setChecked(false);
                    CurrentTestNumber++;
                    TestImplementation(CurrentTestNumber);
                }
                else{
                    snackbar(1);
                }
            }
            else if(OptionC_CheckBox.isChecked()) {
                if (looplist.get(CurrentTestNumber).getAns().equals("3")) {
                    Toast.makeText(getActivity(),"Done", Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog();
                    OptionC_CheckBox.setChecked(false);
                    CurrentTestNumber++;
                    TestImplementation(CurrentTestNumber);
                }
                else{
                    snackbar(1);
                }
            }
            else if(OptionD_CheckBox.isChecked()) {
                if (looplist.get(CurrentTestNumber).getAns().equals("4")) {
                    Toast.makeText(getActivity(),"Done", Toast.LENGTH_SHORT).show();
                    ShowCorrectDialog();
                    OptionD_CheckBox.setChecked(false);
                    CurrentTestNumber++;
                    TestImplementation(CurrentTestNumber);
                }
                else{
                    snackbar(1);
                }
            }
            else{
                snackbar(0);
            }

        }
        /**************************************************/

    }

    private void snackbar(int i) {
        Snackbar snackbar;
        if(i==0) {
            snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Select one option", Snackbar.LENGTH_LONG);
        }else{
            snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Wrong ans, Try again !!!", Snackbar.LENGTH_LONG);
        }
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(this.getResources().getColor(R.color.GoogleColor));

        snackbar.show();
    }

    public void ShowCorrectDialog() {

        CorrectDialog.setContentView(R.layout.custom_correct_dialog);
        TextView vfText = CorrectDialog.findViewById(R.id.CorrectText);
        ImageView vfDone = CorrectDialog.findViewById(R.id.checkmarkCorrect);
        Button Donebtn = CorrectDialog.findViewById(R.id.btnDoneSuccess);


        if(CurrentTestNumber == (SizeOfTestData-1)){

            Donebtn.setVisibility(View.VISIBLE);
            vfText.setText("Test successfully completed");
            CorrectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            CorrectDialog.setCanceledOnTouchOutside(false);
            CorrectDialog.show();
            Donebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MainMenu.class);
                    intent.putExtra("number", MainMenu.value);
                    startActivity(intent);
                }
            });

        }
        else{
            CorrectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            CorrectDialog.setCanceledOnTouchOutside(false);
            CorrectDialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    CorrectDialog.dismiss();
                }
            }, 2000);


        }


        Toast.makeText(getActivity(),String.valueOf(CurrentTestNumber), Toast.LENGTH_SHORT).show();

    }


    private String getCourseName(int course) {

        int c = course;
        if(c==1){
            Course_name = "cprogramming";
        }
        else if(c==2){
            Course_name = "cpp";
        }
        else if(c==3){
            Course_name = "java";
        }
        else if(c==4){
            Course_name = "datastructure";
        }
        else if(c==5){
            Course_name = "algorithms";
        }

        return  Course_name;
    }



}