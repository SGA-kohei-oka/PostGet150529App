package com.example.a0000142025.postget150507app.myclasses;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.a0000142025.postget150507app.activities.ImageActivity;
import com.example.a0000142025.postget150507app.activities.MainActivity;

/**
 * オリジナルの非同期処理.
 */
public class MyAsyncTask extends AsyncTask<String, String, String> {

    private MyInterface myInterface;
    private static final String TAG = "PostGetApp";

    /**
     * MyAsyncTaskを呼び出すアクティビティを特定するコンストラクタ.
     * @param callbackActivity MyAsyncTaskを呼び出すアクティビティ
     */
    public MyAsyncTask(MyInterface callbackActivity) {
        myInterface = callbackActivity;
    }

    @Override       //別スレッドで行う処理
    protected String doInBackground(String...value) {

        String  input = value[0]; //executeで渡されるHTTPメソッド名
        //String  arg2 = value[1];
        //String  arg3 = value[2];

        String result = "empty";
        result = myInterface.methods(input);

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
