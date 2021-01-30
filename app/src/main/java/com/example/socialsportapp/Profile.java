package com.example.socialsportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ArrayList<ActivitysOfUser>activitysOfUsers = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Activitys").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for(DataSnapshot post : dataSnapshot.getChildren()) {
                        ActivitysOfUser activ = post.getValue(ActivitysOfUser.class);
                        if(activ.getUsers() != null) {
                            if (activ.getUsers().contains(FirebaseAuth.getInstance().getUid().toString()))
                                activitysOfUsers.add(activ);
                        }
                    }
                }
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));
                ProfileAdapter adapter = new ProfileAdapter(activitysOfUsers);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}