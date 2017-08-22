package com.example.lamaftllocal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;


/**
 * Created by LaMa.ftl(local) on 13.07.2017.
 */

public class PrefActivity extends PreferenceActivity {

    private static final String APP_PREFERENCES = "";
    private static final String FONTS = "";
    private boolean fonts;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

//        final CheckBoxPreference chb = (CheckBoxPreference)findPreference("chb");


//        chb.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//
//                sharedPreferences = getSharedPreferences(FONTS, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                editor.putBoolean(FONTS ,chb.isChecked());
//                editor.apply();
//
//                if (sharedPreferences.contains(FONTS)) Log.d("FONTS", "fonts's here");
//                Log.d("pref fonts", chb.isChecked()+"");
//                return false;
//
//            }
//        });




    }
}
