package com.example.a0000142025.postget150507app.jacksonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by 0000142025 on 2015/05/15.
 */


@JsonIgnoreProperties(ignoreUnknown = true)   //使わないパラメータは無視
public class FlickrPhotoInfo {

    private Photos photos;
//    String stat;

    /**
     * Photosオブジェクトを取得する処理.
     * @return Photosオブジェクト
     */
    public  Photos getPhotos() {
        Photos hoge = photos;
        return hoge;
    }
}
