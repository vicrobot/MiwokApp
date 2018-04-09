package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter1 extends ArrayAdapter<Word> {

    public WordAdapter1 (Context ctx, ArrayList<Word> srs){
       super(ctx,0,srs);
   }

   @Override
    public View getView(int position, View convertOfView, ViewGroup parent){
        View rrc = convertOfView;
        if(rrc==null){
            LayoutInflater lf = LayoutInflater.from(getContext());
            rrc  = lf.inflate(R.layout.modal1,parent,false);
            }
            TextView txt = (TextView)rrc.findViewById(R.id.qw1);
            TextView tx = (TextView)rrc.findViewById(R.id.qw2);
            txt.setText(getItem(position).getFirstText());
            tx.setText(getItem(position).getSecondText());




       return rrc;
   }
}
