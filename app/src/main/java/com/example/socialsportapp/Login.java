package com.example.socialsportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button loginBtn;
    private EditText email;
    private EditText password;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.emailID);
        password = (EditText)findViewById(R.id.PasswordLogin);
        loginBtn = (Button)findViewById(R.id.loginbtn);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            FirebaseUser mFirebaseUser =mFirebaseAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if(mFirebaseUser != null){
                    Toast.makeText(Login.this,"You are logged in ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,homePage.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }

            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newEmail = email.getText().toString();
                String newPassword = password.getText().toString();


                if(CheckEditText(newEmail,newPassword))
                {
                   mFirebaseAuth.signInWithEmailAndPassword(newEmail,newPassword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful())
                           {

                               Toast.makeText(Login.this,"Login Error, Please Try again" ,Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Intent intoHome= new Intent(Login.this,homePage.class);
                               startActivity(intoHome);
                           }

                       }
                   });
                }

            }
        });
    }


    private boolean CheckEditText(String emailC,String passwordC)
    {

        if(emailC.matches(""))
        {
            email.setError("Please set Email");
            email.requestFocus();
            return false;
        }

        else if(passwordC.matches(""))
        {
            password.setError("Please set password");
            password.requestFocus();
            return false;
        }
        return true;
    }
    protected void onStart() {

        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}