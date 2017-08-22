package com.example.lamaftllocal.myapplication;

import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by LaMa.ftl(local) on 10.08.2017.
 */
public class CallActivity extends Service {

    //Ïåðåìåííàÿ îòâå÷àþùàÿ çà õðàíåíèå â ñåáå òåêóùåãî âðåìåíè (Ïî ñåðâåðó)
    private Integer dateTime = 0;

    public CallActivity() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

       /*  TODO Åñëè íå õîòèòå æäàòü, òî ìîæíî âåñü ïîòîê çàêîìåíòèòü è îñòàâèòü òîëüêî ýòî, èëè íàîáîðîò
       launchActivity(); */
        //Ñòàðò àêòèâíîñòè ïî âðåìåíè
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                //Datetime ïîêà ðàáîòàåò òîëüêî ïî ìèíóòà (èñïðàâèòü íà ÷àñû è ìèíóòû áóäåò ïðîñòî)
                //Ñòàâèòü ýòó ïåðåìåííóþ íàäî çà 1 ìèíóòó äî íóæíîãî âðåìåíè
                while (dateTime != 19) {
                    try {
                        //Áåðåì òåêóùåå âðåìÿ è ïðèðàâíèâàåì ê ïåðåìåííîé dateTime
                        SimpleDateFormat sdf = new SimpleDateFormat("mm");
                        long date = System.currentTimeMillis();
                        String dateString = sdf.format(date);
                        dateTime = dateTime.parseInt(dateString);
                        //Ïîâòîíàÿ ïðîâåðêà êàæäóþ ìèíóòó
                        Thread.sleep(60000);
                        Log.d("Minutes",dateTime.toString());
                        //Ëîâèì âîçìîæíûå îøèáêè
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Çàïóñêàåì ëîêñêðèí, åñëè óñëîâèå çàêîí÷èëîñü
                }launchActivity();
            }
        });
        thread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Çàïóñê Âòðîîé Àêòèâíîñòè. ãäå áóäåò ïðîèñõîäèòü îòñëåæèâàíèå íàæàòèÿ
    private void launchActivity (){
        Intent openActivityIntent = new Intent(CallActivity.this, ScreenActivitySecond.class);
        openActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openActivityIntent);
    }
}
