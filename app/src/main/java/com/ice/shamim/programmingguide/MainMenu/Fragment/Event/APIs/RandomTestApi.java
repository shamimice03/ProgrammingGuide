package com.ice.shamim.programmingguide.MainMenu.Fragment.Event.APIs;

import com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay.EventTestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomTestApi {
    String BASE_URL ="https://api.myjson.com/bins/";
    @GET("u4qpd")
    Call<EventTestModel> getEventTestData();
}
