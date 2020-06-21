package com.example.covid_19.District;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

@SerializedName("state")
@Expose
private String state;
@SerializedName("statecode")
@Expose
private String statecode;
@SerializedName("districtData")
@Expose
private List<DistrictDatum> districtData = null;

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getStatecode() {
return statecode;
}

public void setStatecode(String statecode) {
this.statecode = statecode;
}

public List<DistrictDatum> getDistrictData() {
return districtData;
}

public void setDistrictData(List<DistrictDatum> districtData) {
this.districtData = districtData;
}

}