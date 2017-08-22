package com.example.lamaftllocal.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Frag_choose extends Fragment {

    Button name;
    Button pass;
    Button log;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_frag_choose, container, false);

        name = (Button)view.findViewById(R.id.button_na_name);
        pass = (Button)view.findViewById(R.id.button_na_pass);
        log = (Button)view.findViewById(R.id.button_na_log);

        final Bundle bundle = new Bundle();
        final Frag_repass frag_repass = new Frag_repass();
        final Frag_rename frag_rename = new Frag_rename();
        final Frag_relog frag_relog = new Frag_relog();

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag_rename.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, frag_rename)
                        .addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag_repass.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, frag_repass)
                        .addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag_relog.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, frag_relog)
                        .addToBackStack("");
                fragmentTransaction.commit();
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
