package com.example.a0000142025.postget150507app.jacksonclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Created by 0000142025 on 2015/05/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)   //�g��Ȃ��p�����[�^�͖���
public class Photos {

    private List<Photo> photo;
//    int page;
//    int pages;
//    int perpage;
//    String total;

    /**
     * Photo�I�u�W�F�N�g�̔z����擾���鏈��.
     * @return Photo�I�u�W�F�N�g��List
     */
    public  List<Photo> getPhotoList() {
        return photo;
    }

}
