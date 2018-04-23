package com.dshevchenkoo.gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Created by dshevchenkoo on 18.04.18.
 */

public class ListViewActivity extends AppCompatActivity{

    public static String[] myImages = {
            "https://pp.userapi.com/c846019/v846019735/2c5bd/aXvvLbY7pxs.jpg",
            "https://pp.userapi.com/c847123/v847123093/2d5cd/IvnNff0Y4Bc.jpg",
            "https://pp.userapi.com/c840237/v840237603/4d0fd/nrmMZc5-dEI.jpg",
            "https://sun9-1.userapi.com/c840438/v840438611/1736a/Xet4wGVeGok.jpg",
            "https://sun9-1.userapi.com/c840530/v840530497/109b7/ETc56hiafkw.jpg",
            "https://pp.userapi.com/c639716/v639716389/69294/mRkbYO3l16g.jpg",
    };

    private Context context = ListViewActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_listview);

        ListView listView = (ListView) findViewById(R.id.usage_listview);
        listView.setAdapter(
                new SimpleImageListAdapter(
                        ListViewActivity.this,
                        myImages
                )
        );
    }
}
