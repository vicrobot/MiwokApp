// package name...
package com.example.android.miwok;

// imported files or classes or interfaces or such things...
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// class defining..
public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mp;

    //Creating a global variable for MediaPlayer.OnCompletionListener so that
    //we don't have to create it's object on every clicks.

    private MediaPlayer.OnCompletionListener mpocl = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(PhrasesActivity.this,"Hello",Toast.LENGTH_SHORT).show();

            //releasing the MediaPlayer object after its completion
            releaseMediaPlayer();
        }
    };
    // now overriding essential methods(here onCreate)...
    @Override
    protected void onCreate(Bundle var) {
        super.onCreate(var);
        setContentView(R.layout.main);

        // now making the text view with the help of java
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here));

        WordAdapter wds = new WordAdapter(this,words,"#16AFCA");
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView)findViewById(R.id.id2);
        fd.setAdapter(wds);
        fd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the word object by the position argument that each view is taking with

                Word cc = words.get(i);
                mp = MediaPlayer.create(PhrasesActivity.this, cc.getAudioResourse());

                //setting OnCompletionListener on the mediaPlayer
                //the object of MediaPlayer.OnCompletionListener is  globally initiated for memory
                //efficiency.
                mp.setOnCompletionListener(mpocl);
                mp.start();
            }
        });

    }

    //To make everything clear from the MediaPlayer object we use release and null instantiation
    //for it.
    public void releaseMediaPlayer(){

        //check if something is present or associated with the MediaPlayer object and release it.
        //because we no longer want it.
        if(mp!=null){
            mp.release();
        }

        //even if the code skipped the if condition we have to initialize the object
        mp = null;
    }

}

