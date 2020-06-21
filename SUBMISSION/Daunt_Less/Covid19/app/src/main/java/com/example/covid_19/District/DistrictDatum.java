package com.example.covid_19.District;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictDatum {

@SerializedName("district")
@Expose
private String district;
@SerializedName("notes")
@Expose
private String notes;
@SerializedName("active")
@Expose
private Integer active;
@SerializedName("confirmed")
@Expose
private Integer confirmed;
@SerializedName("deceased")
@Expose
private Integer deceased;
@SerializedName("recovered")
@Expose
private Integer recovered;
@SerializedName("delta")
@Expose
private Delta delta;

public String getDistrict() {
return district;
}

public void setDistrict(String district) {
this.district = district;
}

public String getNotes() {
return notes;
}

public void setNotes(String notes) {
this.notes = notes;
}

public Integer getActive() {
return active;
}

public void setActive(Integer active) {
this.active = active;
}

public Integer getConfirmed() {
return confirmed;
}

public void setConfirmed(Integer confirmed) {
this.confirmed = confirmed;
}

public Integer getDeceased() {
return deceased;
}

public void setDeceased(Integer deceased) {
this.deceased = deceased;
}

public Integer getRecovered() {
return recovered;
}

public void setRecovered(Integer recovered) {
this.recovered = recovered;
}

public Delta getDelta() {
return delta;
}

public void setDelta(Delta delta) {
this.delta = delta;
}

}