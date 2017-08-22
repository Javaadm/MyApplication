package com.example.lamaftllocal.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    //Ïåðåìåííàÿ êîòîðàÿ áóäåò ïîëó÷àòü îáùåå (ñóììèðîâàííîå) âðåìÿ èç âòîðîé àêòèâíîñòè
    private Integer counter = 0;
    //Ñóììèðîâàíîå âðåìÿ òàéìåðà
    private Integer counter2 = 0;
    //Ïîòîì ðåàëèçóåì
    private boolean mStopFromOutsideCount,mStopFromOutsideTimer;

    private final String LOG_COUNTER="";

//    public void onOpenFrag1(String string){
//        frag1.setArguments(bundle);
//        bundle.putString(Frag1.TAG,string);
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.content,frag1);
//        fragmentTransaction.commit();
//    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.b1: {
                    frag1.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, frag1);
                    // .addToBackStack(Frag1.TAG);
                    fragmentTransaction.commit();

                    return true;
                }
                case R.id.b2: {
                    frag2.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, frag2);
                    //.addToBackStack(Frag2.TAG);
                    fragmentTransaction.commit();

                    return true;
                }
                case R.id.b3: {
                    frag3.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, frag3);
                    //.addToBackStack(Frag3.TAG);
                    fragmentTransaction.commit();

                    return true;
                }
            }
            return false;
        }

    };

    //Ìåòîä çàïóñêà ñåðâàèñà
    private void serviceStart (){
        Intent intent = new Intent(LoginActivity.this,CallActivity.class);
        startService(intent);
    }
    //Ïîòîì
    public boolean stopFromOutsideCount(){
        mStopFromOutsideCount = true;
        return mStopFromOutsideCount;
    }
    //Ïîòîì
    public boolean stopFromOutsideTimer(){
        mStopFromOutsideTimer = true;
        return mStopFromOutsideTimer;
    }
    //Ìåòîä, êîòîðûé ïîëó÷àåò âðåìÿ
    public int sumMinutesMSG (int a){
        counter = a;
        counter2 = counter + counter2;
        Log.d("smhahaha",counter2.toString());
        return 0;
    }
    //Ïðîâåðêà ñóùåñòâóåò èëè íåò ñåðâèñ
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private Frag1 frag1 = new Frag1();
    private Frag2 frag2 = new Frag2();
    private Frag3 frag3 = new Frag3();

    Bundle bundle = new Bundle();

    private class Log_Counter{
        String log_counter="";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//Ïðîâåðêà, ÷òîáû ñåðâèñ íå çàïóñêàëñÿ êàæäûé ðàç, êîãäà âêëþ÷àåòñÿ ïðèëîæåíèå
        if (!isMyServiceRunning(CallActivity.class)) {
            serviceStart();
        }
        //Ïðîâåðêà, åñëè òðó, òî çàïóñêàåì ëîêñðèí
        if (isMyServiceRunning(CountDownService.class)) {
            Intent intent = new Intent(LoginActivity.this, ScreenActivitySecond.class);
            startActivity(intent);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, frag1);
        fragmentTransaction.commit();



    }



    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }




    @Override
    protected void onResume() {

        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem set = menu.add(0, 1, 0, "Settings");
        MenuItem choose = menu.add(0,1,0,"Choose");
        final MenuItem logout = menu.add(0, 1, 0, "Logout");


        set.setIntent(new Intent(this, PrefActivity.class));

        final Intent log_intent = new Intent(this, MainActivity.class);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Frag_reg.man.out=false;
                SharedPreferences sharedPreferences = getSharedPreferences(LOG_COUNTER, MODE_APPEND);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LOG_COUNTER, false);
                editor.apply();
                startActivity(log_intent);
                return false;
            }
        });

        choose.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Frag_choose frag_choose = new Frag_choose();
                frag_choose.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content,frag_choose)
                        .addToBackStack("");
                fragmentTransaction.commit();

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}

