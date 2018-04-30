package com.dshevchenkoo.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by dshevchenkoo on 30.04.18.
 */

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        GalleryActivity.ImageGalleryAdapter adapter = new GalleryActivity.ImageGalleryAdapter(
                this, Image.getImages());
        recyclerView.setAdapter(adapter);
    }

    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {

        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the layout
            View imageView = inflater.inflate(R.layout.item_image, parent, false);

            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(imageView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            Image image = images[position];
            ImageView imageView = holder.imageView;

            Glide.with(context)
                    .load(image.getUrl())
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (images.length);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView imageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.iv_image);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    Image image = images[position];

                    Intent intent = new Intent(context, ImageActivity.class);
                    intent.putExtra(ImageActivity.EXTRA_IMAGE, image);
                    startActivity(intent);
                }
            }
        }

        private Image[] images;
        private Context context;

        public ImageGalleryAdapter(Context context, Image[] images) {
            this.context = context;
            this.images = images;
        }
    }
}
