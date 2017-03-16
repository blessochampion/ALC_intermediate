package com.lagosdevelopers.lagdevs.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by blessochampion on 3/16/17.
 */

public class ImageUtils
{
    public static void loadImageInto(Context c, ImageView imageView, int placeHolder, String imagUrl)
    {
        Picasso.with(c).load(imagUrl).placeholder(placeHolder).fit().into(imageView);
    }
}
