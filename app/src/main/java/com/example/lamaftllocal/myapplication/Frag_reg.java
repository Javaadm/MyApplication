package com.example.lamaftllocal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//ЭТО ФРАГМЕНТ РЕГИСТРАЦИИ

public class Frag_reg extends Fragment {

    Button bReg;
    EditText edLog;
    EditText Pass;
    EditText Name;

    public static Man man;

    SharedPreferences sharedPreferences;
    //СТРОКА НАСТРОЕК ВХОДА
    private String LOG_COUNTER = "";
    Frag2 iv= new Frag2();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Intent intent = new Intent(Frag_reg.this.getActivity(), LoginActivity.class);


        View view = inflater.inflate(R.layout.fragment_frag_reg, container, false);
        //КНОПКА РЕГИСТРАЦИИ
        bReg = (Button)view.findViewById(R.id.button_reg);
        //ПОЛЕ ВВОДА ЛОГИНА
        edLog = (EditText)view.findViewById(R.id.editText_name);
        //ПОЛЕ ВВОДА ПАРОЛЯ
        Pass = (EditText)view.findViewById(R.id.editText_password);
        //ПОЛЕ ВВОДА  НИКНЕЙМА
        Name = (EditText)view.findViewById(R.id.editText_password1);

        //ПОЛУЧЕНИЕ ДОСТУПА К ВНУТРЕННИМ НАСТРОЙКАМ
        sharedPreferences = this.getActivity().getSharedPreferences(LOG_COUNTER, Context.MODE_APPEND);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();

        //ПРОВЕРКА ВХОДА ПОЛЬЗОВАТЕЛЯ
        boolean login = sharedPreferences.getBoolean(LOG_COUNTER,false);
        if(login==true){
            startActivity(intent);
        }


        //КНОПКА РЕГИСТРАЦИИ
        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Name.getText().toString().equals("") && !edLog.getText().toString().equals("")&&!Pass.getText().toString().equals("")) {

                    int id = (int) Math.random() * 12256;//id= getID()       (server)
                    man = new Man(Name.getText().toString(), Pass.getText().toString(), edLog.getText().toString(), id );
                    man.setOut(false);
                    editor.putBoolean(LOG_COUNTER, false);
                    editor.apply();

                    startActivity(intent);
                }
                else{

                }
            }
        });
        return view;
    }

//
}
