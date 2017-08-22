package com.example.lamaftllocal.myapplication;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class ServiceTimer extends Service {

    //Ïåðåìåííàÿ õðàíÿùàÿ âðåìÿ òàéìåðà
    private Integer stCounter;
    //Ïåðåìåííàÿ îòâå÷àþùàÿ çà ðàáîòó ïîòîêà
    private boolean work = false;
    //Ñîçäàåì ýêçåìïëÿð ñêðèí àêòèâèòè
    ScreenActivity sa;
    //Ñîçäàåì ïðåôåððåíñ
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNT = "count";

    SharedPreferences mSettings;
    //Ñîçäàåì ïîòîêè
    Thread run,run2;

    public ServiceTimer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //Èíèöèàëèçèðóåì
        sa = new ScreenActivity();
        //Ïåðåìåííàÿ îòâå÷àþùàÿ çà ðàáîòó òàéìåðà
        work = true;
        //Ïðîâåðÿåì åñòü ëè ïðåôåðåíññ
        if (mSettings.contains(APP_PREFERENCES_COUNT)) {
            stCounter = mSettings.getInt(APP_PREFERENCES_COUNT, 0);
            run = new Thread(new Runnable() {

                @Override
                public void run() {
                    //Ïîêà work == true ó íàñ áóäåò ïðîäîëæàòüñÿ öèêë
                    while (work) {
                        try {
                            //Îòïðàâëÿåì ëîêñêðèíó çíà÷åíèå ñ÷åò÷èêà
                            stCounter++;
                            Intent intent = new Intent("MinutesMSG");
                            intent.putExtra("Minutes", stCounter);
                            sendBroadcast(intent);
                            //Åñëè òàéìåð îñòàíîâèòñÿ ïîñëåäíèå äàííûå ñîõðàíÿþòñÿ
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNT, stCounter);
                            editor.apply();
                            //Ñ÷èòàåì êàæäóþ ìèíóòó
                            Thread.sleep(1000);
                            Log.d("Timerïðôïô", stCounter.toString());
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            });
            run.start();
        }else{
            //Òîæå ñàìîå, ÷òî è â run, òîëüêî çàïóñêàåòñÿ â ïåðâûé ðàç
            run2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    //Ïîêà work == true ó íàñ áóäåò ïðîäîëæàòüñÿ öèêë
                    while (work) {
                        try {
                            stCounter++;
                            Intent intent = new Intent("MinutesMSG");
                            intent.putExtra("Minutes", stCounter);
                            sendBroadcast(intent);
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt(APP_PREFERENCES_COUNT, stCounter);
                            editor.apply();
                            //Ñ÷èòàåì êàæäóþ ìèíóòó
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            });
        }
        return Service.START_STICKY;
    }
    //Ìåòîä îáíóëÿþùèé ïåðåìåííóþ òàéìåðà
    public void stCounterToZero (){
        stCounter = 0;
        //Ñîõðàíÿåì
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNT, stCounter);
        editor.apply();
    }

    public Integer getStCounter() {
        return stCounter;
    }

    public void setStCounter(Integer stCounter) {
        this.stCounter = stCounter;
    }

    //Ïîñëå óíè÷òîæåíèÿ ñåðâèñà çíà÷åíèå work ìåíÿåòñÿ íà false
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Îñòàíàâëèâàåì òàéìåð
        work = false;
        //Îáíóëÿåì çíà÷åíèå
        stCounterToZero();


    }
}
