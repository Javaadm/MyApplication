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


public class Frag_rename extends Fragment {

    Button bRename;
    EditText name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_frag_rename, container, false);

        bRename = (Button)view.findViewById(R.id.button_rename);
        name = (EditText)view.findViewById(R.id.editText_rename);

        bRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_reg.man.rename(name.getText().toString());
                Toast.makeText(getContext(), "Name has been changed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
}
