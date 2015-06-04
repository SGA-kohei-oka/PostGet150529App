package com.example.a0000142025.postget150507app.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.a0000142025.postget150507app.myclasses.MyApplication;
import com.example.a0000142025.postget150507app.myclasses.MyAsyncTask;
import com.example.a0000142025.postget150507app.R;


/**
 * 画像を表示するアクティビティ.
 * */
public class ImageActivity extends Activity {

    private Bitmap displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final ImageActivity ia = this;

        //別スレッドで非同期処理
        MyAsyncTask asynctask = new MyAsyncTask(ia);
        asynctask.execute("image");
    }

    /**
     * 保存している画像を呼び出す.
     * */
    public void setBitmap() {

        //①URLを受け取るとき
//        Intent intent = getIntent();
//        String photourl = intent.getStringExtra("urlstring");
//        displayImage = getBitmap(photourl);
//        String result = photourl;

        //②画像を受け取るとき
//        MySerializable serializable = (MySerializable)getIntent()
//              .getSerializableExtra("serializablestring");
//        displayImage = serializable.serializableBitmap;

        //③Applicationクラスを経由して画像を受け取るとき
        MyApplication app = (MyApplication) getApplication();
        displayImage = app.getBitmapSaved();
    }



    /**
     * 画像を表示する.
     * */
    public void resultJob() {
        //画像表示
        ImageView iv = (ImageView) findViewById(R.id.imageView10);
        iv.setImageBitmap(displayImage);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
