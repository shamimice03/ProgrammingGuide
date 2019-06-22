package com.ice.shamim.programmingguide.MainMenu.Fragment.Event.APIs;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay.EventTestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizTestApi {

    String BASE_URL ="https://api.myjson.com/bins/";
    @GET("l53gx")
    Call<EventTestModel> getEventTestData();
}
