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
    private String hoge = "hoge";

    /**
     * 非同期処理を開始.
     * @param object 対象のアクティビティ
     */
    public MyAsyncTask(Object object) {
        if (object.getClass().getSimpleName().equals("MainActivity")) {
            this.main = (MainActivity) object;
        } else if (object.getClass().getSimpleName().equals("ImageActivity")) {
            this.image = (ImageActivity) object;
            hoge = "Image done.";
        }
    }


//    public MyAsyncTask(ImageActivity ia) {
//        this.image = ia;
//    }

    @Override       //別スレッドで行う処理
    protected String doInBackground(String...value) {

        String  arg1 = value[0]; //executeで渡される"hoge"
        //String  arg2 = value[1]; //executeで渡される"foo"
        //String  arg3 = value[2]; //executeで渡される"bar"

        String result = "empty";

        if (hoge.equals("Image done.")) {
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
        if (hoge.equals("Image done.")) {
            image.resultJob();
        } else {
            main.resultJob(result);
        }


    }


}
