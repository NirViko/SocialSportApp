package com.example.socialsportapp;


import java.util.ArrayList;

public class ActivitysOfUser {

    private String selectedSportAdd ,selectedCityAdd ,startTime ,endTime ,getDate,address;
    //private ArrayList <String> userInThisActiv;
    private int maxOfUsers , numberOfusers;
    String pickimage ;




    public ActivitysOfUser()
    {
        // Default
    }
    public ActivitysOfUser(String selectedSportAdd , String  selectedCityAdd , String  startTime , String endTime , String pickimage , String getDate,int maxOfUsers,String address)
    {

        this.selectedSportAdd = selectedSportAdd;
        this.selectedCityAdd = selectedCityAdd;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pickimage = pickimage;
        this.getDate = getDate;
        this.maxOfUsers = maxOfUsers;
        this.address = address;
    }

    //GET

    public String getAddress() { return address; }

    public String getSelectedCityAdd() { return selectedCityAdd; }

    public String getStartTime() { return startTime; }

    public String getEndTime() {
        return endTime;
    }

    public String getGetDate() { return getDate; }

    public String getPickimage() {
        return pickimage;
    }

    public String getUserInThisActiv() {
        return selectedSportAdd;
    }

    public int getNumberOfusers() {
        return numberOfusers;
    }

    public int getMaxOfUsers() {
        return maxOfUsers;
    }

    //SET!


    public void setAddress(String address) { this.address = address; }

    public void setSelectedCityAdd(String selectedCityAdd) { this.selectedCityAdd = selectedCityAdd; }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public void setPickimage(String pickimage) {
        this.pickimage = pickimage;
    }

    public void setUserInThisActiv(String selectedSportAdd) { this.selectedSportAdd = selectedSportAdd; }

    public void setMaxOfUsers(int maxOfUsers) {
        this.maxOfUsers = maxOfUsers;
    }

    public void setNumberOfusers(int numberOfusers) {
        this.numberOfusers = numberOfusers;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedSportAdd = selectedCity;
    }

}
