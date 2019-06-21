package com.ice.shamim.programmingguide.MainMenu.Fragment.Course.LessonAndTest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Course.ProductAdapter;
import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.R;

public class LessonAndTestMain extends AppCompatActivity {

        TabLayout tabLayout;
        String title = ProductAdapter.title;
        int number = MainMenu.value;



    @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lesson_and_test_main);

            final Toolbar toolbar = findViewById(R.id.toolbar);

            final Activity activity = LessonAndTestMain.this;

            tabLayout = findViewById(R.id.tablayout);
            final TabItem tabLesson = findViewById(R.id.tabLesson);
            final TabItem tabTest = findViewById(R.id.tabTest);
            final ViewPager viewPager = findViewById(R.id.viewPager);





            Toast.makeText(activity,title , Toast.LENGTH_SHORT).show();

            /*For default*/

            toolbar.setBackground(ContextCompat.getDrawable(LessonAndTestMain.this,
                    R.drawable.gradient_lesson_main));
            tabLayout.setBackground(ContextCompat.getDrawable(LessonAndTestMain.this,
                    R.drawable.gradient_lesson_main));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
                window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
                window.setBackgroundDrawable(ContextCompat.getDrawable(LessonAndTestMain.this,
                        R.drawable.gradient_lesson_main));
                //window.setBackgroundDrawable(background);
            }

            /**/

            toolbar.setTitle(title);
            setSupportActionBar(toolbar);

            PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(pageAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LessonAndTestMain.this, MainMenu.class);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }
            });

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    viewPager.setCurrentItem(tab.getPosition());
                    if (tab.getPosition() == 0) {
                        toolbar.setBackground(ContextCompat.getDrawable(LessonAndTestMain.this,
                                R.drawable.gradient_lesson_main));
                        tabLayout.setBackground(ContextCompat.getDrawable(LessonAndTestMain.this,
                                R.drawable.gradient_lesson_main));

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = activity.getWindow();
                          //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
                            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
                            window.setBackgroundDrawable(ContextCompat.getDrawable(LessonAndTestMain.this,
                                    R.drawable.gradient_lesson_main));
                            //window.setBackgroundDrawable(background);
                        }
                    }
                    if (tab.getPosition() == 1) {
                        toolbar.setBackground(ContextCompat.getDrawable(LessonAndTestMain.this,
                                R.drawable.gradient_test_main));
                        tabLayout.setBackground(ContextCompat.getDrawable(LessonAndTestMain.this,
                                R.drawable.gradient_test_main));

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = activity.getWindow();
                            //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
                            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
                            window.setBackgroundDrawable(ContextCompat.getDrawable(LessonAndTestMain.this,
                                    R.drawable.gradient_test_main));
                            //window.setBackgroundDrawable(background);
                        }
                    }
                }


                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }


