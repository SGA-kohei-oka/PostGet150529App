package com.example.a0000142025.postget150507app.activities;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


//追加
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.util.Log;

import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.EditText;

import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;

import java.io.InputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.util.List;

import android.widget.GridView;


import android.widget.AdapterView.OnItemClickListener;


import com.example.a0000142025.postget150507app.jacksonclasses.Photo;
import com.example.a0000142025.postget150507app.myclasses.MyApplication;
import com.example.a0000142025.postget150507app.myclasses.MyArrayAdapter;
import com.example.a0000142025.postget150507app.myclasses.MyAsyncTask;
import com.example.a0000142025.postget150507app.R;
import com.example.a0000142025.postget150507app.jacksonclasses.FlickrPhotoInfo;
import com.example.a0000142025.postget150507app.myclasses.MyInterface;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 最初のアクティビティ.
 */
public class MainActivity extends Activity implements MyInterface {

    private static final String TAG = "PostGetApp";
    private ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    private MyArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getBtn = (Button) findViewById(R.id.Button001);
        Button postBtn = (Button) findViewById(R.id.Button002);
        Button deleteBtn = (Button) findViewById(R.id.Button003);
        Button flickrBtn = (Button) findViewById(R.id.Button004);


        //final AsyncJob asynctask = new AsyncJob(this);


        getBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // クリック時の処理
                //別スレッドで非同期処理
                MyAsyncTask asynctask = new MyAsyncTask();
                asynctask.execute("get");
            }
        });
        postBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MyAsyncTask asynctask = new MyAsyncTask();
                asynctask.execute("post");
            }
        });
        deleteBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MyAsyncTask asynctask = new MyAsyncTask();
                asynctask.execute("delete");
            }
        });
        flickrBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MyAsyncTask asynctask = new MyAsyncTask();
                asynctask.execute("flickr");
            }
        });
    }

    /**
     * 各HTTPメソッドに対応した処理.
     * @param method HTTPメソッド
     * @return 画面に表示する文字列
     */
    public String methods(String method) {
        String result = null;
        if (method.equals("post")) {
            //Post実行
            httpPost();
            result = "Post done.";
        } else if (method.equals("get")) {
            //Get実行　resに格納
            result = httpGet();
        } else if (method.equals("delete")) {
            //Delete実行
            httpDelete();
            result = "Delete done.";
        } else if (method.equals("flickr")) {
            //Flickr
            httpFlickr();
            result = "Flickr done.";
        }
        return result;
    }

    private void httpPost() {
        HttpPost post = new HttpPost("http://192.168.61.55:8080/test/database/");

        HttpClient client = new DefaultHttpClient();

        EditText codeTextBox = (EditText) findViewById(R.id.editText1);
        EditText nameTextBox = (EditText) findViewById(R.id.editText2);

        //クエリを設定
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("code", codeTextBox.getText().toString()));
        list.add(new BasicNameValuePair("name", nameTextBox.getText().toString()));

        //Postを実行
        try {
            post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            HttpResponse response = client.execute(post);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    //GETの処理
    private String httpGet() {
        //HttpGet get = new HttpGet("http://192.168.61.55:8080/test/database/?method=get");
        HttpGet get = new HttpGet("http://192.168.61.55:8080/test/database/");
        HttpClient client = new DefaultHttpClient();
        String str = "empty";
        try {
            HttpResponse response = client.execute(get);
            str = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return str;
    }

    //Deleteの処理
    private void httpDelete() {
        //EditText codeTextBox = (EditText)findViewById(R.id.editText1);
        EditText nameTextBox = (EditText) findViewById(R.id.editText2);
        String url2 = "http://192.168.61.55:8080/test/database/?name="
                + nameTextBox.getText().toString();
        HttpDelete delete = new HttpDelete(url2);
        HttpClient client = new DefaultHttpClient();


        //Deleteを実行
        try {
            HttpResponse response = client.execute(delete);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }



    //Flickrボタンの処理
    private void httpFlickr() {
        //フォームの入力値から取得する情報を決定
        EditText codeTextBox = (EditText) findViewById(R.id.editText1);
        EditText nameTextBox = (EditText) findViewById(R.id.editText2);
        String geturl = "https://api.flickr.com/services/rest/?method="
                + "flickr.photos.search&api_key=3b95b3a46552591aaa157ed0a41b2eeb&tags="
                + nameTextBox.getText().toString() + "&format=json&&nojsoncallback=1&per_page="
                + codeTextBox.getText().toString();
        HttpGet get = new HttpGet(geturl);
        HttpClient client = new DefaultHttpClient();
        String str = "empty";
        //画像検索
        try {
            HttpResponse response = client.execute(get);
            str = EntityUtils.toString(response.getEntity());
            //余計な文字　"jsonFlickrApi("　と　")"　の削除が必要
            //str = str.substring(14, str.length()-1);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        //Jsonによって画像を取得
        httpFlickrByJson(str);

        //Jacksonによって画像を取得
        //httpFlickrByJackson(str);
    }


    //flickrからJsonによって画像を取得.
    private void httpFlickrByJson(String str) {
        try {
            //JSONをパース
            JSONObject json = new JSONObject(str);
            JSONObject photos = json.getJSONObject("photos");
            JSONArray photoArray = photos.getJSONArray("photo");

            for (int i = 0; i < photoArray.length(); i++) {
                //次のphotoを選択
                JSONObject photo = photoArray.getJSONObject(i);
                //選択したphotoの各パラメータ取得
                String id = photo.getString("id");
                //String owner = photo0.getString("owner");
                int farm = photo.getInt("farm");
                String server = photo.getString("server");
                String secret = photo.getString("secret");

                //取得したパラメータで該当画像のURL生成
                String photourl = "http://farm" + String.valueOf(farm) + ".staticflickr.com/"
                        + server + "/" + id + "_" + secret + ".jpg";

                //生成URLから該当画像を取得しlistに追加
                Bitmap bmp = getBitmap(photourl);
                bitmapList.add(bmp);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        //GridViewに挿入するAdapterを用意
        adapter = new MyArrayAdapter(getApplicationContext(), R.layout.list_item, bitmapList);
    }


/*

    //flickrからJacksonによって画像を取得.
    private void httpFlickrByJackson(String str) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FlickrPhotoInfo flickrPhotoInfo = mapper.readValue(str, FlickrPhotoInfo.class);
            List<Photo> photoList =  flickrPhotoInfo.getPhotos().getPhotoList();
            for (Photo photo: photoList) {
                String photourl = photo.getURL();
                //生成URLから該当画像を取得しlistに追加
                Bitmap bmp = getBitmap(photourl);
                bitmapList.add(bmp);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        //GridViewに挿入するAdapterを用意
        adapter = new MyArrayAdapter(getApplicationContext(), R.layout.list_item, bitmapList);
    }

*/



    //取得する画像のURLから画像データを取得.
    private Bitmap getBitmap(String photourl) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            HttpGet getpic = new HttpGet(photourl);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(getpic);
            in = response.getEntity().getContent();
            bitmap = BitmapFactory.decodeStream(in);

        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            //必ずInputStreamを閉じる
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
        }
        return bitmap;
    }


    /**
     * onPostExecuteで実行される関数.
     * @param result 画面に表示する文字列
     */
    public void resultJob(String result) {
        //結果を描画(UIは要メインスレッド)
        TextView tv = (TextView) findViewById(R.id.textView001);
        tv.setText(result);

        //GridViewに描画
        GridView gridView = (GridView) findViewById(R.id.gridView001);
        gridView.setAdapter(adapter);


        //gridView.setOnItemClickListener(gridView.getOnItemClickListener());
        gridView.setOnItemClickListener(new OnItemClickListener() {
           // 項目がクリックされた時のハンドラ
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                // クリックされた時の処理を記述
                Intent intent = new Intent();
                intent.setClassName("com.example.a0000142025.postget150507app",
                        "com.example.a0000142025.postget150507app.activities.ImageActivity");

                //①URLを渡すとき
//                intent.putExtra("urlstring", String.valueOf(photoUrls[(int)id])) ;

                //②画像を渡すとき
//                MySerializable serializable = new MySerializable();
//                serializable.serializableBitmap = bitmapList.get((int) id);
//                intent.putExtra("serializablestring", serializable);

                //③Applicationクラスを経由して画像を渡すとき
                MyApplication app = (MyApplication) getApplication();
                //画像をApplicationクラスに保存
                app.setBitmapSaved(bitmapList.get((int) id));
                //ImageActivityに移動
                startActivity(intent);
            }
        });
    }








    //追加
    /*
    public List<NameValuePair> setJson (String objName, String... str) {
        JSONObject object = new JSONObject();
        try {
            for(int i = 1; i < str.length;i++){
                object.put("val_" + i, str[i]);
            }
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(objName, object.toString()));
            return params;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
    */






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
