package com.ice.shamim.programmingguide.MainMenu.Fragment.Event;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.APIs.QuizTestApi;
import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay.EventTestModel;
import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay.Result;
import com.ice.shamim.programmingguide.R;
import com.ice.shamim.programmingguide.userAuth.SignUp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventMenuItem extends AppCompatActivity {
    private ViewPager mViewPager;



    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    ProgressDialog progressDialog;
    private Result[] TestData;
    private String deadline;
    private String round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu_item);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);


        ParsingDetailsOfData();

    }

    private void ParsingDetailsOfData() {

        progressDialog = new ProgressDialog(EventMenuItem.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Getting ready...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(QuizTestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizTestApi api = retrofit.create(QuizTestApi.class);

        //Call<List<EventTestModel>> call = api.getEventTestData();

        Call<EventTestModel> call = api.getEventTestData();

        call.enqueue(new Callback<EventTestModel>() {
     

            @Override
            public void onResponse(Call<EventTestModel> call, Response<EventTestModel> response) {

                 EventTestModel eventTestModel = response.body();
                 deadline = eventTestModel.getDeadline()+" "+"00:00:00";
                 round = eventTestModel.getRound();
                 TimeLeft(deadline);

                TestData = eventTestModel.getResult();
                String question = TestData[0].getQuestion();
                for(int i=0; i<TestData.length; i++) {
                    Log.i("Data", TestData[i].getQuestion());
                    Log.i("Data", TestData[i].getOption_a());
                    Log.i("Data", TestData[i].getOption_b());
                }
                Log.i("Deadline",deadline);
                Log.i("round",round);

            }

            @Override
            public void onFailure(Call<EventTestModel> call, Throwable t) {

            }
        });

    }


    public String TimeLeft(String timeRemain) {

        String time = timeRemain;
        String toyBornTime = time;   //need to sync from server
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        Date deadlineTime = null;

        try {
            deadlineTime = dateFormat.parse(toyBornTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date currentDate = new Date();

        Log.e("date",currentDate.toString());

        long diff = deadlineTime.getTime() - currentDate.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long hours_left = hours-(days*24);
        long minutes_left = minutes- (hours_left*60);


        Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                + " hours: " + hours + " days: " + days);

        String s;

        if(minutes_left<=0) {
            s = "Deadline";
        }
        else if(days==0){
            String s1 = String.valueOf(hours_left);
            String s2 = String.valueOf(minutes_left);
            s = s1 + " hrs " + s2 +" min left";
        }else {
            String s1 = String.valueOf(days);
            String s2 = String.valueOf(hours_left);
            s = s1 + " days " + s2 +" hrs left";
        }


       /*Setting viewpager and Cards*/
        mCardAdapter = new CardPagerAdapter(EventMenuItem.this);
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, s,R.mipmap.quiz_test));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, s,R.mipmap.coding_test));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, s,R.mipmap.interview_questions));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, s,R.mipmap.interview_quiz));


        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        progressDialog.dismiss();
        return s;


    }




}
