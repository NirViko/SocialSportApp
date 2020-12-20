package com.example.socialsportapp;

import android.media.Image;

public class SportActivity {
    private String type_of;
    private String city;
    private String address;
    private String date;
    private int participants;
    private String start_time;
    private String end_time;

    public SportActivity(String type_of, String city, String address, String date,int participants, String start_time,String end_time)
    {
        this.type_of = type_of;
        this.city = city;
        this.address = address;
        this.date = date;
        this.participants = participants;
        this.start_time = start_time;
        this.end_time = end_time;

    }

    public void setType_of(String type_of) {
        this.type_of = type_of;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setParticipents(int participents) {
        this.participants = participants;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    private Image img;


    public String getType_of() {
        return type_of;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public int getParticipants() {
        return participants;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public Image getImg() {
        return img;
    }
}
