package com.wpes.worldofmedicine.drawerclasses;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wpes.worldofmedicine.R;

public class DrawerContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_contact);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        settingToolBar();

    }
    private void settingToolBar()  {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Contact Us");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setLogo(R.drawable.ic_action_navigation_arrow_back);
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolBar);
    }
}
