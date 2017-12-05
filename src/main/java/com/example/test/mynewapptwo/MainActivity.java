package com.example.test.mynewapptwo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.VisualVoicemailService;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.test.mynewapptwo.adapter.RecycleAdapter;
import com.example.test.mynewapptwo.fragment.CountryFragment;
import com.example.test.mynewapptwo.fragment.InternationalFragment;
import com.example.test.mynewapptwo.fragment.SocialFragment;
import com.example.test.mynewapptwo.fragment.TouTiaoFragment;
import com.example.test.mynewapptwo.fragment.TupianFragment;
import com.example.test.mynewapptwo.adapter.PagerAdapter;
import com.example.test.mynewapptwo.preference.MyShowPreference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Fragment> fragmentList;
    private List<String> titeList;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        initToolbar();
        initData();


        String cachePaths = getApplicationContext().getDatabasePath("okgo.db").getAbsolutePath();
        File file = new File(cachePaths);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        };
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            float size = (float) fileInputStream.getChannel().size();
            float v = size / (float) 1024;
            Log.v("缓存文件大小为",v+"kb");
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),fragmentList,titeList));
        tabLayout.setupWithViewPager(viewPager);
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        dayandnightthem();
        super.onStart();

    }

    /**
     * 日夜主题切换  （未完成...）
     */
    private void dayandnightthem() {
        //        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean daynight = preferences.getBoolean("daynight", false);
        if (daynight){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Log.v("再次启动111","");
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Log.v("再次启动111","");
        }
    }

    @Override
    protected void onRestart() {
        dayandnightthem();
        super.onRestart();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0){
            int grantResult = grantResults[0];
            Log.v("权限结果",grantResult+"");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initData() {
        titeList = new ArrayList<>();
        titeList.add("头条");
//        titeList.add("社会");
//        titeList.add("国内");
//        titeList.add("国际");
//        titeList.add("图片");


        TouTiaoFragment touTiaoFragment = new TouTiaoFragment();
//        CountryFragment countryFragment = new CountryFragment();
//        InternationalFragment internationalFragment = new InternationalFragment();
//        SocialFragment socialFragment = new SocialFragment();
//        TupianFragment tupianFragment = new TupianFragment();


        fragmentList = new ArrayList<>();
        fragmentList.add(touTiaoFragment);
//        fragmentList.add(countryFragment);
//        fragmentList.add(internationalFragment);
//        fragmentList.add(socialFragment);
//        fragmentList.add(tupianFragment);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("news news");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        } else if (id == R.id.system_tool){
            Toast.makeText(MainActivity.this,"系统设置被点击",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, MyShowPreference.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
