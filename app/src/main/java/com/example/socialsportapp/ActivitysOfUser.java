package com.example.socialsportapp;


import java.util.ArrayList;

public class ActivitysOfUser {

    private String selectedSportAdd ,selectedCityAdd ,startTime ,endTime ,getDate;
    private ArrayList <String> userInThisActiv;
    private int maxOfUsers , numberOfusers;
    String pickimage ;



    public ActivitysOfUser()
    {
        // Default
    }
    public ActivitysOfUser(String selectedSportAdd , String  selectedCityAdd , String  startTime , String endTime , String pickimage , String getDate)
    {
        userInThisActiv = new ArrayList<String>();
        this.selectedSportAdd = selectedSportAdd;
        this.selectedCityAdd = selectedCityAdd;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pickimage = pickimage;
        this.getDate = getDate;
    }

    public String getSelectedCity() {
        return selectedSportAdd;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedSportAdd = selectedCity;
    }

    public String getSelectedCityAdd() {
        return selectedCityAdd;
    }

    public void setSelectedCityAdd(String selectedCityAdd) {
        this.selectedCityAdd = selectedCityAdd;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public String getPickimage() {
        return pickimage;
    }

    public void setPickimage(String pickimage) {
        this.pickimage = pickimage;
    }


    public ArrayList<String> getUserInThisActiv() {
        return userInThisActiv;
    }

    public void setUserInThisActiv(ArrayList<String> userInThisActiv) {
        this.userInThisActiv = userInThisActiv;
    }

    public int getMaxOfUsers() {
        return maxOfUsers;
    }

    public void setMaxOfUsers(int maxOfUsers) {
        this.maxOfUsers = maxOfUsers;
    }

    public int getNumberOfusers() {
        return numberOfusers;
    }

    public void setNumberOfusers(int numberOfusers) {
        this.numberOfusers = numberOfusers;
    }

}
