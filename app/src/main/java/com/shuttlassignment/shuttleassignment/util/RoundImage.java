package com.shuttlassignment.shuttleassignment.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

import com.shuttlassignment.shuttleassignment.R;

/**
 * Created by Sameer Yadav on 11-10-2017.
 */

public class RoundImage {

    public static Bitmap roundCorner(Context context, Bitmap mbitmap)
    {

       // Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.ocky_poky_intro_bg)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 40, 40, mpaint);// Round Image Corner 100 100 100 100
        //placeHolder.feed_photo.setImageBitmap(imageRounded);
        return imageRounded;
    }


}
