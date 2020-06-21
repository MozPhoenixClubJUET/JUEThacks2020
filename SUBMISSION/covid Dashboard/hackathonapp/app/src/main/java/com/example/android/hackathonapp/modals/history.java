package com.example.android.hackathonapp.modals;

public class history {

    private String date, totalconfirmed, totalrecovered, totaldeceased;

    public history(String date, String totalconfirmed, String totalrecovered, String totaldeceased) {
        this.date = date;
        this.totalconfirmed = totalconfirmed;
        this.totalrecovered = totalrecovered;
        this.totaldeceased = totaldeceased;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public void setTotalconfirmed(String totalconfirmed) {
        this.totalconfirmed = totalconfirmed;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(String totaldeceased) {
        this.totaldeceased = totaldeceased;
    }
}
