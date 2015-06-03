package com.example.a0000142025.postget150507app.jacksonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 0000142025 on 2015/05/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)   //�g��Ȃ��p�����[�^�͖���
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
     * Photo�I�u�W�F�N�g�̓��e����摜��URL���擾.
     * @return �摜��URL
     */
    public String getURL() {
        String photourl = "http://farm" + String.valueOf(farm) + ".staticflickr.com/"
                + server + "/" + id + "_" + secret + ".jpg";
        return photourl;
    }


}
