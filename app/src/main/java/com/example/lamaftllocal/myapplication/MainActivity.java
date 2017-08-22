package com.example.lamaftllocal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    EditText ed1,ed2;

    TextView tx1, tx2;
    int counter = 3;

    SharedPreferences sharedPreferences;
    private final String LOG_COUNTER="";

    Frag_reg frag_reg = new Frag_reg();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        b1 = (Button) findViewById(R.id.button_login);
        b2 = (Button)findViewById(R.id.button_reg);
        ed1 = (EditText) findViewById(R.id.editText_name);
        ed2 = (EditText) findViewById(R.id.editText_password);

        tx1 = (TextView) findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);
        tx2 = (TextView)findViewById(R.id.textView2);

        final Intent intent = new Intent(this, LoginActivity.class);

        sharedPreferences = getSharedPreferences(LOG_COUNTER, Context.MODE_APPEND);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();
        boolean login = sharedPreferences.getBoolean(LOG_COUNTER,false);

            if(login==true){
                startActivity(intent);
            }

       // if (Frag_reg.man==null) if(Frag_reg.man.getOut()) { //не трогаем man если out =false так уак он вышел
            ed1.setText("");
            ed2.setText("");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((!ed1.getText().toString().equals("") && !ed2.getText().toString().equals(""))&&((Frag_reg.man != null)
                            &&(Frag_reg.man.getPass().equals( ed2.getText().toString()) && Frag_reg.man.getLogin().equals( ed1.getText().toString())))) {


                                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                                editor.putBoolean(LOG_COUNTER, false);
                                editor.apply();
                                startActivity(intent);


                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                        System.out.println((!ed1.getText().toString().equals("") && !ed2.getText().toString().equals("")));
                        tx1.setVisibility(VISIBLE);
                        counter--;
                        tx1.setText(Integer.toString(counter));

                        if (counter == 0) {
                            b1.setEnabled(false);
                        }
                    }

                }
            });
        //}

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                frag_reg.setArguments(bundle);

                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                ed1.setVisibility(View.INVISIBLE);
                ed2.setVisibility(View.INVISIBLE);
                tx1.setVisibility(View.INVISIBLE);
                tx2.setVisibility(View.INVISIBLE);
b1.setClickable(false);
                b2.setClickable(false);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_login, frag_reg );
                fragmentTransaction.commit();
            }
        });


    }

}

