package com.example.mine.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by mine on 2016/10/20.
 */

public class AsyncHttpRequest extends AsyncTask<String, String, String> {

    private MainActivity mainActivity;
    private MessageRequests messReq;
    private TextView textView;
    private Map<String, Object> context;

    public AsyncHttpRequest(MainActivity mainActivity) {

        super();
        this.mainActivity = mainActivity;
        messReq = new MessageRequests();
        textView = (TextView)mainActivity.findViewById(R.id.text);
        context = null;
    }

    // このメソッドは必ずオーバーライドする必要があるよ
    // ここが非同期で処理される部分みたいたぶん。
    @Override
    protected String doInBackground(String... params) {

        context = mainActivity.getContext();
        messReq.setResContext(context);
        messReq.requestMessage(params[0]);
        return messReq.getStringOutPut();
    }

    @Override
    protected void onPostExecute(String str){
        mainActivity.setReqText(str);
        context = messReq.getResContext();
        mainActivity.setContext(context);
        textView.setText(str);
        mainActivity.resToSpeak();
    }
}
