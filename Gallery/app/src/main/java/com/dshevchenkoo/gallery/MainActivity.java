package com.dshevchenkoo.gallery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1;
    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.standart_list_imageview1);
        loadImageByInternetUrl();
    }

    private void loadImageByInternetUrl(){
        String internetUrl = "https://s1.1zoom.ru/big3/207/373785-svetik.jpg";
        Glide
                .with(context)
                .load(internetUrl)
                .into(imageView1);
    }
}
