package com.example.szidonialaszlo.screenlockproject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    TimerTask timerTask;

    Timer timer = new Timer();

    public static final int RESULT_ENABLE = 11;

    private Button btn;
    private TextView tw,twFinal;

    private int counter = 0;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);



        System.out.println("Settings ==> "+ Settings.System.SCREEN_OFF_TIMEOUT);
        tw = (TextView) findViewById(R.id.textView);
        twFinal =(TextView) findViewById(R.id.twFinal);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTimerTask();


            }
        });


    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        System.out.println(" -----------------------------------------> ");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_ENABLE){
            if (resultCode == RESULT_OK){
                Toast.makeText(MainActivity.this,"You have enabled the Admin Device feature",Toast.LENGTH_LONG).show();
            }else
            {
                Toast.makeText(MainActivity.this,"You have disabled the admin device feature",Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
    public void doTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        counter++;
                        tw.setText("Timer:" + counter);
                        Log.d("TIMER", "RUN");
                        System.out.println("Counter="+counter);
                        if (counter == 30)
                        {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            //reset


                        }


                    }

                });

            }
        };
        timer.schedule(timerTask, 1000, 1000);

    }




}
