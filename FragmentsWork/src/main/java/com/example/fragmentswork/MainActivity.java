package com.example.fragmentswork;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    FragmentTransaction fTrans;
    GreetingFragment fGreeting;
    LoginFragment fLogin;
    SignupFragment fSignup;
    ListView listView;

    String[] mDrawerTitles;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;

    Animation animFadeIn;
    Animation animRotate;
    Animation animBounce;
    Animation animSlideDown;
    TextView tvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fGreeting = new GreetingFragment();
        fLogin = new LoginFragment();
        fSignup = new SignupFragment();
        tvLogo = (TextView) findViewById(R.id.tvLogo);
        if ( savedInstanceState == null ) {
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.add(R.id.container, fGreeting);
            fTrans.commit();
        }

        mDrawerTitles = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mDrawerTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // load anim
        animFadeIn = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.fade_in);
        animRotate = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.rotate);
        animBounce = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.bounce);
        animSlideDown = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.slide_down);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void onClick(View view) {

        fTrans = getSupportFragmentManager().beginTransaction();

        switch ( view.getId() ) {
            case R.id.btnLogin:
                fTrans.replace(R.id.container, fLogin);
                break;
            case R.id.btnSignup:
                fTrans.replace(R.id.container, fSignup);
                break;
            case R.id.btnLoginSubmit:
            case R.id.btnSignupSubmit:
                fTrans.replace(R.id.container, fGreeting);
                Toast toast = Toast.makeText(getApplicationContext(), R.string.nothing_to_do, Toast.LENGTH_SHORT);
                toast.show();
                break;
            default:
                break;
        }

        fTrans.addToBackStack(null); // we can pass there string key to fetch this activity later
        fTrans.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                // smth should be here
                Toast toast = Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.action_edit:
                // smth should be here
                return true;
            case android.R.id.home:
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.container, fGreeting);
                fTrans.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        switch (position) {
            case 0:
                fTrans = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new CustomListFragment();
                fTrans.replace(R.id.container, fragment);
                fTrans.commit();
                break;
            case 1:
                tvLogo.startAnimation(animFadeIn);
                break;
            case 2:
                tvLogo.startAnimation(animRotate);
                break;
            case 3:
                tvLogo.startAnimation(animBounce);
                break;
            case 4:
                tvLogo.startAnimation(animSlideDown);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }


}
