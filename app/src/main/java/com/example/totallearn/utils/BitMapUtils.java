package com.example.totallearn.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Shinelon on 2019/3/17.
 */

public class BitMapUtils {
    public BitMapUtils(){

    }

    public static Bitmap decodeBitmap(Resources resources,int resid,int reqwith ,int reqHeight){
        //对位图进行解码参数的设置
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只加载边界,避免申请内存空间
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(resources,resid,options);
        options.inSampleSize = getSimpleSize(options,reqwith,reqHeight);

        //真正的输出位图
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resid,options);
    }

    public static int getSimpleSize(BitmapFactory.Options options,int reqwith ,int reqHeight){
        //获得图片的原始的宽高
        int imagewidth = options.outWidth;
        int imageHeight = options.outHeight;
        //判断谁的小

        return 0;//时间关系 ，后面操作
    }

    public static Bitmap decodeBitmap22222(Resources resources,int resid,int reqwith ,int reqHeight){
        //获取解码参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        //加载边界，为true
        options.inJustDecodeBounds = true;
        //加载bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(resources,resid,options);
        options.inSampleSize = 2;
        //加载边界,false
        options.inJustDecodeBounds = false;


        bitmap = BitmapFactory.decodeResource(resources,resid,options);
        return BitmapFactory.decodeResource(resources,resid,options);
    }
}
