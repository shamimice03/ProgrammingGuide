package com.ice.shamim.programmingguide.userAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.Menu.MenuChoice;
import com.ice.shamim.programmingguide.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mSignUpPage;
    EditText UEmail,Upassword;
    AppCompatButton ButtonLogin;
    ConstraintLayout mConstrainLayout;
    FirebaseAuth mAuth;
    int check_field = 0;
    Snackbar snackbar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mSignUpPage = findViewById(R.id.SignUpPage);
        UEmail = findViewById(R.id.emailEditText);
        Upassword = findViewById(R.id.passwordEditText);
        ButtonLogin = findViewById(R.id.loginButton);
        mConstrainLayout = findViewById(R.id.mainLayout);

        mSignUpPage.setOnClickListener(this);
        ButtonLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==  R.id.SignUpPage){

            startActivity(new Intent(this,SignUp.class));
        }

        if(view.getId() == R.id.loginButton){

                //snackbar();
            login();

        }
    }

    private void login() {



        final String email = UEmail.getText().toString().trim();
        final String password = Upassword.getText().toString().trim();

         if(email.isEmpty()) {
            check_field=0;
            snackbar();
            return;
        }
        else if(password.isEmpty()){
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




        progressDialog = new ProgressDialog(Login.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Logging in...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    progressDialog.dismiss();
                    startActivity(new Intent(Login.this, MenuChoice.class));
                    finish();

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

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


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(this.getResources().getColor(R.color.GoogleColor));

        snackbar.show();
    }
}
