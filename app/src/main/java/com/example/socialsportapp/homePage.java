package com.example.socialsportapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.RecaptchaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class homePage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{



    private Button addBtn;
    private Button endBtn;
    private Uri pickimage;
    private String getDate;

    private Spinner spinner;
    private Button startBtn;
    private Button Imagebtn;
    private int countOfchild;
    private Button logOutbtn;
    private Button createbtn;
    private AlertDialog dialog;
    private Spinner spinnerCity;
    private int minStart,minEnd;
    private Button searchButton;
    private Spinner spinnerSport;
    private ListView getListView;
    private int hourStart,hourEnd;
    private Button datebtn,listBtn;
    private Spinner spinnerCityAdd;
    private Context mContext = this;
    private Spinner TypeOfActivityAdd;
    private FirebaseAuth mFireBaseAuth;
    private RecyclerView mRecyclerView;
    private ActivitysOfUser makeActivity;
    private AlertDialog.Builder dialogBuilder;
    private ArrayAdapter<CharSequence> adapter;
    private EditText etAddress ,etParticipants;
    private ArrayAdapter<CharSequence> adapterCity;
    private ArrayAdapter<CharSequence> adaptersportiv;
    private DatabaseReference mDatabase,mReadDatabase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private List <ActivitysOfUser> listOfActivity = new ArrayList<>();
    private String  fullName , phoneNum , startTime , endTime, pickimageadd ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String selected , selectedSport , selectedCity,selectedCityAdd, selectedSportAdd;

// ...

    //Static
    static int PReqCode = 1;
    static int REQUESCODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Connect to data base
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //


        //setView of list
        LoadedAllData();
        //

        searchButton = findViewById(R.id.button4);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!spinnerCity.getSelectedItem().toString().equals("City")) {
                    com.example.socialsportapp.Utilities.currentCityRefine = spinnerCity.getSelectedItem().toString();
                    LoadedAllData();
                }else {
                    com.example.socialsportapp.Utilities.currentCityRefine = null;
                    LoadedAllData();
                }

                if(!spinnerSport.getSelectedItem().toString().equals("Type of activity")) {
                    com.example.socialsportapp.Utilities.currentTypeRefine = spinnerSport.getSelectedItem().toString();
                    LoadedAllData();
                }else {
                    com.example.socialsportapp.Utilities.currentTypeRefine = null;
                    LoadedAllData();
                }
            }
        });

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


        //ListView
        //getListView = (ListView)findViewById(R.id.listViewID);
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
                createNewContentDialog();
            }
        });







    }

    //All Spinners//
    public void EventHandler()
    {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
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
    //End of fun//


    //Popup Window//
    public void createNewContentDialog()
    {

        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_add,null);

        //Add Activity//

        Calendar calender = Calendar.getInstance();
        //Popup Spinner
        spinnerCityAdd = (Spinner) contactPopupView.findViewById(R.id.spinnerCityMake);
        TypeOfActivityAdd = (Spinner) contactPopupView.findViewById(R.id.TypeOfActivityAdd);
        //End
        Imagebtn = (Button)contactPopupView.findViewById(R.id.Imagebtn);
        startBtn = (Button)contactPopupView.findViewById(R.id.StartBtn);
        endBtn = (Button)contactPopupView.findViewById(R.id.EndBtn);
        datebtn = (Button)contactPopupView.findViewById(R.id.Datebtn);
        etAddress = (EditText)contactPopupView.findViewById(R.id.etAddress);
        etParticipants = (EditText)contactPopupView.findViewById(R.id.etParticipants);
        createbtn = (Button)contactPopupView.findViewById(R.id.Createbtn);


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

        Imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDailog();
            }
        });



        //Add Spinner
        spinnerCityAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityAdd = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        TypeOfActivityAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSportAdd = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //



        createbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println("Start time: " + startTime);
                System.out.println("end time: " + endTime);
                if(pickimage != null) {
                    pickimageadd = pickimage.toString();
                }
                if(getDate == null || getDate == "") {
                    Toast.makeText((getApplicationContext()), "Please select date",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(startTime == null || startTime == "") {
                    Toast.makeText((getApplicationContext()), "Please select start time",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(endTime == null || endTime == "") {
                    Toast.makeText((getApplicationContext()), "Please select end time",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Integer.parseInt(endTime.split(":")[0]) < Integer.parseInt(startTime.split(":")[0])) {
                    Toast.makeText((getApplicationContext()), "Your end time should be later than your start time.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Integer.parseInt(endTime.split(":")[0]) == Integer.parseInt(startTime.split(":")[0]) &&
                        Integer.parseInt(endTime.split(":")[1]) < Integer.parseInt(startTime.split(":")[1])) {
                    Toast.makeText((getApplicationContext()), "Your end time should be later than your start time.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etAddress.getText().toString().length() < 3) {
                    Toast.makeText((getApplicationContext()), "Please enter address.",Toast.LENGTH_SHORT).show();
                    etAddress.setError("Address Error");
                    etAddress.requestFocus();
                    return;
                }
                if(etParticipants.getText().toString() == "") {
                    Toast.makeText((getApplicationContext()), "Please enter participants numbers.",Toast.LENGTH_SHORT).show();
                    etParticipants.setError("Enter number");
                    etParticipants.requestFocus();
                    return;
                }



                int size = Integer.parseInt(etParticipants.getText().toString());
                ArrayList<String> users = new ArrayList<String>();
                users.add(FirebaseAuth.getInstance().getUid());
                makeActivity = new ActivitysOfUser(selectedSportAdd,selectedCityAdd,startTime,endTime,pickimageadd,getDate,size,etAddress.getText().toString(),user.getUid(), users);
                //Send info to data Base

                mDatabase.child("Activitys").child(user.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // get total available quest
                                if(dataSnapshot.exists())
                                {
                                    countOfchild = (int) dataSnapshot.getChildrenCount();
                                }
                                makeActivity.setActivityNumber(Integer.toString(countOfchild+1));
                                makeActivity.setNumberOfusers(1);
                                mDatabase.child("Activitys").child(user.getUid()).child(Integer.toString(countOfchild+1)).setValue(makeActivity);

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError)
                            {}
                        });

                dialog.dismiss();
            }
        });


        spinnerCityAdd.setAdapter(adapterCity);
        TypeOfActivityAdd.setAdapter(adaptersportiv);
        spinnerCityAdd.setSelection(0, false);
        TypeOfActivityAdd.setSelection(0, false);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

    }

    //End of popup Window





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView pro_img;

        if (requestCode == 1)
        {
            try {

                if (resultCode == RESULT_OK && data.getData() != null) {
                    pickimage = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void openGallery()
    {
        //Open gallery intent and wait for user to pick an image
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,PReqCode);
    }






    private void showDatePickerDailog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //get the date here!!
        getDate = dayOfMonth + "." + month + "." + year;
    }




    // Read from data-base;
    private void ListViewRefresh(final DataStatus dataStatus)
    {

        mReadDatabase = FirebaseDatabase.getInstance().getReference("Activitys");

        mReadDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfActivity.clear();
                List <String> keys =  new ArrayList<>();
                for(DataSnapshot keyNode: snapshot.getChildren())
                {
                    for(DataSnapshot keyOfVal:keyNode.getChildren())
                    {
                        keys.add(keyOfVal.getKey());
                        ActivitysOfUser Activity = keyOfVal.getValue(ActivitysOfUser.class);
                        listOfActivity.add(Activity);
                    }
                }
                dataStatus.DataIsLoaded(listOfActivity,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    //

    public interface DataStatus
    {
        void DataIsLoaded(List<ActivitysOfUser> Activity,List<String> keys);
        void DataIsInserted();
        void DataIsUpdate();
        void DataIsDeleted();
    }

    public void LoadedAllData()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.ListView);
        new homePage().ListViewRefresh(new DataStatus() {
            @Override
            public void DataIsLoaded(List<ActivitysOfUser> Activity, List<String> keys) {
                new RecyclerView_Config().setConfing(mRecyclerView,homePage.this, Activity,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }




}