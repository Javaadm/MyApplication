package com.example.lamaftllocal.myapplication;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.CountDownTimer;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 1 on 01.08.2017.
 */

public class ScreenActivitySecond extends AppCompatActivity {


    //Íàïèñàòü èíñòðóêöèþ
    private TextView instruction;
    //Ïîêàçûâàåò òàéìåð
    public TextView viewTime;
    //Ðåàëèçîâàòü êíîïêó âûçîâà
    private ImageButton call;
    //Ïåðåìåííàÿ õðàíÿùÿÿ âðåìÿ òàéìåðà
    private Integer mCounter = 0;
    //Ñîçäàåì ýêçåìïëÿð BroadcastReceiver
    private BroadcastReceiver brServerTimeMinutes,brCountDownService,brBooleanWork;
    //Õç ÷òî çà ïåðåìåííàÿ
    public boolean onWork = true;
    //Ìèíóòû â ñ÷åò÷èêå
    private String countMinutes;
    //Ñåêóíäû â ñ÷åò÷èêå
    private String countMinutes2;
    //Ïîêà ÷òî íå ðåàëèçîâàíà êàê íàäî
    public boolean inLife = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen);

        //Ïðèâÿçûâàåì ïåðåìåííûå
        instruction = (TextView) findViewById(R.id.instruction);
        viewTime = (TextView) findViewById(R.id.viewTime);
        call = (ImageButton) findViewById(R.id.call);

        //Çàïóñêàåì ñåðâèñ îáðàòíîãî îòñ÷åòà
        serviceCountStart();

        //Ïîñëå âîçâðàòà â àêòèâíîñòü èäåò ïðîâåðêà : ñóùåñòâóåò ëè ñåðâèñ, åñëè äà, òî ñåðâèñ îñòàíàâëèâàåòñÿ
        if (isMyServiceRunning(ServiceTimer.class)) {
            Intent intent = new Intent(ScreenActivitySecond.this, ServiceTimer.class);
            stopService(intent);
            instruction.setText("");
            Toast.makeText(this, "Òàéìåð îñòàíîâëåí", Toast.LENGTH_SHORT).show();
        }

        //Ïðîñòî âêëþ÷àåì ìåòîäû ñ ðåñèâåðàìè
        brServerTimeMinutesM();
        brCountDownServiceM();
        brBooleanWorkM();
    }

    //Îñòëåæèâàíèå íàæàòèÿ êíîïêè "Íàçàä" è çàïóñê òàéìåðà
    public void onBackPressed() {
        //Ïðîâåðÿåì çàïóùåí ëè ñåðâèñ, åñëè íåò, òî çàïóñêàåì, åñëè äà, òî íè÷åãî
        if (!isMyServiceRunning(ServiceTimer.class)){
            serviceStart();
            Toast.makeText(this, "Çàïóñê òàéìåðà", Toast.LENGTH_SHORT).show();
        }
    }

    //    Îñòëåæèâàíèå íàæàòèÿ êíîïêè "Äîì" è çàïóñê òàéìåðà
    public void onUserLeaveHint() {
        //Ïðîâåðÿåì çàïóùåí ëè ñåðâèñ, åñëè íåò, òî çàïóñêàåì, åñëè äà, òî íè÷åãî
        if (!isMyServiceRunning(ServiceTimer.class)){
            serviceStart();
            Toast.makeText(this, "Çàïóñê òàéìåðà", Toast.LENGTH_SHORT).show();
        }
    }
    //Ñîçäàåì èíòåíò äëÿ çàïóñêà ñåðâèñà
    private void serviceStart() {
        Intent intent = new Intent(ScreenActivitySecond.this, ServiceTimer.class);
        startService(intent);
    }
    //Îñòàíîâêà ñåðâèñà ñ îáðàíûì îòñ÷åòîì
    private void serviceCountStart() {
        Intent intent = new Intent(ScreenActivitySecond.this, CountDownService.class);
        startService(intent);
    }
    //Îñòàíîâêà ñåðâèñà ñ òàéìåðîì
    private void serviceStop() {
        Intent intent = new Intent(ScreenActivitySecond.this, ServiceTimer.class);
        stopService(intent);
    }
    //Ìåòîä êîòîðûé âêëþ÷àåòñÿ êîãäà ðåñèâåð brBooleanWork ïîëó÷àåò ñîîáùåíèå è îñòàíàâëèâàåò ñåðâèñ è àêòèâèòè
    public void finishActivity() {
        inLife = false;
        serviceStop();
        finish();
    }

    //Ìåòîä ïðîâåðÿþùèé ðàáîòàåò ëè ñåðâèñ èëè íåò, íà âõîä èäåò íàçâàíèÿ ñåðâèñà
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void brServerTimeMinutesM (){
        //Ïîëó÷àåì ñîîáùåíèå îò ñåðâèñà ñîõðàíÿåì çíà÷åíèå è ïîêàçûâàåì â viewTime
        brServerTimeMinutes = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                mCounter = intent.getIntExtra("Minutes",0);
                viewTime.setText(mCounter.toString());
                //Îòïðàâëÿåì â ìýéíàêòèâèòè äàííûå î òàéìåðå
                LoginActivity sa = new LoginActivity();
                sa.sumMinutesMSG(mCounter);
                Log.d("broaaaaaaaad",mCounter.toString());
            }
        };
        //Ñîçäàåì ôèëüòð äëÿ BroadcastReceiver
        IntentFilter intFilt = new IntentFilter("MinutesMSG");
        // Ðåãèñòðèðóåì (âêëþ÷àåì) BroadcastReceiver
        registerReceiver(brServerTimeMinutes, intFilt);
    }

    private void brCountDownServiceM () {
        //Ïîëó÷àåì è ñòàâèì îáðàòíûé îòñ÷åò
        brCountDownService = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                countMinutes = intent.getStringExtra("CountDownMinutes");
                countMinutes2 = intent.getStringExtra("CountDownMinutes2");
                instruction.setText(countMinutes + ":" + countMinutes2);
            }
        };
        //Ñîçäàåì ôèëüòð äëÿ BroadcastReceiver
        IntentFilter intFilt2 = new IntentFilter("CountDownMinutesMSG");
        // Ðåãèñòðèðóåì (âêëþ÷àåì) BroadcastReceiver
        registerReceiver(brCountDownService, intFilt2);
    }

    private void brBooleanWorkM (){
        //Ïðîâåðÿåì ðàáîòàåò ëè ñåðâèñ countDown è çàâåðøàåì ðàáîòó
        brBooleanWork = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onWork = intent.getBooleanExtra("BooleanWork",false);
                finishActivity();
            }
        };
        //Ñîçäàåì ôèëüòð äëÿ BroadcastReceiver
        IntentFilter intFilt3 = new IntentFilter("BooleanWorkMSG");
        // Ðåãèñòðèðóåì (âêëþ÷àåì) BroadcastReceiver
        registerReceiver(brBooleanWork,intFilt3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}