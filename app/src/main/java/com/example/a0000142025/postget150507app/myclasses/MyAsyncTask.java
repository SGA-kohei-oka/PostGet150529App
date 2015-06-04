package com.example.a0000142025.postget150507app.myclasses;

/**
 * Created by 0000142025 on 2015/05/07.
 */

import android.os.AsyncTask;

import com.example.a0000142025.postget150507app.activities.ImageActivity;
import com.example.a0000142025.postget150507app.activities.MainActivity;

/**
 * オリジナルの非同期処理.
 */
public class MyAsyncTask extends AsyncTask<String, String, String> {

    private MainActivity main;
    private ImageActivity image;
    private boolean imageFlag = false;

    /**
     * 非同期処理を開始.
     * @param nowActivity 対象のアクティビティ
     */
    public MyAsyncTask(Object nowActivity) {
        if (nowActivity instanceof MainActivity) {
            this.main = (MainActivity) nowActivity;
        } else if (nowActivity instanceof ImageActivity) {
            this.image = (ImageActivity) nowActivity;
            imageFlag = true;
        }
    }


    @Override       //別スレッドで行う処理
    protected String doInBackground(String...value) {

        String  arg1 = value[0]; //executeで渡されるHTTPメソッド名
        //String  arg2 = value[1];
        //String  arg3 = value[2];

        String result = "empty";

        if (imageFlag) {
            image.setBitmap();
        } else {
            result = main.methods(arg1);
        }

        return result;
    }

    /*
    @Override
    protected void onProgressUpdate(String... values) {
        //
    }
    */


    @Override       //最後にメインスレッドで行う処理
    protected void onPostExecute(String result) {
        //UIの描画
        if (imageFlag) {
            image.resultJob();
        } else {
            main.resultJob(result);
        }
    }


}
