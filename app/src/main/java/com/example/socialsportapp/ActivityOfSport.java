package com.example.socialsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ActivityOfSport extends AppCompatActivity {


    Context mContext = this;
    private Button addbtn;
    private int minStart,minEnd;
    private int hourStart,hourEnd;
    private Button startBtn;
    private Button endBtn;
    private Button datebtn,listBtn;
    private String  fullName , phoneNum , startTime , endTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of_sport);
        endBtn = (Button)findViewById(R.id.EndBtn);
        Calendar calender = Calendar.getInstance();
        addbtn = (Button)findViewById(R.id.SendBtn);
        startBtn = (Button)findViewById(R.id.StartBtn);
        final int minute = calender.get(Calendar.MINUTE);
        final int hour = calender.get(Calendar.HOUR_OF_DAY);



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


    }
}