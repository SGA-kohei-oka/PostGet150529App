package com.example.a0000142025.postget150507app.jacksonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Created by 0000142025 on 2015/05/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)   //使わないパラメータは無視
public class Photos {

    private List<Photo> photo;
//    int page;
//    int pages;
//    int perpage;
//    String total;

    /**
     * Photoオブジェクトの配列を取得する処理.
     * @return PhotoオブジェクトのList
     */
    public  List<Photo> getPhotoList() {
        return photo;
    }

}
