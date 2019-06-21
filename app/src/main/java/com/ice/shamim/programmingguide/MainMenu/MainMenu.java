package com.ice.shamim.programmingguide.MainMenu;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.ice.shamim.programmingguide.MainMenu.Fragment.Course.CourseFragment;
import com.ice.shamim.programmingguide.MainMenu.Fragment.Course.Product;
import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventFragment;
import com.ice.shamim.programmingguide.MainMenu.Fragment.LeaderboardFragment;
import com.ice.shamim.programmingguide.MainMenu.Fragment.ProfileFragment;
import com.ice.shamim.programmingguide.MainMenu.Fragment.RecommandedFragment;
import com.ice.shamim.programmingguide.R;

import java.util.ArrayList;
import java.util.List;


public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    LinearLayout linearLayout;
    BottomNavigationView bottomNavigationView;

    AppCompatButton nav_button;

    //This is our viewPager
    ViewPager viewPager;

    //Fragments
    CourseFragment courseFragment;
    EventFragment eventFragment;
    LeaderboardFragment leaderboardFragment;
    ProfileFragment profileFragment;
    RecommandedFragment recommandedFragment;

   //Navigation Drawer

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    MenuItem prevMenuItem;
    public  static int value;
    public int number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /*data from menu choice*/

        number = getIntent().getExtras().getInt("number");

        value = number;
        Toast.makeText(this, Integer.toString(number), Toast.LENGTH_SHORT).show();
        /******/





        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        nav_button = findViewById(R.id.nav_icon);
        linearLayout = findViewById(R.id.layout_touch);




        nav_button.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_course:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_recommanded:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_event:
                                viewPager.setCurrentItem(2);
                                break;

                            case R.id.action_profile:
                                viewPager.setCurrentItem(3);
                                break;

                            case R.id.action_leaderboard:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(prevMenuItem != null){
                    prevMenuItem.setChecked(true);
                }
                else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
                //viewPager.setOffscreenPageLimit(2);



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

             setupViewPager(viewPager);


    }

    /**/
    public int getSelectedItem() {

        return value;
    }


    public void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        courseFragment = new CourseFragment();
        recommandedFragment = new RecommandedFragment();
        eventFragment = new EventFragment();
        profileFragment = new ProfileFragment();
        leaderboardFragment = new LeaderboardFragment();

        adapter.addFragment(courseFragment);
        adapter.addFragment(recommandedFragment);
        adapter.addFragment(eventFragment);
        adapter.addFragment(profileFragment);
        adapter.addFragment(leaderboardFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {


        if(view.getId() == R.id.nav_icon){


            //Intent intent = new Intent(this,Navigation.class);
            Intent intent = new Intent(this, Navigation.class);
            intent.putExtra("number", number);
            startActivity(intent);

            Bundle bndlAnimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();

            startActivity(intent, bndlAnimation);


        }

        if(view.getId() == R.id.layout_touch){

            Intent intent = new Intent(this,Navigation.class);
            Bundle bndlAnimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();

            startActivity(intent, bndlAnimation);


        }



    }
}
