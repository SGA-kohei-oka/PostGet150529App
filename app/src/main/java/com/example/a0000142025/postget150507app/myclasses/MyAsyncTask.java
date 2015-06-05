package com.example.a0000142025.postget150507app.myclasses;

import android.os.AsyncTask;

import com.example.a0000142025.postget150507app.activities.ImageActivity;
import com.example.a0000142025.postget150507app.activities.MainActivity;

/**
 * オリジナルの非同期処理.
 */
public class MyAsyncTask extends AsyncTask<String, String, String> {


    private MyInterface myInterface;


    @Override       //別スレッドで行う処理
    protected String doInBackground(String...value) {

        String  arg1 = value[0]; //executeで渡されるHTTPメソッド名
        //String  arg2 = value[1];
        //String  arg3 = value[2];

        String result = "empty";
        result = myInterface.methods(arg1);

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
        myInterface.resultJob(result);
    }


}
