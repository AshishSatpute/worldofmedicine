package com.wpes.worldofmedicine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.TextView;

import com.wpes.worldofmedicine.activities.Login;
import com.wpes.worldofmedicine.drawerclasses.DrawerAbout;
import com.wpes.worldofmedicine.drawerclasses.DrawerCategories;
import com.wpes.worldofmedicine.drawerclasses.DrawerContact;
import com.wpes.worldofmedicine.drawerclasses.DrawerMediPoly;
import com.wpes.worldofmedicine.drawerclasses.DrawerOrder;
import com.wpes.worldofmedicine.drawerclasses.DrawerPrivacy;
import com.wpes.worldofmedicine.fragments.Cart;
import com.wpes.worldofmedicine.fragments.Categories;
import com.wpes.worldofmedicine.fragments.Home;
import com.wpes.worldofmedicine.fragments.Wishlist;
import com.wpes.worldofmedicine.model.User;
import com.wpes.worldofmedicine.sessionmanager.SharedPrefManager;
import com.wpes.worldofmedicine.utils.UrlLinks;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = MainActivity.class.getCanonicalName();
    Context context;
    public static int notificationCountCart = 0;
    SharedPrefManager prefManager;
    TextView adminname,adminemail;
    BottomNavigationView bottomNavigationView;

    static int value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        prefManager = new SharedPrefManager(getApplicationContext());

        if (prefManager != null) {
            //getting the current user
            User user = SharedPrefManager.getInstance(this).getUser();
            Log.d(TAG, "onCreate: " + user);
        }
        context = MainActivity.this;
        Log.i(TAG, "onCreate: ");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();

        bottomNavigationView = findViewById(R.id.navigation);
        final BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View view = bottomNavigationMenuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) view;


        View badge = LayoutInflater.from(this).inflate(R.layout.notification_badge, bottomNavigationMenuView, false);

        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null && savedInstanceState.getString("from").contains("ItemDetails")) {
            replaceFragment(new Cart());
            bottomNavigationView.setSelectedItemId(R.id.navigation_cart);
        }

        // TODO: 04-12-2018 Add Badget function

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value1 = extras.getInt("added");
            if (value1 < value) {
                ++value;
                TextView text = badge.findViewById(R.id.notifications);
                text.setVisibility(View.VISIBLE);
                Log.i(TAG, "onCreate:value " + value);
                text.setText("" + value);
            }
        }
        itemView.addView(badge);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        View header = navigationView.getHeaderView(0);
        adminemail=header.findViewById(R.id.navEmail);
        adminname=header.findViewById(R.id.navAdmin);
        User user = SharedPrefManager.getInstance(this).getUser();
        adminname.setText(user.getName());
        adminemail.setText(user.getEmail());

        if (savedInstanceState == null) {
            Log.i(TAG, "onCreate: savedInstanceState");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home()).commit();
        }


        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null && savedInstanceState.getString("from").contains("ItemDetails")) {
            replaceFragment(new Cart());
            bottomNavigationView.setSelectedItemId(R.id.navigation);
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment SelectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Log.i(TAG, "onNavigationItemSelected: chick home");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Home()).commit();
                        break;

                    case R.id.nav_cat:
                        startActivity(new Intent(MainActivity.this, DrawerCategories.class));
                        break;

                    case R.id.navigation_wishlist:
                        Log.i(TAG, "onNavigationItemSelected: chick wishlist");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Wishlist()).commit();
                        break;
                    case R.id.navigation_cart:
                        Log.i(TAG, "onNavigationItemSelected: chick cart");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Cart()).commit();
                        break;
                }
                return true;
            }
        });


    }
  @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home()).commit();
        } else if (id == R.id.nav_cat) {
            startActivity(new Intent(MainActivity.this, DrawerCategories.class));
        } else if (id == R.id.nav_med) {
            startActivity(new Intent(MainActivity.this, DrawerMediPoly.class));
        } else if (id == R.id.nav_privacy) {
            startActivity(new Intent(MainActivity.this, DrawerPrivacy.class));
        } else if (id == R.id.nav_order) {
            startActivity(new Intent(MainActivity.this, DrawerOrder.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, DrawerAbout.class));
        } else if (id == R.id.nav_contact) {
            startActivity(new Intent(MainActivity.this,DrawerContact.class));

    } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi there! Please check out Medicine and More application at:play store");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_logout) {
            prefManager.logout();
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}
