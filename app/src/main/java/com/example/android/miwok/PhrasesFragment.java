/**
 * I have deleted the PhrasesActivity and made PhrasesFragment.
 * For comparision i have those java files and content has
 * converted to comments for making them effect-less.
 * For converting an activity to fragment you can use  Fragment Transaction if
 * you want to use that Activity file to associate that fragment
 *     getSupportFragmentManager().beginTransaction()
 *     .replace(R.id.container, new PhrasesFragment())
 *     .commit();
 * By adding this statement where the R.id.container id the id of the parent View and
 * The name of the fragment  file; you can begin transaction of fragment.
 * But here i converted those Activity files to Fragment files which will be associate with
 * the MainActivity by the ViewPager and the FragmentPagerAdapter.
 * We will make a customFragmentPagerAdapter for our specific
 * output.
 * The MainActivity will associate these fragments with the FragmentPagerAdapter and when the
 * ViewPager will ask the custom-Adapter for the View to show; the custom-adapter will
 * ask the corresponding Fragment by its getItem() method to give a View by inflating the
 * main.xml or the layout that will become its content.
 * As for this version i have noticed that the Fragment get pause when its adjacent to adjacent
 * Fragment come to screen.
 * This version contains some bugs that will be fixed in future versions.
 * ViewPage~ListView
 * SampleFragmentPagerAdapter~WordAdapter
 * FragmentPagerAdapter~ArrayAdapter
 * getItem()~getView()
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class PhrasesFragment extends Fragment {

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
            //abandoning the MediaPlayer object after its completion
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    };

    public PhrasesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main,container,false);
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

        WordAdapter wds = new WordAdapter(getActivity(),words,"#16AFCA");
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView)rootView.findViewById(R.id.id2);
        fd.setAdapter(wds);
        //initializing the AudioManager class's object by giving it the context and the data of what
        //we need.
        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

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
                    mp = MediaPlayer.create(getActivity(), cc.getAudioResourse());
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
                    if(mp!=null){
                        mp.pause();
                    }
                }
                else if(i==AUDIOFOCUS_LOSS){
                    releaseMediaPlayer();
                }
                else if(i==AUDIOFOCUS_GAIN_TRANSIENT||i==AUDIOFOCUS_GAIN){
                    mp.seekTo(0);
                    mp.start();
                }
            }
        };
        return rootView;
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
    public void onStop() {
        //releasing the media player object and setting it to null because we no longer need it.
        //You can implement the below method before or after the super call; it is same.
        releaseMediaPlayer();
        Log.v("PhrasesFragment ","Stopped.");
        super.onStop();
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onResume(){

        super.onResume();
    }
    @Override
    public void onPause(){
        Log.v("PhrasesFragment ","Paused.");
        if(mp!=null){
            mp.pause();}
            releaseMediaPlayer();
        super.onPause();
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
