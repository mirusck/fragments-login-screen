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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    FragmentTransaction fTrans;
    GreetingFragment fGreeting;
    MainFragment fMain;
    AnimationFragment fAnimation;
    LoginFragment fLogin;
    SignupFragment fSignup;

    String[] mDrawerTitles;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;

    TextView tvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fGreeting = new GreetingFragment();
        fMain = new MainFragment();
        fLogin = new LoginFragment();
        fSignup = new SignupFragment();
        fAnimation = new AnimationFragment();
        tvLogo = (TextView) findViewById(R.id.tvLogo);

        // check if container exists and there are no saved state
        if ( (findViewById(R.id.container) != null) && (savedInstanceState == null) ) {
            fTrans = getSupportFragmentManager().beginTransaction();
            fMain.setArguments(getIntent().getExtras());
            fTrans.add(R.id.container, fMain);
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
                fTrans.replace(R.id.container, fMain);
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
                fTrans.replace(R.id.container, fMain);
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
        fTrans = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = fAnimation;
                break;
            case 1:
                fragment = fLogin;
                break;
            case 2:
                fragment = fSignup;
                break;
            default:
                fragment = fMain;
                break;
        }
        fTrans.replace(R.id.container, fragment);
        fTrans.addToBackStack(null);
        fTrans.commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }

}
