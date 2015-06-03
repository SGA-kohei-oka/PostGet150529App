package com.example.a0000142025.postget150507app.jacksonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 0000142025 on 2015/05/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)   //使わないパラメータは無視
public class Photo {

    private String id;
    private String secret;
    private String server;
    private int farm;

//    String owner;
//    String title;
//    int ispublic;
//    int isfriend;
//    int isfamily;


    /**
     * Photoオブジェクトの内容から画像のURLを取得.
     * @return 画像のURL
     */
    public String getURL() {
        String photourl = "http://farm" + String.valueOf(farm) + ".staticflickr.com/"
                + server + "/" + id + "_" + secret + ".jpg";
        return photourl;
    }


}
