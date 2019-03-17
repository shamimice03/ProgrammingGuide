package com.ice.shamim.programmingguide.MainMenu.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ice.shamim.programmingguide.R;

public class CourseFragment extends Fragment {


    Context thiscontext;
    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thiscontext = container.getContext();
        Log.d("page", "onPageSelected: ");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }


}
