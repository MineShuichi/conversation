package com.example.mine.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, TextToSpeech.OnInitListener{

    private static final int REQUEST_CODE = 0;
    private String reqText = "";
    private Map<String, Object> context;
    private Button speechBtn;
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speechBtn = (Button)findViewById(R.id.push);
        speechBtn.setOnClickListener(this);
        tts = new TextToSpeech(this, this);

        startRequest("");
    }

    public void onSpeechBtn(View view){speech();}

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
            startRequest(resultsString);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setContext(Map<String, Object> context){
        this.context = context;
    }

    public Map<String, Object> getContext(){
        return this.context;
    }

    public void startRequest(String text){
        AsyncHttpRequest task = new AsyncHttpRequest(this);
        task.execute(text);
    }

    @SuppressWarnings("deprecation")
    public void resToSpeak(){
        String toSpeak = getReqText();
        if(tts.isSpeaking()){
            tts.stop();
        }
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status){
        if(status == TextToSpeech.SUCCESS){
            float pitch = 1.0f; //音の高低
            float rate = 1.0f;  //話すスピード

            tts.setPitch(pitch);
            tts.setSpeechRate(rate);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(tts != null){
            tts.shutdown();
        }
    }

    @Override
    public void onClick(View v){
        try{
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

            // 「お話しください」の画面で表示される文字列
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "音声認識中です");

            // 音声入力開始
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e){
            // 非対応の場合
            Toast.makeText(this, "音声入力に非対応です。", Toast.LENGTH_LONG).show();
        }
    }

    public void setReqText(String reqText){
        this.reqText = reqText;
    }

    public String getReqText(){
        return this.reqText;
    }

}
