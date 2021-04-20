package com.gulfam.gmailveiw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ImageView mImageView;
    TextView mtitleName,mDescription;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gmail-view.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<GmailAPI>> call = jsonPlaceHolderAPI.getGamilApi();

        call.enqueue(new Callback<List<GmailAPI>>() {
            @Override
            public void onResponse(Call<List<GmailAPI>> call, Response<List<GmailAPI>> response) {
                if(response.code() != 200) {
                    Log.d("responseError","Code is : "+response.code());
                }

                List<GmailAPI> lists = response.body();
                final MailAdapter mailAdapter = new MailAdapter(lists,getApplicationContext());
                mRecyclerView.setAdapter(mailAdapter);
//                for(GmailAPI list :lists) {
//                    String res = "";
//                    res += list.getId();
//                    mtitleName.append(res);
//                }
            }

            @Override
            public void onFailure(Call<List<GmailAPI>> call, Throwable t) {
                Log.d("error","response Failed : "+t.getStackTrace());
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mImageView = findViewById(R.id.inside_image);
        mtitleName = findViewById(R.id.textName);
        mDescription = findViewById(R.id.description);




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


    }
}
