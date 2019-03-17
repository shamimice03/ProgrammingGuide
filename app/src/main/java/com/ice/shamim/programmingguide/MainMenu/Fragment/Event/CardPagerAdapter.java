package com.ice.shamim.programmingguide.MainMenu.Fragment.Event;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.MainMenu.Navigation;
import com.ice.shamim.programmingguide.Menu.MenuChoice;
import com.ice.shamim.programmingguide.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter  {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;


    Context context;
    public CardPagerAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.context = context;
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.event_menu, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG" ,"text :" +position);
                //Toast.makeText(context, position, Toast.LENGTH_SHORT).show();

                if(position == 0) {
                    Intent i = new Intent(context, MenuChoice.class);
                    context.startActivity(i);
                }
                else if(position==1){

                    Intent i = new Intent(context, Navigation.class);
                    context.startActivity(i);
                }
            }
        });

        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }





    private void bind(CardItem item, View view) {
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView timeremainText = (TextView) view.findViewById(R.id.timeRemainText);
        ImageView titleImageView =(ImageView) view.findViewById(R.id.iconImage);
        titleTextView.setText(item.getTitle());
        timeremainText.setText(item.getText());
        titleImageView.setImageResource(item.getmImageResource());
    }





}
