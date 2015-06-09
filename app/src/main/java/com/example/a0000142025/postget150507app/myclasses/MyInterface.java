package com.example.a0000142025.postget150507app.myclasses;

/**
 * アクティビティの違いによる分岐をなくすためのインターフェイス.
 */
public interface MyInterface {

    /**
     * 別スレッドとして行う処理.
     * @param input 入力文字列
     * @return 出力文字列
     */
    String methods(String input);

    /**
     * 別スレッドの処理が終わった時に行う処理.
     * @param input 入力文字列
     */
    void resultJob(String input);

}
