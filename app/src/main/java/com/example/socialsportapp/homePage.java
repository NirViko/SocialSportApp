package com.example.socialsportapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class homePage extends AppCompatActivity {


    private Button addBtn;
    private Button endBtn;
    private Spinner spinner;
    private Button startBtn;
    private Button logOutbtn;
    private AlertDialog dialog;
    private Spinner spinnerCity;
    private int minStart,minEnd;
    private Spinner spinnerSport;
    private int hourStart,hourEnd;
    private Button datebtn,listBtn;
    private Context mContext = this;
    private FirebaseAuth mFireBaseAuth;
    private AlertDialog.Builder dialogBuilder;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapterCity;
    private ArrayAdapter<CharSequence> adaptersportiv;
    private String selected , selectedSport , selectedCity;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String  fullName , phoneNum , startTime , endTime ;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //btn Of this Activity
        addBtn = (Button)findViewById(R.id.Add);
        logOutbtn = (Button) findViewById(R.id.LogoutBtn);
        //

        //Spinner Activity//
        spinner = (Spinner) findViewById(R.id.spinnerDays);
        spinnerSport = (Spinner) findViewById(R.id.spinnerActiv);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        //Array Adapter
        adapter = ArrayAdapter.createFromResource(this, R.array.array_names, android.R.layout.simple_spinner_dropdown_item);
        adaptersportiv = ArrayAdapter.createFromResource(this, R.array.typs_sport, android.R.layout.simple_spinner_dropdown_item);
        adapterCity = ArrayAdapter.createFromResource(this, R.array.array_ofCitys, android.R.layout.simple_spinner_dropdown_item);
        //

        //Set all array adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptersportiv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //


        spinner.setAdapter(adapter);
        spinnerCity.setAdapter(adapterCity);
        spinnerSport.setAdapter(adaptersportiv);


        EventHandler();
        logOutbtn.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intTo = new Intent(homePage.this, Login.class);
                startActivity(intTo);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                createNewContentDiaglog();
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
            public void onNothingSelected(AdapterView<?> parent) {}
        });

     spinnerSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             selectedSport = parent.getItemAtPosition(position).toString();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {}
     });

     spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             selectedCity = parent.getItemAtPosition(position).toString();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {}
     });

    }

    public void createNewContentDiaglog()
    {



        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_add,null);

        //Add Activity//

        Calendar calender = Calendar.getInstance();
        startBtn = (Button)contactPopupView.findViewById(R.id.StartBtn);
        endBtn = (Button)contactPopupView.findViewById(R.id.EndBtn);
        final int minute = calender.get(Calendar.MINUTE);
        final int hour = calender.get(Calendar.HOUR_OF_DAY);
        //


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        startTime= hourOfDay + ":" + minute;
                        minStart=minute;
                        hourStart=hourOfDay;
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();

            }
        });

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay < 12) {
                            hourEnd=hourOfDay+24;
                        } else {
                            hourEnd=hourOfDay;
                        }
                        endTime = hourOfDay + ":" + minute;

                        minEnd=minute;
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();

            }
        });


        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}