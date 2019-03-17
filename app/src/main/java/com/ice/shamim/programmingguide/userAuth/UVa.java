package com.ice.shamim.programmingguide.userAuth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ice.shamim.programmingguide.R;

public class UVa extends AppCompatActivity implements View.OnClickListener{

    EditText UVaUserName;
    AppCompatButton UVaIdSubmit;
    RelativeLayout nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uva);

        UVaUserName = findViewById(R.id.UVaUserNameEditText);
        UVaIdSubmit = findViewById(R.id.submitUvaId);
        nextPage = findViewById(R.id.nextUVa);

        nextPage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.nextUVa){
            startActivity(new Intent(this,Codeforces.class));
        }

    }
}
