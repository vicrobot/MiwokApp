package com.example.android.miwok;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.parseColor;
import static java.lang.Integer.parseInt;

public class WordAdapter extends ArrayAdapter<Word> {
    MediaPlayer mp;
    private String color;

   public WordAdapter(Context ctx, ArrayList<Word> ark,String hex){
       super(ctx,0,ark);
       color=hex;

   }
   @Override
    public View getView(int position,View nameOfTheView, ViewGroup vrg ){
       //now i am about to override the getView method and thus i will manually make a view
       // that would be returnable and thus will return it.
       ///////////
       //Asking for the item(i.e the object of word class that we gave in ArrayList
       //Now as we get it; we will access its field of text andthus will set it as i did
       // at the last.
        final Word wrd = getItem(position);
       //firstly checking that the view is inflated or not
       if(nameOfTheView==null){
           LayoutInflater lf = LayoutInflater.from(getContext());
           //now what to inflate and the parentView and the attachment to the root view
           // and assigning the resultant view to the main view that was asked to inflate
           nameOfTheView=lf.inflate(R.layout.model,vrg,false);
       }

      //Find the comments and detail in the first commit of the app
       //initializing the text view and  then pointing them
       //I haven't did it for image because i have to make it go through conditional codes
       TextView tx =  (TextView)nameOfTheView.findViewById(R.id.asdf);
       TextView txt =  (TextView)nameOfTheView.findViewById(R.id.asfd);


       //I have made a method hasImage() which checks whether the class word's object have taken the
       //values for image or not and thus returns the boolean value for it indicating
       // that the particular object has imageView or not.
       //And thus if the image is available; do the general view declaration and initialisation
       //and pointing it.
       //And if the image Is not available then making the default image of the layout "model"
       // to be GONE by that method listed below.
       if(wrd.hasImage()) {
           ImageView img = (ImageView) nameOfTheView.findViewById(R.id.asfs);
           img.setImageResource(wrd.getImageResouse());
           img.setBackgroundColor(parseColor("#fff7da"));
       }else{
           ImageView img = (ImageView) nameOfTheView.findViewById(R.id.asfs);
           img.setVisibility(View.GONE);
       }

       //since i have already set the image for the needed one thus setting  the rest views
       tx.setText(wrd.getFirstText());
       txt.setText(wrd.getSecondText());
       LinearLayout ll = (LinearLayout)nameOfTheView.findViewById(R.id.color);
       ll.setBackgroundColor(parseColor(color));



       return nameOfTheView;
   }
}
