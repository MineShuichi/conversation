package com.example.mine.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private String reqText = "";
    private Map<String, Object> context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AsyncHttpRequest task = new AsyncHttpRequest(this);
        task.execute("");
    }

    public void onSpeechBtn(View view){
        //speech();
        AsyncHttpRequest task = new AsyncHttpRequest(this);
        task.execute("てすと");
    }

    private void speech(){

        try{
            // 音声認識プロンプト立ち上げ
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

            // プロンプトに表示する文字
            intent.putExtra(
                    RecognizerIntent.EXTRA_PROMPT,
                    "話てください");

            startActivityForResult(intent, REQUEST_CODE);
        }catch (ActivityNotFoundException e){
            Toast.makeText(MainActivity.this,
                    "ActivityNotFoundException", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode,Intent data){
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            ArrayList<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String resultsString = "";
            /*for (int i = 0; i < results.size(); i++){
                resultsString += results.get(i)+";";
            }*/
            resultsString = results.get(0);

            //Toast.makeText(this, resultsString, Toast.LENGTH_LONG).show();
            TextView tv = (TextView)findViewById(R.id.text);
            tv.setText(resultsString);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setContext(Map<String, Object> context){
        this.context = context;
    }

    public Map<String, Object> getContext(){
        return this.context;
    }

}
