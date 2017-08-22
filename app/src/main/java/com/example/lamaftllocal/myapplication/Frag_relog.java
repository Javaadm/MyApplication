package com.example.lamaftllocal.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Frag_relog extends Fragment {

    Button relog;
    EditText log;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_relog, container, false);

        relog = (Button)view.findViewById(R.id.button_relog);
        log=(EditText)view.findViewById(R.id.editText_relog);

        relog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_reg.man.relog(log.getText().toString());
                Toast.makeText(getContext(), "Login has been changed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        throw new RuntimeException(context.toString()
//                + " must implement OnFragmentInteractionListener");
//    }
}