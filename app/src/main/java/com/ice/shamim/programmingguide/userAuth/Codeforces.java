package com.ice.shamim.programmingguide.userAuth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ice.shamim.programmingguide.Menu.MenuChoice;
import com.ice.shamim.programmingguide.R;

public class Codeforces extends AppCompatActivity implements View.OnClickListener{

    EditText CfHandle;
    AppCompatButton CfIdSubmit;
    RelativeLayout nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeforces);

        CfHandle = findViewById(R.id.CfHandleEditText);
        CfIdSubmit = findViewById(R.id.submitCfId);
        nextPage = findViewById(R.id.nextCodeforces);

        nextPage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.nextCodeforces){
            startActivity(new Intent(this,MenuChoice.class));
        }


    }
}
