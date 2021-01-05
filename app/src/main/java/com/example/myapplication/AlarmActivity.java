package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity{

    Calendar calendar;
    SwipeButton swipeButton;
    TextView timeText;
    MediaPlayer mediaPlayer;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("alarm activity","created");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_execute);
        calendar=Calendar.getInstance();
        swipeButton=(SwipeButton)findViewById(R.id.swipe_btn);
        timeText=(TextView)findViewById(R.id.time);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                |WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                |WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                |WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

       /*new Thread(new Runnable(){//이게 왜 new thread에서 실행이 돼야하는거지 ㅠ
            @Override
                    public void run(){
                        while(flag==true){

                    //Toast.makeText(getApplicationContext(), "You clicked on the second item", Toast.LENGTH_SHORT).show();
                    ///////여기 못들어옴!!!!!!
                    try{
                        calendar=Calendar.getInstance();
                        if(calendar.get(Calendar.HOUR_OF_DAY)>0&&calendar.get(Calendar.HOUR_OF_DAY)<12) {
                            timeText.setText("AM" + calendar.get(Calendar.HOUR_OF_DAY) + "시" + calendar.get(Calendar.MINUTE) + "분" + calendar.get(Calendar.SECOND) + "초");
                        }else if(calendar.get(Calendar.HOUR_OF_DAY)==12){
                            timeText.setText("PM"+calendar.get(Calendar.HOUR_OF_DAY) + "시" + calendar.get(Calendar.MINUTE) + "분" + calendar.get(Calendar.SECOND) + "초");
                        }else if(calendar.get(Calendar.HOUR_OF_DAY)==12) {
                            timeText.setText("PM"+(calendar.get(Calendar.HOUR_OF_DAY)-12) + "시" + calendar.get(Calendar.MINUTE) + "분" + calendar.get(Calendar.SECOND) + "초");
                        }else if(calendar.get(Calendar.HOUR_OF_DAY)==0) {
                            timeText.setText("AM 0시"+ calendar.get(Calendar.MINUTE) + "분" + calendar.get(Calendar.SECOND) + "초");
                        }
                        Thread.sleep(1000);
                    }catch(InterruptedException ex){}
                }
        }

        }).start();*/
        //swipe button 밀어서 해제
        swipeButton.setOnStateChangeListener(new OnStateChangeListener(){
            @Override
            public void onStateChange(boolean active){
                Toast.makeText(getApplicationContext(),"종료하였습니다.", Toast.LENGTH_LONG).show();
                mediaPlayer.stop();
                flag=false;
                finish();
            }
        });
    }
}
