package com.dshevchenkoo.gallery;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by dshevchenkoo on 30.04.18.
 */

public class ImageActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "ImageActivity.IMAGE";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = (ImageView) findViewById(R.id.image);
        Image image = getIntent().getParcelableExtra(EXTRA_IMAGE);

        Glide.with(this)
                .load(image.getUrl())
                .asBitmap()
                .listener(new RequestListener<String, Bitmap>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target,
                                               boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {

                        onPalette(Palette.from(resource).generate());
                        imageView.setImageBitmap(resource);

                        return false;
                    }

                    public void onPalette(Palette palette) {
                        if (null != palette) {
                            ViewGroup parent = (ViewGroup) imageView.getParent().getParent();
                            parent.setBackgroundColor(Color.BLACK);
                        }
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
