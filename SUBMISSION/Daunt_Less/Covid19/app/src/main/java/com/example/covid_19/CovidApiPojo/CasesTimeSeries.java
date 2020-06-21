package com.example.covid_19.CovidApiPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CasesTimeSeries {

    @SerializedName("dailyconfirmed")
    @Expose
    private String dailyconfirmed;
    @SerializedName("dailydeceased")
    @Expose
    private String dailydeceased;
    @SerializedName("dailyrecovered")
    @Expose
    private String dailyrecovered;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("totalconfirmed")
    @Expose
    private String totalconfirmed;
    @SerializedName("totaldeceased")
    @Expose
    private String totaldeceased;
    @SerializedName("totalrecovered")
    @Expose
    private String totalrecovered;

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public void setDailyconfirmed(String dailyconfirmed) {
        this.dailyconfirmed = dailyconfirmed;
    }

    public String getDailydeceased() {
        return dailydeceased;
    }

    public void setDailydeceased(String dailydeceased) {
        this.dailydeceased = dailydeceased;
    }

    public String getDailyrecovered() {
        return dailyrecovered;
    }

    public void setDailyrecovered(String dailyrecovered) {
        this.dailyrecovered = dailyrecovered;
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

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(String totaldeceased) {
        this.totaldeceased = totaldeceased;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

}