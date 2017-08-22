package com.example.lamaftllocal.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Frag2 extends Fragment  {
    ImageView iv;
    ImageView ivLAMA;
    TextView textView4 ;
    TextView textView;
    TextView textView5;
    TextView textView6;

    //СТРОКИ НАСТРОЕК
    protected final static String TAG= "TAG";
    private static final String APP_PREFERENCES = "";
    private static final String FONTS = "";
    private boolean fonts;
    private String s;

    SharedPreferences sharedPreferences;
    public TextView nickname;

    //ПОЛУЧЕНИЕ РЕЗУЛЬТАТА РАБОТЫ GetFilePathFromDevice
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null){
            if(Frag_reg.man.getImage()==null){
                String realPath = GetFilePathFromDevice.getPath(Frag2.this.getContext(), data.getData());
                iv.setImageURI(Uri.parse(realPath));
            }
            else {
                iv.setImageBitmap(Frag_reg.man.getImage());
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag2, container, false);


        iv = (ImageView) view.findViewById(R.id.image);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);

            }
        });

        //ДОПИСАТЬ СЕРЕЖЕ
        //iv.setImageBitmap( Frag_reg.man.getImage());


        //ТЕКСТВЬЮ ЛЕВЕЛА
        textView = (TextView) view.findViewById(R.id.textView);
        s = "Level: " + String.valueOf(Frag_reg.man.getChar().getLevel());
        textView.setText(s);
//        textView.setText(Frag_reg.man.char.level);


        //ТЕКСТВЬЮ НИКНЕЙМА
        nickname = (TextView) view.findViewById(R.id.nickname);
        nickname.setText(Frag_reg.man.getName());

        textView4 = (TextView) view.findViewById(R.id.textView4);
        s = "Health: " + String.valueOf(Frag_reg.man.getChar().getHealth());
        textView4.setText(s);

        textView5 = (TextView) view.findViewById(R.id.textView5);
        s = "Damage: " + String.valueOf(Frag_reg.man.getChar().getDamage());
        textView5.setText(s);

        textView6 = (TextView) view.findViewById(R.id.textView6);
        s = "Armor: " + String.valueOf(Frag_reg.man.getChar().getArmor());
        textView6.setText(s);

        if(Frag_reg.man.getImage()!=((BitmapDrawable)iv.getDrawable()).getBitmap()){
            Frag_reg.man.choseFoto(((BitmapDrawable)iv.getDrawable()).getBitmap());
        }


        return view;

    }
    @Override
    public void onResume () {
        sharedPreferences = getActivity().getSharedPreferences(FONTS, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(FONTS)) Log.d("Key", "fonts's here");

        fonts = sharedPreferences.getBoolean(FONTS, false);
        Log.d("fonts", fonts + "");
        if (fonts) nickname.setTextColor(Color.BLUE);
        else nickname.setTextColor(Color.BLACK);
        super.onResume();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }

}