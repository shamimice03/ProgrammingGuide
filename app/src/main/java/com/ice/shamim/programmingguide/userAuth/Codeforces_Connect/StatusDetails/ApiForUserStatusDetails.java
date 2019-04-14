package com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.StatusDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiForUserStatusDetails {

    String BASE_URL = "https://codeforces.com/api/";

    @GET
    Call<UserSubmissionDetails> getValue(@Url String url); ///for dynamic access.
}
