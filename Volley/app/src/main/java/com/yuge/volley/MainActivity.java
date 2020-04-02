package com.yuge.volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    SwipeRefreshLayout swipeRefreshLayout;
    String url1="https://pixabay.com/get/55e0d340485aa814f6da8c7dda2932761639dbe7524c704c7d2a7cd5914cc359_1280.jpg";
    String url2="https://pixabay.com/get/55e3d3464352b108f5d08460c62d3f7d133adce44e507441702879d69144c1_1280.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);//必须在前面啊     ！@！@！！！！！！！！！！
        super.onCreate(savedInstanceState);

        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        //final NetworkImageView imageView=findViewById(R.id.imageView);
        imageView=findViewById(R.id.imageView);

        RequestQueue queue= Volley.newRequestQueue(this);
//        ImageLoader imageLoader=new ImageLoader(queue, new ImageLoader.ImageCache() {
//            private LruCache<String,Bitmap> cache=new LruCache<>(50);
//            @Override
//            public Bitmap getBitmap(String url) {
//                return cache.get(url);
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//                cache.put(url,bitmap);
//            }
//        });

//        imageLoader.get(url, new ImageLoader.ImageListener() {
//            @Override
//            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//                imageView.setImageBitmap(response.getBitmap());
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("my", "onErrorResponse: "+error);
//            }
//        });

        //use NetworkImageView
        //imageView.setImageUrl(url,imageLoader);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadImage();
            }
        });

    }
    void loadImage(){
        Random random=new Random();
        String url=random.nextBoolean()?url1:url2;
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background).centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if(swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if(swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);
                        return false;
                    }
                })
                .into(imageView);
    }
}
