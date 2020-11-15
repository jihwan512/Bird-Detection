package com.test.birdcontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


//IntroActivity의 start()메소드를 통해 IntroThread의 run()메소드가 실행됨
//IntroThread의 sleep()메소드가 종료되면,
    //IntroThread의 sendEmptyMessage()메소드를 통해 handleMessage()메소드가 호출되어 message값을 전달받음
    //handleMessage()메소드 호출을 통해 전달받은 message값 검사
//전달받은 값이 1과 같다면 intend를 사용해 IntroActivity->MainActivity 화면 전환
public class IntroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); //xml, java 소스 연결

        IntroThread introThread = new IntroThread(handler);
        introThread.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        }
    };
}
