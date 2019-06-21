package com.ice.shamim.programmingguide.MainMenu;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ice.shamim.programmingguide.Menu.MenuChoice;
import com.ice.shamim.programmingguide.R;

public class Navigation extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton Nav_Button;
    LinearLayout SelectLinearLayout,LibraryLinearLayout,CreditsLinearLayout,FeedbackLinearLayout,ForumLinearLayout;

    Animation left_to_right_animation_select,left_to_right_animation_library
    , left_to_right_animation_forum,left_to_right_animation_feedback,left_to_right_animation_credits;


    public  static int value;
    public int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        /**/

        number = getIntent().getExtras().getInt("number");
        value = number;
        Toast.makeText(this, Integer.toString(number), Toast.LENGTH_SHORT).show();

        /**/

        Nav_Button = findViewById(R.id.backToMainMenu);
        SelectLinearLayout = findViewById(R.id.Select);
        LibraryLinearLayout = findViewById(R.id.Library);
        ForumLinearLayout = findViewById(R.id.Forum);
        CreditsLinearLayout = findViewById(R.id.Credits);
        FeedbackLinearLayout = findViewById(R.id.Feedback);



        Nav_Button.setOnClickListener(this);
        SelectLinearLayout.setOnClickListener(this);
        LibraryLinearLayout.setOnClickListener(this);
        ForumLinearLayout.setOnClickListener(this);
        CreditsLinearLayout.setOnClickListener(this);
        FeedbackLinearLayout.setOnClickListener(this);






        //Moving Gradient
        ConstraintLayout constraintLayout =  findViewById(R.id.nav_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        //**********************//



        left_to_right_animation_select = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        left_to_right_animation_library = AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        left_to_right_animation_forum =AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        left_to_right_animation_credits =AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        left_to_right_animation_feedback =AnimationUtils.loadAnimation(this,R.anim.left_to_right);


        left_to_right_animation_select.setDuration(1500);
        SelectLinearLayout.startAnimation(left_to_right_animation_select);

        left_to_right_animation_library.setDuration(2000);
        LibraryLinearLayout.startAnimation(left_to_right_animation_library);


        left_to_right_animation_forum.setDuration(2500);
        ForumLinearLayout.startAnimation(left_to_right_animation_forum);


        left_to_right_animation_credits.setDuration(3000);
        CreditsLinearLayout.startAnimation(left_to_right_animation_credits);


        left_to_right_animation_feedback.setDuration(3500);
        FeedbackLinearLayout.startAnimation(left_to_right_animation_feedback);



    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.backToMainMenu){

            Intent intent = new Intent(this, MainMenu.class);
            intent.putExtra("number", number);
            startActivity(intent);

           //startActivity(new Intent(this,MainMenu.class));
           // finish();
        }

        else if(view.getId() == R.id.Select){

            startActivity(new Intent(this, MenuChoice.class));
            finish();

        }
        else if(view.getId() == R.id.Library){

        }
        else if(view.getId() == R.id.Forum){

        }
        else if(view.getId() == R.id.Credits){

        }
        else if(view.getId() == R.id.Feedback){

        }





    }
}
