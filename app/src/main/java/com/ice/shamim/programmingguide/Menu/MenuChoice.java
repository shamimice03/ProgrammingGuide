package com.ice.shamim.programmingguide.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.R;

public class MenuChoice extends AppCompatActivity implements View.OnClickListener {

    CardView C_card,Cpp_card,Java_card,DataStructure_card,Algorithms_card;
    AppCompatCheckBox C_checkboxInit,Cpp_checkboxInit,Java_checkboxInit,DataStructure_checkboxInit,Algorithms_checkboxInit;
    LinearLayout mElementContainer,Buttons;
    AppCompatButton ButtonContinue,ButtonCancel;
    Animation fade;
    ScrollView scrollView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choice);

        C_card = findViewById(R.id.C_element);
        Cpp_card = findViewById(R.id.Cpp_element);
        Java_card = findViewById(R.id.Java_element);
        DataStructure_card = findViewById(R.id.DataStrcuture_element);
        Algorithms_card = findViewById(R.id.Algorithms_element);

        C_checkboxInit = findViewById(R.id.C_checkBox);
        Cpp_checkboxInit = findViewById(R.id.Cpp_checkBox);
        Java_checkboxInit = findViewById(R.id.Java_checkBox);
        DataStructure_checkboxInit = findViewById(R.id.DataStrcuture_checkBox);
        Algorithms_checkboxInit = findViewById(R.id.Algorithms_checkBox);

        ButtonContinue = findViewById(R.id.ContinueButton);
        ButtonCancel = findViewById(R.id.CancelButton);

        scrollView = findViewById(R.id.scrollView2);

        fade = AnimationUtils.loadAnimation(MenuChoice.this,R.anim.fade);


        mElementContainer = findViewById(R.id.elements_container);
        Buttons = findViewById(R.id.buttons);



        C_card.setOnClickListener(this);
        Cpp_card.setOnClickListener(this);
        Java_card.setOnClickListener(this);
        DataStructure_card.setOnClickListener(this);
        Algorithms_card.setOnClickListener(this);


        C_checkboxInit.setOnClickListener(this);
        Cpp_checkboxInit.setOnClickListener(this);
        Java_checkboxInit.setOnClickListener(this);
        DataStructure_checkboxInit.setOnClickListener(this);
        Algorithms_checkboxInit.setOnClickListener(this);


        ButtonCancel.setOnClickListener(this);
        ButtonContinue.setOnClickListener(this);

        Buttons.setAlpha(0);


    }

    @Override
    public void onClick(View view) {




        if(view.getId() == R.id.C_element || view.getId() == R.id.C_checkBox){


            C_checkboxInit.setChecked(true);
            Buttons.setAlpha(1);
            Buttons.startAnimation(fade);

            C_card.setClickable(false);
            Cpp_card.setClickable(false);
            Java_card.setClickable(false);
            DataStructure_card.setClickable(false);
            Algorithms_card.setClickable(false);

            C_checkboxInit.setClickable(false);
            Cpp_checkboxInit.setClickable(false);
            Java_checkboxInit.setClickable(false);
            DataStructure_checkboxInit.setClickable(false);
            Algorithms_checkboxInit.setClickable(false);

        }


        else if(view.getId() == R.id.Cpp_element || view.getId() == R.id.Cpp_checkBox){


            Cpp_checkboxInit.setChecked(true);
            Buttons.setAlpha(1);
            Buttons.startAnimation(fade);

            C_card.setClickable(false);
            Cpp_card.setClickable(false);
            Java_card.setClickable(false);
            DataStructure_card.setClickable(false);
            Algorithms_card.setClickable(false);

            C_checkboxInit.setClickable(false);
            Cpp_checkboxInit.setClickable(false);
            Java_checkboxInit.setClickable(false);
            DataStructure_checkboxInit.setClickable(false);
            Algorithms_checkboxInit.setClickable(false);

        }

        else if(view.getId() == R.id.Java_element  || view.getId() == R.id.Java_checkBox){


            Java_checkboxInit.setChecked(true);
            Buttons.setAlpha(1);
            Buttons.startAnimation(fade);

            C_card.setClickable(false);
            Cpp_card.setClickable(false);
            Java_card.setClickable(false);
            DataStructure_card.setClickable(false);
            Algorithms_card.setClickable(false);

            C_checkboxInit.setClickable(false);
            Cpp_checkboxInit.setClickable(false);
            Java_checkboxInit.setClickable(false);
            DataStructure_checkboxInit.setClickable(false);
            Algorithms_checkboxInit.setClickable(false);

        }


        else if(view.getId() == R.id.DataStrcuture_element  || view.getId() == R.id.DataStrcuture_checkBox){


            DataStructure_checkboxInit.setChecked(true);
            Buttons.setAlpha(1);
            Buttons.startAnimation(fade);

            C_card.setClickable(false);
            Cpp_card.setClickable(false);
            Java_card.setClickable(false);
            DataStructure_card.setClickable(false);
            Algorithms_card.setClickable(false);

            C_checkboxInit.setClickable(false);
            Cpp_checkboxInit.setClickable(false);
            Java_checkboxInit.setClickable(false);
            DataStructure_checkboxInit.setClickable(false);
            Algorithms_checkboxInit.setClickable(false);

        }


        else if(view.getId() == R.id.Algorithms_element  || view.getId() == R.id.Algorithms_checkBox){


            Algorithms_checkboxInit.setChecked(true);
            Buttons.setAlpha(1);
            Buttons.startAnimation(fade);

            C_card.setClickable(false);
            Cpp_card.setClickable(false);
            Java_card.setClickable(false);
            DataStructure_card.setClickable(false);
            Algorithms_card.setClickable(false);

            C_checkboxInit.setClickable(false);
            Cpp_checkboxInit.setClickable(false);
            Java_checkboxInit.setClickable(false);
            DataStructure_checkboxInit.setClickable(false);
            Algorithms_checkboxInit.setClickable(false);

        }




        if(view.getId() == R.id.CancelButton){

            Buttons.setAlpha(0);


            C_checkboxInit.setChecked(false);
            Cpp_checkboxInit.setChecked(false);
            Java_checkboxInit.setChecked(false);
            DataStructure_checkboxInit.setChecked(false);
            Algorithms_checkboxInit.setChecked(false);

            C_card.setClickable(true);
            Cpp_card.setClickable(true);
            Java_card.setClickable(true);
            DataStructure_card.setClickable(true);
            Algorithms_card.setClickable(true);

            C_checkboxInit.setClickable(true);
            Cpp_checkboxInit.setClickable(true);
            Java_checkboxInit.setClickable(true);
            DataStructure_checkboxInit.setClickable(true);
            Algorithms_checkboxInit.setClickable(true);

        }

        if(view.getId() == R.id.ContinueButton){

           startActivity(new Intent(this, MainMenu.class));

        }


    }
}
