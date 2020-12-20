package com.example.socialsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class homePage extends AppCompatActivity {

    private Button logOutbtn;
    FirebaseAuth mFireBaseAuth;
    Spinner spinner;
    Spinner spinnerSport;
    Spinner spinnerCity;

    ArrayAdapter<CharSequence> adapterCity;
    ArrayAdapter<CharSequence> adaptersportiv;
    ArrayAdapter<CharSequence> adapter;

    String selected , selectedSport , selectedCity;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logOutbtn = (Button) findViewById(R.id.LogoutBtn);
        //Spinner Activity//
        spinner = (Spinner) findViewById(R.id.spinnerDays);
        spinnerSport = (Spinner) findViewById(R.id.spinnerActiv);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        adapter = ArrayAdapter.createFromResource(this, R.array.array_names, android.R.layout.simple_spinner_dropdown_item);
        adaptersportiv = ArrayAdapter.createFromResource(this, R.array.typs_sport, android.R.layout.simple_spinner_dropdown_item);
        adapterCity = ArrayAdapter.createFromResource(this, R.array.array_ofCitys, android.R.layout.simple_spinner_dropdown_item);

        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);

        adaptersportiv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSport.setAdapter(adaptersportiv);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        EventHandler();
        logOutbtn.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intTo = new Intent(homePage.this, Login.class);
                startActivity(intTo);
            }
        });
    }

    public void EventHandler() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

     spinnerSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             selectedSport = parent.getItemAtPosition(position).toString();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });

     spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             selectedCity = parent.getItemAtPosition(position).toString();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });


    }
}