package com.ice.shamim.programmingguide.MainMenu.Fragment.Course;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ice.shamim.programmingguide.MainMenu.MainMenu;
import com.ice.shamim.programmingguide.R;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends Fragment {


    Context thiscontext;

    public CourseFragment() {
        // Required empty public constructor
    }

    List<Product> productList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thiscontext = container.getContext();
        Log.d("page", "onPageSelected: ");
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_course, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

     /*   ListAdapter listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);*/




        //adding some items to our list
        parse();

        ProductAdapter productAdapter = new ProductAdapter(thiscontext,productList);
        recyclerView.setAdapter(productAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);

        return view;

    }


    private void parse() {


        if(MainMenu.value==1) {

            productList = new ArrayList<>();
            //adding some items to our list
            productList.add(
                    new Product(
                            "Fundamental",
                            "C programming"
                    ));

            productList.add(
                    new Product(
                            "Data-type",
                            "C programming"
                    ));

            productList.add(
                    new Product(
                            "Variables",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Loop",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Array",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Operators",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Function",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Strings",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Enum, Struct and Union",
                            "C programming"
                    ));
            productList.add(
                    new Product(
                            "Pointers",
                            "C programming"
                    ));

        }
        else if(MainMenu.value==3){
            productList = new ArrayList<>();


            //adding some items to our list
            productList.add(
                    new Product(
                            "Fundamental",
                            "java"
                    ));

            productList.add(
                    new Product(
                            "Data-type",
                            "Java"
                    ));

            productList.add(
                    new Product(
                            "Variables",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Loop",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Array",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Operators",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Function",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Strings",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Enum, Struct and Union",
                            "Java"
                    ));
            productList.add(
                    new Product(
                            "Pointers",
                            "Java"
                    ));

        }

    }


}
