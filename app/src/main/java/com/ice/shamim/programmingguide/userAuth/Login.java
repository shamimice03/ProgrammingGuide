package com.ice.shamim.programmingguide.userAuth;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ice.shamim.programmingguide.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mSignUpPage;
    EditText UEmail,Upassword;
    AppCompatButton ButtonLogin;
    ConstraintLayout mConstrainLayout;

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


    }

    @Override
    public void onClick(View view) {

        if(view.getId()==  R.id.SignUpPage){

            startActivity(new Intent(this,SignUp.class));
        }

        if(view.getId() == R.id.loginButton){

                snackbar();

        }
    }

    private void snackbar() {

        Snackbar snackbar = Snackbar.make(mConstrainLayout,"All fields are mandatory",Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(this.getResources().getColor(R.color.snackbarColor));

        snackbar.show();
    }
}
