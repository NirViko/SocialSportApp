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
import com.google.firebase.auth.FirebaseUser;
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
    private EditText birthday;
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
        birthday = (EditText)findViewById(R.id.DateReg);
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
                String newbirthday = birthday.getText().toString();

                if (CheckEditText(newfullName,newEmail,newbirthday,newpassword))
                {

                    mFirebaseAuth.createUserWithEmailAndPassword(newEmail, newpassword).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(register.this, "Field is unsuc", Toast.LENGTH_SHORT);
                            }
                            else
                              {
                                startActivity(new Intent(register.this, Login.class));
                            }
                        }

                    });
                    Intent i = new Intent(register.this,homePage.class);
                    startActivity(i);

                }


            }
    });


}
    private boolean CheckEditText(String fullNameC,String emailC,String birthdayC,String passwordC)
    {
        if (fullNameC.matches(""))
        {
            full_name.setError("Please set name");
            full_name.requestFocus();
            return false;
        }
        else if(emailC.matches(""))
        {
            email.setError("Please set Email");
            email.requestFocus();
            return false;
        }
        else if(birthdayC.matches(""))
        {
            birthday.setError("Please set birthday");
            birthday.requestFocus();
            return  false;
        }
        else if(passwordC.matches(""))
        {
            password.setError("Please set password");
            password.requestFocus();
            return false;
        }
        return true;
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();

    }

}