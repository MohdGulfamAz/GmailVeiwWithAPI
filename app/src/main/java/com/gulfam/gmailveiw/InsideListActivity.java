package com.gulfam.gmailveiw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import java.io.DataOutput;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsideListActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mTextView,mDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_list);

        mDescriptionView = findViewById(R.id.description_view);
        mImageView = findViewById(R.id.inside_image);
        mTextView = findViewById(R.id.textName);

        String name = getIntent().getStringExtra("titelName");
        mTextView.setText(name);

        String image = getIntent().getStringExtra("image");
        Picasso.get().load(image).into(mImageView);

        String description = getIntent().getStringExtra("description");
        mDescriptionView.setText(description);

    }}