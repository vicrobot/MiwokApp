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
public class ColorsActivity extends AppCompatActivity {

    // now overriding essential methods(here onCreate)...
    @Override
    protected void onCreate(Bundle var) {
        super.onCreate(var);
        setContentView(R.layout.main);

        // now making the text view with the help of java
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi",R.drawable.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow));
        words.add(new Word("green", "chokokki",R.drawable.color_green));
        words.add(new Word("brown", "ṭakaakki",R.drawable.color_brown));
        words.add(new Word("gray", "ṭopoppi",R.drawable.color_gray));
        words.add(new Word("black", "kululli",R.drawable.color_black));
        words.add(new Word("white", "kelelli",R.drawable.color_white));




        WordAdapter wds = new WordAdapter(this,words,"#8800A0");
        //i am now attaching the wordAdapter to the main adapterView  that will get its profits to me
        ListView fd = (ListView)findViewById(R.id.id2);
        fd.setAdapter(wds);

    }
}

