package com.example.mychatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    Thread wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Tools.context=getApplicationContext();

        progressBar=(ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth=firebaseAuth.getInstance();
        SplashThread();

        if(firebaseAuth.getCurrentUser()!=null){
            Tools.showMessage("You are already login. Redirect to main page");
            wait.start();

        }else{
            Tools.showMessage("Login or Register please .!");
        }
    }

    public void signupClick(View view) {
        startActivity(new Intent(SplashActivity.this,RegisterActivity.class));
    }

    public void loginClick(View view) {
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
    }

    public void SplashThread(){
        wait=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}