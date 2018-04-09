// package name...
package com.example.android.miwok;

// imported files or classes or interfaces or such things...
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

// class defining..
public class FamilyActivity extends AppCompatActivity {

    // now overriding essential methods(here onCreate)...
    @Override
    protected void onCreate(Bundle var) {
        super.onCreate(var);
        setContentView(R.layout.main);

        // now making the text view with the help of java
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә",R.drawable.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister));
        words.add(new Word("grandmother ", "ama",R.drawable.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather));

        WordAdapter wds = new WordAdapter(this,words);
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView)findViewById(R.id.id2);
        fd.setAdapter(wds);

    }
}

