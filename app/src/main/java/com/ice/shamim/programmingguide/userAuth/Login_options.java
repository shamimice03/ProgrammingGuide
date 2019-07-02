package com.ice.shamim.programmingguide.userAuth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ice.shamim.programmingguide.Menu.MenuChoice;
import com.ice.shamim.programmingguide.R;

public class Login_options extends AppCompatActivity implements View.OnClickListener{

    AppCompatButton buttonGoogle,buttonFacebook,buttonEmail;
    TextView LoginPage;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);

        buttonGoogle = findViewById(R.id.GoogleButton);
        buttonFacebook = findViewById(R.id.FacebookButton);
        buttonEmail = findViewById(R.id.appRegisterButton);
        LoginPage = findViewById(R.id.loginPageGO);


        buttonGoogle.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);
        buttonGoogle.setVisibility(View.VISIBLE);
        buttonFacebook.setVisibility(View.VISIBLE);
        buttonEmail.setOnClickListener(this);
        LoginPage.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
                finish();
                startActivity(new Intent(this, MenuChoice.class));
        }


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.GoogleButton){

            //startActivity(new Intent(this,SignUp.class));
        }
        else if (view.getId() == R.id.FacebookButton){

            //startActivity(new Intent(this,SignUp.class));
        }

        else if (view.getId() == R.id.appRegisterButton){

            startActivity(new Intent(this,SignUp.class));
        }

        else if(view.getId() == R.id.loginPageGO){

            startActivity(new Intent(this,Login.class));
        }

    }
}
