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

public class RegisterActivity extends AppCompatActivity {

    Button signup;
    EditText name, email,pass;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup=(Button) findViewById(R.id.buttonsignup3);
        name=(EditText) findViewById(R.id.editTextTextPersonName);
        email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        pass=(EditText) findViewById(R.id.editTextTextPassword);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth=firebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                progressBar.setVisibility(View.VISIBLE);
                singupUser();
            }
        });

    }

    private void singupUser() {
        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPass=pass.getText().toString();

        if(userName.isEmpty()){
            Tools.showMessage("Username can't be left blank ");
        }
        if(userEmail.isEmpty()){
            Tools.showMessage("User email can't be left blank ");
        }

        if(userPass.isEmpty() || userPass.length()<6){
            Tools.showMessage("Invalid password");
        }

        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful()){
                            Tools.showMessage("Kayıt başarılı");
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        }else{
                            Tools.showMessage("Failed");
                        }
                    }
                });

    }

    public void loginClick(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}