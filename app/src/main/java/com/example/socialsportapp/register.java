package com.example.socialsportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class register extends AppCompatActivity
{

    private EditText full_name;
    private EditText email;
    private EditText password;
    private Button registerBtn;
    private String sFullName,sEmail,sBirthday,sPassword;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        full_name = (EditText)findViewById(R.id.fullname);
        email = (EditText)findViewById(R.id.EmailAddress2);
        password = (EditText)findViewById(R.id.PasswordReg);
        registerBtn = (Button)findViewById(R.id.btnReg);
        mFirebaseAuth = FirebaseAuth.getInstance();

        //Connect to database
        onStart();
        //reff = FirebaseDatabase.getInstance().getReference().child("User");

        //sFullName = full_name.getText().toString();
        //sEmail = email.getText().toString();
        //sBirthday= birthday.getText().toString();
        //sPassword=password.getText().toString();


        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newfullName = full_name.getText().toString();
                String newpassword = password.getText().toString();
                String newEmail = email.getText().toString();

                if (CheckEditText(newfullName,newEmail,newpassword))
                {

                    mFirebaseAuth.createUserWithEmailAndPassword(newEmail, newpassword).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText((getApplicationContext()), "Authentication failed: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                email.setError("Email already exists");
                                email.requestFocus();

                            }
                            else
                              {
                                  Intent i = new Intent(register.this,homePage.class);
                                  startActivity(i);
                            }
                        }

                    });


                }
                }



    });


}
    private boolean CheckEditText(String fullNameC,String emailC,String passwordC)
    {
        if (fullNameC.matches(""))
        {
            full_name.setError("Please set name");
            full_name.requestFocus();
            return false;
        }
        else if (!isValidName(fullNameC))
        {
            full_name.setError("unexpected chars");
            full_name.requestFocus();
            return false;
        }

        else if(emailC.matches(""))
        {
            email.setError("Please set Email");
            email.requestFocus();
            return false;
        }

        else if(!isValidEmailAddress(emailC))
        {
            email.setError("invalid Email");
            email.requestFocus();
            return false;

        }

        else if(passwordC.matches(""))
        {
            password.setError("Please set password");
            password.requestFocus();
            return false;
        }
        else if(passwordC.length() < 6)
        {
            password.setError("At least 6 chars");
            password.requestFocus();
            return false;
        }
        return true;
    }



    public static boolean isValidEmailAddress(String emailC) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailC);
        return m.matches();
    }
    public static boolean isValidName(String name) {
        String ePattern = "^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,2}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(name);
        return m.matches();
    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();

    }

}