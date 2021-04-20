package com.gulfam.gmailveiw;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("list-data")
    Call<List<GmailAPI>> getGamilApi();
}
