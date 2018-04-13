/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


//I had extended this activity with FragmentActivity which result in no ActionBar
//then i replaced it with the correct one that is AppCompatActivity.
public class MainActivity extends AppCompatActivity {

    //Initializing the TabLayout variable.
    TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setting the layout for the MainActivity
        setContentView(R.layout.activity_main);

        //Setting the ViewPager with the android.support.v4.view.ViewPager view
        ViewPager vp = (ViewPager) findViewById(R.id.viewP);

        //Declaring and initialising the SampleFragmentPagerAdapter and
        //giving it the argument as getSupportFragmentManager() which will get the FragmentManager
        //to manage the fragments associated with the adapter and that have to show to this Activity.
        SampleFragmentPagerAdapter sp =
                new SampleFragmentPagerAdapter(getSupportFragmentManager());

        //setting the adapter with the ViewPager
        vp.setAdapter(sp);

        //Referring the TabLayout to the layout's one.
        tl = (TabLayout)findViewById(R.id.tabL);

        //I here set that with the ViewPager of the layout. By doing this
        // i have fixed that on every fragment change, the title of the fragment that is
        // inside of TabLayout will change accordingly.
        //You can visit the superclasses for more detail.
        tl.setupWithViewPager(vp);

        //To set the color of the barLine
        tl.setSelectedTabIndicatorColor(getResources().getColor(R.color.BarLine));

        tl.setSelectedTabIndicatorHeight(10);

        ColorStateList csl = tl.getTabTextColors();

        Log.e("TabColors",csl.toString());




    }



    }





