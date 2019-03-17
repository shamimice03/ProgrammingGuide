package com.ice.shamim.programmingguide.MainMenu.Fragment.Event;

import android.graphics.drawable.Drawable;

public class CardItem {

    private String mTextResource;
    private int mTitleResource;
    int  mImageResource;



    public CardItem(int title, String text, int image) {
        mTitleResource = title;
        mTextResource = text;
        mImageResource = image;
    }


    public String getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public int getmImageResource() {
        return mImageResource;
    }
}
