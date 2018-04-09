package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

   public WordAdapter(Context ctx, ArrayList<Word> ark){
       super(ctx,0,ark);
   }
   @Override
    public View getView(int position,View nameOfTheView, ViewGroup vrg ){
       //now i am about to override the getView method and thus i will manually make a view
       // that would be returnable and thus will return it.
       ///////////
       //Asking for the item(i.e the object of word class that we gave in ArrayList
       //Now as we get it; we will access its field of text andthus will set it as i did
       // at the last.
        Word wrd = getItem(position);
       //firstly checking that the view is inflated or not
       if(nameOfTheView==null){
           LayoutInflater lf = LayoutInflater.from(getContext());
           //now what to inflate and the parentView and the attachment to the root view
           // and assigning the resultant view to the main view that was asked to inflate
           nameOfTheView=lf.inflate(R.layout.model,vrg,false);
       }

       //now to access the childViews of the inflated layout to populate the values
       TextView tx =  (TextView)nameOfTheView.findViewById(R.id.asdf);
       TextView txt =  (TextView)nameOfTheView.findViewById(R.id.asfd);
       ImageView img = (ImageView)nameOfTheView.findViewById(R.id.asfs);

       //getting access to the public fields of Word class
      //  tx.setText(wrd.mVar1);
      // txt.setText(wrd.mVar2);
      // img.setImageResource(wrd.mVar3);
       //but we can't let anyone access the fields directly thus we will assign getters on the word
       //class and then will access the resources by those methods.

       tx.setText(wrd.getFirstText());
       txt.setText(wrd.getFirstText());
       img.setImageResource(wrd.getImageResouse());





       return nameOfTheView;
   }
}
