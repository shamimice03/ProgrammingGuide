package com.ice.shamim.programmingguide.MainMenu.Fragment.Course.LessonAndTest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Course.ProductAdapter;
import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.R;


public class LessonFragment extends Fragment {




    public WebView mWebView;
    public String fileName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);


        /**/
        int course = MainMenu.value;
        String title = ProductAdapter.title;
        //Toast.makeText(getActivity(), course + title, Toast.LENGTH_SHORT).show();

        /*Setting Which file will show*/
        setFile(course,title);
        mWebView = (WebView) view.findViewById(R.id.simpleWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/" + fileName);

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        return view;
    }

    private void setFile(int course, String title) {
        if(course==1){
            if(title=="Loop")
                fileName = "looptest.html";
        }


    }


}

/*    public class CallFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            setHasOptionsMenu(true);
            return inflater.inflate(R.layout.fragment_call, container, false);
        }


    }*/

