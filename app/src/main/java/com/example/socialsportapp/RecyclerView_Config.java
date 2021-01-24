package com.example.socialsportapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private ActivityAdapter mActivityAdapter;
    public void setConfing(RecyclerView recyclerView ,Context context, List<ActivitysOfUser> activity ,List<String> keys)
    {
        mContext=context;
        mActivityAdapter = new ActivityAdapter(activity,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mActivityAdapter);
    }


    class ActivityItemView extends RecyclerView.ViewHolder
    {

        private TextView startTime;
        private TextView endTime;
        private TextView date;
        private TextView typeOfActivity;
        private TextView city;
        private String key;

        public ActivityItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).inflate(R.layout.activity_adapter,parent,false));
            startTime = (TextView)itemView.findViewById(R.id.starttime2);
            endTime = (TextView)itemView.findViewById(R.id.endtime2);
            date = (TextView)itemView.findViewById(R.id.dataForshow);
            typeOfActivity = (TextView)itemView.findViewById(R.id.TypeOfActivityToSee2);
            city = (TextView)itemView.findViewById(R.id.CityOfActivity1);

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
