package com.ice.shamim.programmingguide.MainMenu.Fragment.Event;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ice.shamim.programmingguide.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventMenuItem extends AppCompatActivity {
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu_item);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);


        String timeleft = TimeLeft();
        mCardAdapter = new CardPagerAdapter(EventMenuItem.this);
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, timeleft,R.mipmap.quiz_test));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, timeleft,R.mipmap.coding_test));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, timeleft,R.mipmap.interview_questions));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, timeleft,R.mipmap.interview_quiz));


        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

    }



    public String TimeLeft() {

        String toyBornTime = "2019-02-15 00:00:00";   //need to sync from server
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        Date oldDate = null;

        try {
            oldDate = dateFormat.parse(toyBornTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Date currentDate = new Date();

        long diff = oldDate.getTime() -currentDate.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long hours_left = hours % 24;




        Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                + " hours: " + hours + " days: " + days);

        String s1 = String.valueOf(days);
        String s2 = String.valueOf(hours_left);

        String s = s1 + " days " + s2 +" hrs left";

        return s;

    }



}
