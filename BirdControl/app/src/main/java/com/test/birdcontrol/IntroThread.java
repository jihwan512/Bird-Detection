package com.test.birdcontrol;
import android.os.Handler;
import android.os.Message;

public class IntroThread extends Thread {

    private Handler handler;

    //IntroThread에 Thread.sleep()메소드가 종료되었을 때
    // 메세지를 전달받기 위해 handler 전달받기
    public IntroThread(Handler handler){
        this.handler = handler;
    }

    //Thread.sleep() 메소드 사용 및 종료되었을 때
    // handler를 통해 message 전달받음
    @Override
    public void run() {
        Message msg = new Message();

        try{
            Thread.sleep(3000); //3초 뒤 로딩화면 종료
            msg.what = 1;
            handler.sendEmptyMessage(msg.what);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
