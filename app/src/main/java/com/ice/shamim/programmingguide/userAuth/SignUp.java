package com.ice.shamim.programmingguide.userAuth;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.Uva.UVa;

public class SignUp extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;

    EditText Uname,Upassword,UretypePassword,Uemail;
    TextView loginOption;
    AppCompatButton RegisterButton,ReSendLink;
    RelativeLayout nextPage,VerificationPage;
    ConstraintLayout mConstrainLayout;
    RelativeLayout FormRegister;
    FrameLayout frameLayout;
    int check_field = 0;
    Snackbar snackbar;
    ProgressDialog progressDialog;
    Dialog verifiedDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        Uname = findViewById(R.id.nameEditText);
        Uemail = findViewById(R.id.emailEditText);
        Upassword = findViewById(R.id.passwordEditText);
        UretypePassword = findViewById(R.id.retypePasswordEditText);
        RegisterButton = findViewById(R.id.registerButton);
        nextPage = findViewById(R.id.nextSignUp);
        mConstrainLayout = findViewById(R.id.mainLayout);
        frameLayout = findViewById(R.id.frame);
        FormRegister = findViewById(R.id.registerForm);
        loginOption = findViewById(R.id.loginOptionsPage);
        VerificationPage = findViewById(R.id.verificationPage);
        ReSendLink = findViewById(R.id.resent_link);

        verifiedDialog = new Dialog(SignUp.this);


        nextPage.setVisibility(View.INVISIBLE);
        VerificationPage.setVisibility(View.INVISIBLE);


        RegisterButton.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        loginOption.setOnClickListener(this);
        ReSendLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {



        if(view.getId()==  R.id.registerButton){

             registerUser();

        }
        else if(view.getId() ==  R.id.nextSignUp){

            startActivity(new Intent(this, UVa.class));
        }
        else if(view.getId() ==  R.id.loginOptionsPage){

            startActivity(new Intent(this,Login_options.class));
        }
        else if(view.getId() ==  R.id.resent_link){

            resendEmailLink();

        }



    }



    private void registerUser() {


        String name = Uname.getText().toString().trim();
        final String email = Uemail.getText().toString().trim();
        final String password = Upassword.getText().toString().trim();
        String re_password = UretypePassword.getText().toString().trim();


        if(name.isEmpty()){
            check_field=0;
            snackbar();
            return;
        }
        else if(email.isEmpty()) {
            check_field=0;
            snackbar();
            return;
        }
        else if(password.isEmpty()){
            check_field=0;
            snackbar();
            return;
        }
        else if(re_password.isEmpty()){
            check_field=0;
            snackbar();
            return;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            check_field=1;
            snackbar();
            return;
        }
        else if(password.length()<6){
            check_field=2;
            snackbar();
            return;
        }

        else if(re_password.equals(password)==false){
            check_field=3;
            snackbar();
            return;
        }


        progressDialog = new ProgressDialog(SignUp.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Signing up...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Uname.getText().clear();
                    Uemail.getText().clear();
                    Upassword.getText().clear();
                    UretypePassword.getText().clear();
                    progressDialog.dismiss();

                    FormRegister.setVisibility(View.INVISIBLE);
                    VerificationPage.setVisibility(View.VISIBLE);


                    //Email verification sending process
                    FirebaseUser user;
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Verification sent", Toast.LENGTH_SHORT).show();
                                content(email,password);
                            }
                        }
                    });


                }
                else {
                    progressDialog.dismiss();
                    check_field=4;
                    snackbar();
                }
            }


        });



    }


    // Auto refresh Activity


    private void content(final String email, final String password) {


        final FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            if(user.isEmailVerified()){
                                ShowVerificationDialog();
                                return;
                            }
                            else{

                                refresh(10000,email,password);
                            }
                        }
                    }
                });


        }



    private void refresh(int milliseconds, final String email, final String password) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                content(email,password);
            }
        };

        handler.postDelayed(runnable,milliseconds);
    }


    ////////////////// End of Auto refresh activity//////////////



    private void resendEmailLink() {

        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Verification sent", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    public void ShowVerificationDialog() {

        verifiedDialog.setContentView(R.layout.custom_dialog_verification);
        TextView vfText = verifiedDialog.findViewById(R.id.verifiedText);
        ImageView vfDone = verifiedDialog.findViewById(R.id.checkmark);
        Button Donebtn = verifiedDialog.findViewById(R.id.btnDone);

        verifiedDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        verifiedDialog.setCanceledOnTouchOutside(false);
        verifiedDialog.show();

        Donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifiedDialog.dismiss();
                startActivity(new Intent(SignUp.this,UVa.class));
                finish();
            }
        });


    }


    private void snackbar() {

        if(check_field==0) {
            snackbar = Snackbar.make(mConstrainLayout, "All fields are mandatory.", Snackbar.LENGTH_LONG);
        }
        else if(check_field==1){
            snackbar = Snackbar.make(mConstrainLayout, "Please enter a valid Email.", Snackbar.LENGTH_LONG);
        }
        else if(check_field==2){
            snackbar = Snackbar.make(mConstrainLayout, "Minimum length of password should be 6.", Snackbar.LENGTH_LONG);
        }
        else if(check_field==3){
            snackbar = Snackbar.make(mConstrainLayout, "Password didn't matched.", Snackbar.LENGTH_LONG);
        }
        else if(check_field==4){
            snackbar = Snackbar.make(mConstrainLayout, "The Email address already in use.", Snackbar.LENGTH_LONG);
        }

        View sbView = snackbar.getView();
        sbView.setBackgroundColor(this.getResources().getColor(R.color.GoogleColor));

        snackbar.show();
    }
}
