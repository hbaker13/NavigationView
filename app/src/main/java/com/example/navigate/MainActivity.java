package com.example.navigate;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity{

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open,R.string.close);
        NavigationView nv = (NavigationView) findViewById(R.id.nav);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nv);
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void selectItemDrawer(MenuItem menuItem){
        Fragment myfragment = null;
        Class fragClass;
        switch (menuItem.getItemId()){

            case R.id.home:
                fragClass = Home.class;
                break;

            case R.id.basket:
                fragClass = Basketball.class;
                break;

            case R.id.soccer:
                fragClass = Soccer.class;
                break;

            case R.id.volley:
                fragClass = Volley.class;
                break;

                default:
                    fragClass = Home.class;

        }

        try {
            myfragment = (Fragment) fragClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.lin,myfragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerlayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectItemDrawer(item);
                return true;
            }
        });
    }
}
