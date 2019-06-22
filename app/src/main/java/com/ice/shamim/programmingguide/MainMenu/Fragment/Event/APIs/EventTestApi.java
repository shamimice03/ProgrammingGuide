package com.ice.shamim.programmingguide.MainMenu.Fragment.Event.APIs;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay.EventTestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EventTestApi {

    String BASE_URL ="https://api.myjson.com/bins/";
   /* @GET("fs70x")
    Call<EventTestModel> getEventTestData();*/
   @GET
   Call<EventTestModel> getValue(@Url String url);
}

