package com.ice.shamim.programmingguide.MainMenu.Fragment.Event;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ice.shamim.programmingguide.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EventFragment extends Fragment implements View.OnClickListener {

    public EventFragment() {
        // Required empty public constructor
    }

    TextView completionRate,timeRemains;
    CardView eventCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_event, container, false);

        completionRate = (TextView) view.findViewById(R.id.percentCompletion);
        timeRemains = (TextView) view.findViewById(R.id.timeremain);
        eventCard = (CardView) view.findViewById(R.id.EventcardView);


       String LeftTime = TimeLeft();

       completionRate.setText("5% Completed");
       timeRemains.setText(LeftTime);
       eventCard.setOnClickListener(this);


        return view;
    }



    ///Time remains

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



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.EventcardView:
                //Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),EventMenuItem.class);
                getActivity().startActivity(intent);
                break;

        }

    }
}
