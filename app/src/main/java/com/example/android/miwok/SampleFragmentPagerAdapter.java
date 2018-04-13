/**
 * This is like WordAdapter and it gives Views to ViewPager
 */
package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    //announcing our constructor and then referring it to superclass
    //constructor does't have return type.
    //Also super() takes the input; what the extended class's constructor will take.
    //If the extended class has interfaces then we have to define them as i am doing here

    public SampleFragmentPagerAdapter(FragmentManager fm){
        super(fm); }



            @Override
            public int getCount() {
                //giving the count of total Fragments that have to associate with the Activity
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
              switch(position)  {
                  case 0:
                      //we have to return a Fragment or say an instance of Fragment class
                      //but as we have some childClasses which have extended Fragment and since we
                      //have to use them thus i will return their instances...
                      //if i put return new NumbersActivity(); then it will put error
                      //because it has non-matched imports. To match them we will set  the
                      //those imports com.example.android.miwok.NumbersActivity to
                      //import android.support.v4.app.Fragment;
                      return new NumbersFragment();
                  case 1:
                      return new FamilyFragment();
                  case 2:
                      return new ColorsFragment();
                  default:
                      return new PhrasesFragment();
              }
            }
    }


