package com.example.socialsportapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ActivitysOfUser> activities = new ArrayList<>();

    public ProfileAdapter(ArrayList<ActivitysOfUser> activities) {
        this.activities = activities;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public static TextView starttime2, endtime2, dataForshow, CityOfActivity1, TypeOfActivityToSee2;
        public static Button infoButton;
        public ViewHolder(View view) {
            super(view);
            starttime2 = (TextView) view.findViewById(R.id.starttime2);
            endtime2 = (TextView) view.findViewById(R.id.endtime2);
            dataForshow = (TextView) view.findViewById(R.id.dataForshow);
            CityOfActivity1 = (TextView) view.findViewById(R.id.CityOfActivity1);
            TypeOfActivityToSee2 = (TextView) view.findViewById(R.id.TypeOfActivityToSee2);


        }
    }

    public static TextView starttime2, endtime2, dataForshow, CityOfActivity1, TypeOfActivityToSee2;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        starttime2 = viewHolder.starttime2;
        endtime2 = viewHolder.endtime2;
        dataForshow = viewHolder.dataForshow;
        CityOfActivity1 = viewHolder.CityOfActivity1;
        TypeOfActivityToSee2 = viewHolder.TypeOfActivityToSee2;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        starttime2.setText(activities.get(position).getStartTime());
        endtime2.setText(activities.get(position).getEndTime());
        dataForshow.setText(activities.get(position).getGetDate());
        CityOfActivity1.setText(activities.get(position).getSelectedCityAdd());
        TypeOfActivityToSee2.setText(activities.get(position).getSelectedSportAdd());



    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

}
