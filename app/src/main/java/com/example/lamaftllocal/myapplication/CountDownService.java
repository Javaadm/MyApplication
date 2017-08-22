package com.example.lamaftllocal.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by LaMa.ftl(local) on 20.08.2017.
 */

public class CountDownService extends Service {
    //Ñîçäàåì ýêçåìïëÿð òàéìåðà îáðàòíîãî îòñ÷åòà
    private CountDownTimer countDownTimer;
    //Áóäåì ïîòîì ïîëó÷àòü ñ ñåðâåðà äàííûå î âðåìåíè ñêîëüêî ïîëüçîâàòåëü áóäåò â îôôëàéíå è ïðèñâàèâàåì
    private Integer downMinutes;
    //Ïîêà ïóñòü ïðîñòî ñóùåñòâóåò
    public boolean stopFromOutside = true;
    //Îòñûëàåì ìèíóòû
    private double part;
    //Îòñûëàåì ñåêóíäû
    private Integer part2;
    //Ïåðåìåííàÿ êîòîðàÿ íóæíà äëÿ ïðîâåðîê ïðåôåðåíñîâ
    private String cMinutes;

    //Ñàìè ïðåôåðåíñû
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_MINUTES = "min";

    SharedPreferences mSettings;

    public CountDownService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //Íà÷èíàåì îáðàòíûé îòñ÷åò
        startCountDown();

        return super.onStartCommand(intent, flags, startId);
    }

    //Òàéìåð îáðàòíîãî îòñ÷åòà, ïî îêîí÷àíèþ êîòîðîãî áóäåò çàêðûâàòüñÿ äàííàÿ àêòèâíîñòü
    private void startCountDown(){
        //Ïðîâåðÿåì åñòü ëè ïðåôåðåíñ
        if(mSettings.contains(APP_PREFERENCES_MINUTES)){
            //Ïðèñâàèâàåì long ê cMinutes
            cMinutes = String.valueOf(mSettings.getLong(APP_PREFERENCES_MINUTES, 0));
            //Ïðîâåðÿåì, ÷òîáû òàéìåð ïðè ïîâòîðíîì çàïóñêå íå áûë < 1
            if (Integer.parseInt(cMinutes) / 1000 > 1) {
                //Ïðèñâàèâåì ïåðåìåííîé îñòàøååñÿ âðåìÿ
                downMinutes = Integer.parseInt(cMinutes);
                //Èíà÷å ïðîñòî ïðèñâàèâàåì äåôîëòíîå çíà÷åíèå
            }else{downMinutes = 40000;}
            //Èíèöèàëèçèðóåì îáðàíûé îòñ÷åò
            countDownTimer = new CountDownTimer(downMinutes, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //Ñîçäàåì èíòåíòû è îòïðàâëÿåì â ëîê ñêðèí è òàì ïîêàçûâàåì ýòè äàííûå
                    Intent intent = new Intent("CountDownMinutesMSG");
                    String pars = String.valueOf(millisUntilFinished / 1000 / 60);
                    part = millisUntilFinished % 60000;
                    part2 = (int)part / 1000;
                    String pars2 = String.valueOf(part2);
                    intent.putExtra("CountDownMinutes", pars);
                    intent.putExtra("CountDownMinutes2", pars2);
                    sendBroadcast(intent);
                    sendBroadcast(intent);
                    Log.d("Pars",pars);
                    //Ñîõðàíÿåì ïîñëåäíèå äàííûå
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putLong(APP_PREFERENCES_MINUTES, millisUntilFinished);
                    editor.apply();
                }
                @Override
                //Êîãäà òàéìåð îñòàíîâèòñÿ îòïðàâëÿåì èíòåíò î òîì, ÷òî ðàáîòà êîìïëèò
                public void onFinish() {
                    Intent intent = new Intent("BooleanWorkMSG");
                    intent.putExtra("BooleanWork",false);
                    sendBroadcast(intent);
                    //Ïîêà îñòàâëÿåì òàê
                    stopFromOutside = false;
                    //Îñòàíàâëèâàåì ñåðâèñ
                    stopSelf();
                }
            }.start();

        }else {
            //Âñå òîæå ñàìîå, òîëüêî åñëè íå áóäåò ïðåôåðåíñ ñòàâèì äåôîëòíîå çíà÷åíèå
            downMinutes = 40000;

            countDownTimer = new CountDownTimer(downMinutes, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Intent intent = new Intent("CountDownMinutesMSG");
                /* TODO  Äîðàáîòàòü ïîêàç âðåìåíè â ðåàë òàéìå */
                    String pars = String.valueOf(millisUntilFinished / 1000 / 60);
                    part = millisUntilFinished % 60000;
                    part2 = (int)part / 1000;
                    String pars2 = String.valueOf(part2);
                    intent.putExtra("CountDownMinutes", pars);
                    intent.putExtra("CountDownMinutes2", pars2);
                    sendBroadcast(intent);
                    sendBroadcast(intent);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putLong(APP_PREFERENCES_MINUTES, millisUntilFinished);
                    editor.apply();
                }
                @Override

                public void onFinish() {
                    Intent intent = new Intent("BooleanWorkMSG");
                    intent.putExtra("BooleanWork",false);
                    sendBroadcast(intent);
                    stopFromOutside = false;
                    stopSelf();

                }
            }.start();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        /* TODO Ñäåëàòü ïîòîì */
//        if (stopFromOutside){
//            ScreenActivity sa = new ScreenActivity();
//            sa.stopFromOutsideCount();
//            Toast.makeText(CountDownService.this, "Ïîëó÷è permament",Toast.LENGTH_SHORT).show();
//        }
    }
}
