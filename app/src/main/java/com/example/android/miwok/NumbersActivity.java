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
public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mp;
    //Creating a global variable for MediaPlayer.OnCompletionListener so that
    //we don't have to create it's object on every clicks.

    private MediaPlayer.OnCompletionListener mpocl = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(NumbersActivity.this,"Hello",Toast.LENGTH_SHORT).show();

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
        final ArrayList<Word> item = new ArrayList<>();
        item.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        item.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        item.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        item.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        item.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        item.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        item.add(new Word("seven", "kenekaku", R.drawable.number_two, R.raw.number_seven));
        item.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        item.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        item.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter wds = new WordAdapter(this, item, "#FD8E09");
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView) findViewById(R.id.id2);
        fd.setAdapter(wds);
        fd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
                //getting the word object by the position argument that each view is taking with

                Word cc = item.get(i);
                mp = MediaPlayer.create(NumbersActivity.this, cc.getAudioResourse());
                mp.start();
                mp.setOnCompletionListener(mpocl);
                Log.e("NumbersActivity", "Current word: " + cc.toString());
            }
        });
    }

    //To make everything clear from the MediaPlayer object we use release and null instantiation
    //for it.
    public void releaseMediaPlayer() {

        //check if something is present or associated with the MediaPlayer object and release it.
        //because we no longer want it.
        if (mp != null) {
            mp.release();
        }

        //even if the code skipped the if condition we have to initialize the object
        mp = null;
    }
}

