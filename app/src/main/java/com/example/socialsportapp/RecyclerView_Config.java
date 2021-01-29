package com.example.socialsportapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerView_Config {
    private Context mContext;
    private ActivityAdapter mActivityAdapter;
    private static List<ActivitysOfUser> activity;
    public void setConfing(RecyclerView recyclerView ,Context context, List<ActivitysOfUser> activity ,List<String> keys) {
        mContext=context;
        this.activity = activity;
        mActivityAdapter = new ActivityAdapter(activity,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mActivityAdapter);

        System.out.println("activities: " + activity);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(currentTime);
        String currentDay = currentDateandTime.split(":")[0];
        String currentMonth = currentDateandTime.split(":")[1];
        String currentYear = currentDateandTime.split(":")[2];
        System.out.println("currentDateandTime: " + currentDateandTime);
        System.out.println("currentYear: " + currentYear);
        System.out.println("currentMonth: " + Integer.parseInt(currentMonth));
        System.out.println("currentDay: " + currentDay);

        List<ActivitysOfUser> currentList = new ArrayList<>();
        for (ActivitysOfUser activitys : activity) {
            String date = activitys.getGetDate().toString();

            if (Integer.parseInt(currentMonth) < Integer.parseInt(activitys.getGetDate().split(":")[1])) {
                currentList.add(activitys);
            }
            if (Integer.parseInt(currentYear) < Integer.parseInt(activitys.getGetDate().split(":")[2])) {
                currentList.add(activitys);
            }
            if (Integer.parseInt(currentMonth) == Integer.parseInt(activitys.getGetDate().split(":")[1])
                    && Integer.parseInt(currentDay) < Integer.parseInt(activitys.getGetDate().split(":")[0])) {
                currentList.add(activitys);
            }
        }
        for(ActivitysOfUser cur : currentList) {
            activity.remove(cur);
            FirebaseDatabase.getInstance().getReference().child("Activitys")
                    .child(cur.getActivityID())
                    .child(cur.getActivityNumber()).removeValue();
        }



        if(Utilities.currentCityRefine == null || Utilities.currentCityRefine == "") {
        }else {
            List<ActivitysOfUser> current = new ArrayList<>();
            for (ActivitysOfUser activitys : activity) {
                if (!activitys.getSelectedCityAdd().equals(Utilities.currentCityRefine)) {
                    current.add(activitys);

                }
            }
            for(ActivitysOfUser cur : current) {
                activity.remove(cur);
            }
        }

        if(Utilities.currentTypeRefine == null || Utilities.currentTypeRefine == "") {
        }else {
            List<ActivitysOfUser> current = new ArrayList<>();
            for (ActivitysOfUser activitys : activity) {
                if (!activitys.getSelectedSportAdd().equals(Utilities.currentTypeRefine)) {
                    //activity.remove(activitys);
                    current.add(activitys);
                }
            }
            for(ActivitysOfUser cur : current) {
                activity.remove(cur);
            }
        }





        if(Utilities.currentDayRefine == null || Utilities.currentDayRefine == "") {
        }else {
            List<ActivitysOfUser> current = new ArrayList<>();
            for (ActivitysOfUser activitys : activity) {
                SimpleDateFormat inFormat = new SimpleDateFormat("dd.MM.yy");
                Date date = null;
                System.out.println("Date: " + activitys.getGetDate());
                try {
                    date = inFormat.parse(activitys.getGetDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                String goal = outFormat.format(date);
                System.out.println("goal: " + goal);
                System.out.println("Utilities.currentDayRefine: " + Utilities.currentDayRefine);
                if (!goal.equals(Utilities.currentDayRefine)) {
                    //activity.remove(activitys);
                    current.add(activitys);
                }
            }
            for(ActivitysOfUser cur : current) {
                activity.remove(cur);
            }
        }





    }


    private static TextView startTime;
    private static TextView endTime;
    private static TextView date;
    private static TextView typeOfActivity;
    private static TextView city;
    private static TextView street;
    private static Button joinButton;

    class ActivityItemView extends RecyclerView.ViewHolder
    {

        private String key;


        public ActivityItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).inflate(R.layout.activity_adapter,parent,false));
            startTime = (TextView)itemView.findViewById(R.id.starttime2);
            joinButton = (Button)itemView.findViewById(R.id.clickbtnf);
            endTime = (TextView)itemView.findViewById(R.id.endtime2);
            date = (TextView)itemView.findViewById(R.id.dataForshow);
            typeOfActivity = (TextView)itemView.findViewById(R.id.TypeOfActivityToSee2);
            city = (TextView)itemView.findViewById(R.id.CityOfActivity1);
            street = (TextView)itemView.findViewById(R.id.CityOfActivity1);


        }
        public void bind(ActivitysOfUser user , String key)
        {
            city.setText(user.getSelectedCityAdd());
            startTime.setText(user.getStartTime());
            endTime.setText(user.getEndTime());
            date.setText(user.getGetDate());
            typeOfActivity.setText(user.getUserInThisActiv());
            this.key = key;
        }
    }

    class ActivityAdapter extends RecyclerView.Adapter<ActivityItemView>
    {
        private List<ActivitysOfUser> activitysList;
        private List<String> mKey;

        public ActivityAdapter(List<ActivitysOfUser> activitysList, List<String> mKey) {
            this.activitysList = activitysList;
            this.mKey = mKey;
        }

        public ActivityAdapter() {
            super();
        }

        @NonNull
        @Override
        public ActivityItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ActivityItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ActivityItemView holder, int position) {
            holder.bind(activitysList.get(position),mKey.get(position));


            if(activitysList.get(position).getMaxOfUsers() == activitysList.get(position).getNumberOfusers()) {
                joinButton.setText("Full.");
                joinButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                joinButton.setTextColor(Color.parseColor("#000000"));
                joinButton.setEnabled(false);
            }

            if(FirebaseAuth.getInstance().getUid() != null && activitysList.get(position).getUsers() != null) {
                if (activitysList.get(position).getUsers().contains(FirebaseAuth.getInstance().getUid())) {
                    joinButton.setText("Joined.");
                    joinButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    joinButton.setTextColor(Color.parseColor("#000000"));
                    joinButton.setEnabled(false);
                }
            }


            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Hello world");
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                    final View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_join, null);
                    TextView typeTV = (TextView) popupView.findViewById(R.id.infoTypeOfActivity);
                    TextView dateTV = (TextView) popupView.findViewById(R.id.infoTextViewDate);
                    TextView startTimeTV = (TextView) popupView.findViewById(R.id.infoStartTime);
                    TextView endTimeTV = (TextView) popupView.findViewById(R.id.infoEndTime);
                    TextView cityTV = (TextView) popupView.findViewById(R.id.infoOfcity);
                    TextView streetTV = (TextView) popupView.findViewById(R.id.infoOfStreet);
                    TextView numberTV = (TextView) popupView.findViewById(R.id.etParticipants);

                    Button joinButton = (Button) popupView.findViewById(R.id.infoJoin);

                    typeTV.setText(activitysList.get(position).getUserInThisActiv());
                    dateTV.setText(activitysList.get(position).getGetDate());
                    startTimeTV.setText(activitysList.get(position).getStartTime());
                    endTimeTV.setText(activitysList.get(position).getEndTime());
                    cityTV.setText(activitysList.get(position).getSelectedCityAdd());
                    streetTV.setText(activitysList.get(position).getAddress());
                    numberTV.setText("" + (activitysList.get(position).getMaxOfUsers() - activitysList.get(position).getNumberOfusers()));



                    dialogBuilder.setView(popupView);
                    AlertDialog dialog = dialogBuilder.create();
                    dialog.show();


                    joinButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            if(auth == null) {

                                Toast.makeText(mContext, "User Error", Toast.LENGTH_LONG).show();
                                return;
                            }else {
                                if(numberTV.getText() == "0") {

                                    Toast.makeText(mContext, "No place left.", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                List<String>users;
                                if(activitysList.get(position).getUsers() != null) {
                                    users = activitysList.get(position).getUsers();
                                }else {
                                    users = new ArrayList<String>();
                                }

                                users.add(auth.getUid());
                                FirebaseDatabase.getInstance().getReference().child("Activitys").
                                        child(activitysList.get(position).getActivityID()).
                                        child(activitysList.get(position).getActivityNumber()).child("users").
                                        setValue(users);
                                FirebaseDatabase.getInstance().getReference().child("Activitys").
                                        child(activitysList.get(position).getActivityID()).
                                        child(activitysList.get(position).getActivityNumber()).child("numberOfusers").
                                        setValue(users.size());

                                dialog.dismiss();

                            }
                        }
                    });

                }
            });
        }

        @Override
        public int getItemCount() {
            if (activitysList != null)
                return activitysList.size();
            else
                return 0;
        }
    }



}
