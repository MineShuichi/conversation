package com.example.mine.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by mine on 2016/10/20.
 */

public class AsyncHttpRequest extends AsyncTask<Void, Void, Void> {

    private Activity mainActivity;

    public AsyncHttpRequest() {
        super();
    }

    // このメソッドは必ずオーバーライドする必要があるよ
    // ここが非同期で処理される部分みたいたぶん。
    @Override
    protected Void doInBackground(Void... params) {
        // httpリクエスト投げる処理を書く。
        // ちなみに私はHttpClientを使って書きましたー

        /*Handler mHandler = new Handler(Looper.getMainLooper());*/
        MessageRequests messReq = new MessageRequests();
        messReq.requestMessage();
        //System.out.println("非同期");
        return null;
    }

}
