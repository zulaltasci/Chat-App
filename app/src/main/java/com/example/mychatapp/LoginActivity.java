package com.example.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText email,pass;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button) findViewById(R.id.buttonlogin3);
        email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        pass=(EditText) findViewById(R.id.editTextTextPassword);
        progressBar=(ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth=firebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                progressBar.setVisibility(View.VISIBLE);
                loginUser();
            }
        });
    }

    private void loginUser() {

        String userEmail=email.getText().toString();
        String userPass=pass.getText().toString();

        if(userEmail.isEmpty()){
            Tools.showMessage("User email can't be left blank ");
        }

        if(userPass.isEmpty() || userPass.length()<6){
            Tools.showMessage("Invalid password");
        }

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    Tools.showMessage("Welcome");
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Tools.showMessage("Failed");
                }
            }
        });

    }

    public void signupClick(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}