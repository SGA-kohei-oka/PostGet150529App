
package com.example.a0000142025.postget150507app.myclasses;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by 0000142025 on 2015/05/15.
 */


public class MyApplication extends Application {

    private static final String TAG = "PostGetApp";
    private Bitmap bitmapSaved;

    @Override
    public void onCreate() {
        //Application作成時
        Log.v(TAG, "--- onCreate() in ---");
    }

    @Override
    public void onTerminate() {
        //Application終了時
        Log.v(TAG, "--- onTerminate() in ---");
    }

    /**
     * 押下した画像を保存.
     * @param bmp 押下した画像データ
     * */
    public void setBitmapSaved(Bitmap bmp) {
        bitmapSaved = bmp;
    }

    /**
     * 保存している画像を呼び出す.
     * @return 保存している画像データ
     */
    public Bitmap getBitmapSaved() {
        return bitmapSaved;
    }

    /**
     * 保存している画像データを消去.
     */
    public void clearObj() {
        bitmapSaved = null;
    }
}