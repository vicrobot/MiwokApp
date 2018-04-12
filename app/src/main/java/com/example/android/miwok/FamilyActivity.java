// package name...
package com.example.android.miwok;

// imported files or classes or interfaces or such things...
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.STREAM_MUSIC;

// class defining..
public class FamilyActivity extends AppCompatActivity {

    //Declaring MediaPlayer instance
    MediaPlayer mp;
    //Declaring the AudioManager Object
    AudioManager mAudioManager;
    //Declaring instance for nested interface OnAudioFocusChangeListener of Class AudioManager so
    // that we don't have to create it's object on every clicks.
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;


    //Making instance of nested interface OnCompletionListener of class MediaPlayer and initiating
    //it so that we could do task after the playback completion.
    private MediaPlayer.OnCompletionListener mpocl = new MediaPlayer.OnCompletionListener() {
        //Overriding its onCompletion method
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(FamilyActivity.this,"I'm Done",Toast.LENGTH_SHORT).show();
            //abandoning the MediaPlayer object after its completion
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    };

    // now overriding essential methods(here onCreate)...
    @Override
    protected void onCreate(Bundle var) {
        super.onCreate(var);
        setContentView(R.layout.main);
        // now making the text view with the help of java
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        //Initialising the word adapter with context, arrayList, and giving the color code
        // for its background
        WordAdapter wds = new WordAdapter(this,words,"#379237");
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView)findViewById(R.id.id2);
        fd.setAdapter(wds);
        //initializing the AudioManager class's object by giving it the context and the data of what
        //we need.
        mAudioManager = (AudioManager)FamilyActivity.this.getSystemService(Context.AUDIO_SERVICE);

        //setting onItemClickListener on ListView
        //It takes the argument of the adapterView.ONItemClickListener with its onItemClick method
        //overriden which has arguments of position of listView items, their optional id,
        fd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //releasing the MediaPlayer instance before playing audio
                releaseMediaPlayer();

                //As the item is clicked now thus we have to proceed firstly asking or requesting for
                //audioFocus from the android. For that we will use requestAudioFocus() method of the
                //AudioManage class; which takes argument of AudioManager.OnAudioFocusChangeListener,
                //the stream type of your sound that you will stream and the durationHint or say
                //the duration of AudioFocus you want for your audio.Now the requestAudioFocus method
                // will give the value of int type showing that whether our permission is granted or
                //denied by android.
                int var1 = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,STREAM_MUSIC,AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);
                if(var1==AUDIOFOCUS_REQUEST_GRANTED){
                    //we got the permission to play our audio
                    //Starting our usual practice to start playback

                    //getting the word object by the position argument that each view is taking with
                    Word cc = words.get(i);

                    //giving context and source to the MediaPlayer object
                    mp = MediaPlayer.create(FamilyActivity.this, cc.getAudioResourse());
                    mp.start();
                    //Setting OnCompletion method
                    mp.setOnCompletionListener(mpocl);
                }


            }
        });
        //OUTSIDE OF THE ONCLICK EVENT
        //Initializing the mAudioFocusChangeListener and overriding the method
        // onAudioFocusChange of AudioManager.OnAudioFocusChangeListener and then handling
        //all the cases of it(not all but all necessary and what we need.
        // Setting the listener to listen every case of the change in audioFocus and then
        //respond with that
        mOnAudioFocusChangeListener= new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int i) {
                if(i==AUDIOFOCUS_LOSS_TRANSIENT||i==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                    mp.pause();
                    //setting it to start because our pronunciation is already very small
                    mp.seekTo(0);
                }
                else if(i==AUDIOFOCUS_LOSS){
                    releaseMediaPlayer();
                }
                if(i==AUDIOFOCUS_GAIN_TRANSIENT||i==AUDIOFOCUS_GAIN){
                    mp.seekTo(0);
                    mp.start();
                }
            }
        };

    }

    //Defining the releaseMediaPlayer() method which deals properly with the release of the
    //mp.release() method.
    public void releaseMediaPlayer() {
        //check if something is present or associated with the MediaPlayer object and release it.
        //because we no longer want it.
        if (mp != null) {
            mp.release();
        }
        //even if the code skipped the if condition we have to initialize the object
        mp = null;


    }

    //Overriding the activity state changing methods
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

