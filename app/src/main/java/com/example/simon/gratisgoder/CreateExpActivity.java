package com.example.simon.gratisgoder;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class CreateExpActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createexp);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    com.example.simon.gratisgoder.MapsFragment tab1 = new com.example.simon.gratisgoder.MapsFragment();
                    return tab1;
                case 1:
                    ListFragment fragment = new ListFragment();
                    Log.i("JJJ", "Laver jeg new");
                    ListFragment tab2 = fragment;
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}

