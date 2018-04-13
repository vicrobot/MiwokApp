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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the layout for the MainActivity
        setContentView(R.layout.activity_main);

        //Setting the ViewPager with the android.support.v4.view.ViewPager view
        ViewPager vp = (ViewPager)findViewById(R.id.viewP);
        //Declaring and initialising the SampleFragmentPagerAdapter and
        //giving it the argument as getSupportFragmentManager() which will get the FragmentManager
        //to manage the fragments associated with the adapter and that have to show to this Activity.
        SampleFragmentPagerAdapter sp = new SampleFragmentPagerAdapter( getSupportFragmentManager());
        //setting the adapter with the ViewPager
        vp.setAdapter(sp);

    }



}
