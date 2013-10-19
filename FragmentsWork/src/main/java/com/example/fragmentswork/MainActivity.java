package com.example.fragmentswork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    FragmentTransaction fTrans;
    GreetingFragment fGreeting;
    LoginFragment fLogin;
    SignupFragment fSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fGreeting = new GreetingFragment();
        fLogin = new LoginFragment();
        fSignup = new SignupFragment();
        if ( savedInstanceState == null ) {
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.add(R.id.container, fGreeting);
            fTrans.commit();
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

        fTrans.addToBackStack(null);
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
}
