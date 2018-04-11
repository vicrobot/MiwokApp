// package name...
package com.example.android.miwok;

// imported files or classes or interfaces or such things...
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// class defining..
public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mp;
    //Creating a global variable for MediaPlayer.OnCompletionListener so that
    //we don't have to create it's object on every clicks.

    private MediaPlayer.OnCompletionListener mpocl = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(ColorsActivity.this,"Hello",Toast.LENGTH_SHORT).show();

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
        words.add(new Word("red", "weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black", "kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white", "kelelli",R.drawable.color_white,R.raw.color_white));




        WordAdapter wds = new WordAdapter(this,words,"#8800A0");
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView)findViewById(R.id.id2);
        fd.setAdapter(wds);
        fd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
                //getting the word object by the position argument that each view is taking with

                Word cc = words.get(i);
                mp = MediaPlayer.create(ColorsActivity.this, cc.getAudioResourse());
                mp.start();
                mp.setOnCompletionListener(mpocl);
            }
        });

    }
    public void releaseMediaPlayer() {

        //check if something is present or associated with the MediaPlayer object and release it.
        //because we no longer want it.
        if (mp != null) {
            mp.release();
        }

        //even if the code skipped the if condition we have to initialize the object
        mp = null;
    }
    @Override
    protected void onStop() {

        //releasing the media player object and setting it to null because we no longer need it.
        //You can implement the below method before or after the super call; it is same.
        releaseMediaPlayer();
        Log.v("Activity state: ","Stopped.");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
}

