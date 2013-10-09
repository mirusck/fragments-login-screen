package com.example.fragmentswork;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

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

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.container, fGreeting);
        fTrans.commit();

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
            default:
                break;
        }

        fTrans.addToBackStack(null);
        fTrans.commit();
    }
}
