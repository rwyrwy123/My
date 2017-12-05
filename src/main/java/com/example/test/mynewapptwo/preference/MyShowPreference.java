package com.example.test.mynewapptwo.preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.test.mynewapptwo.MainActivity;
import com.example.test.mynewapptwo.R;
import com.example.test.mynewapptwo.daynight.ColorChangePresenter;
import com.example.test.mynewapptwo.daynight.ColorChangePresenterImpl;

public class MyShowPreference extends AppCompatActivity implements ColorChangePresenter {

    private  Toolbar toolbar;
    private ColorChangePresenterImpl colorChangePresenter;
    private MyPreference myPreference;
    private SharedPreferences defaultSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_show_preference);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("news news");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        colorChangePresenter = new ColorChangePresenterImpl(MyShowPreference.this);
        myPreference = new MyPreference();
        getFragmentManager().beginTransaction().replace(R.id.show_preference,myPreference).commit();
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (defaultSharedPreferences.getBoolean("daynight",false)){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


//        if (daynight == false){
//            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//            getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO
//                    ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
//            // 同样需要调用recreate方法使之生效
//            recreate();
//        }
    }

    @Override
    protected void onResume() {
        myPreference.switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (defaultSharedPreferences.getBoolean("daynight",false)){
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
                return true;
            }
        });
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(MyShowPreference.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeColor() {
        colorChangePresenter.changeColor();
    }
}
