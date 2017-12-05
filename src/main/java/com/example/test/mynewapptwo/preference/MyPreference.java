package com.example.test.mynewapptwo.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.test.mynewapptwo.R;

/**
 * Created by 11942 on 2017/11/23.
 */

public class MyPreference extends PreferenceFragment {

    public SwitchPreference switchPreference;
    private  boolean daynight;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        switchPreference = (SwitchPreference) findPreference("daynight");

        sharedPreferences = getPreferenceManager().getSharedPreferences();
    }

}
