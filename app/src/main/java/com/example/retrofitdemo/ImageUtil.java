package com.example.retrofitdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.FutureTarget;

import java.util.concurrent.ExecutionException;

public class ImageUtil {
    public static void createRoundImage(Context context){
       // BitmapFactory.de
        FutureTarget<Bitmap> futureTarget =
                Glide.with(context)
                        .asBitmap()
                        .load("url")
                        .submit(50, 50);

        try {
            Bitmap bitmap = futureTarget.get();
           // bitmap.

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



       // BitmapFactory.decodeResource()
        // RoundedBitmapDrawable roundedBitmapDrawable1 = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.ns2));
    }
}
