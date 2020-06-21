package com.example.covid_19.CovidApiPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tested {

@SerializedName("individualstestedperconfirmedcase")
@Expose
private String individualstestedperconfirmedcase;
@SerializedName("positivecasesfromsamplesreported")
@Expose
private String positivecasesfromsamplesreported;
@SerializedName("samplereportedtoday")
@Expose
private String samplereportedtoday;
@SerializedName("source")
@Expose
private String source;
@SerializedName("testpositivityrate")
@Expose
private String testpositivityrate;
@SerializedName("testsconductedbyprivatelabs")
@Expose
private String testsconductedbyprivatelabs;
@SerializedName("testsperconfirmedcase")
@Expose
private String testsperconfirmedcase;
@SerializedName("totalindividualstested")
@Expose
private String totalindividualstested;
@SerializedName("totalpositivecases")
@Expose
private String totalpositivecases;
@SerializedName("totalsamplestested")
@Expose
private String totalsamplestested;
@SerializedName("updatetimestamp")
@Expose
private String updatetimestamp;

public String getIndividualstestedperconfirmedcase() {
return individualstestedperconfirmedcase;
}

public void setIndividualstestedperconfirmedcase(String individualstestedperconfirmedcase) {
this.individualstestedperconfirmedcase = individualstestedperconfirmedcase;
}

public String getPositivecasesfromsamplesreported() {
return positivecasesfromsamplesreported;
}

public void setPositivecasesfromsamplesreported(String positivecasesfromsamplesreported) {
this.positivecasesfromsamplesreported = positivecasesfromsamplesreported;
}

public String getSamplereportedtoday() {
return samplereportedtoday;
}

public void setSamplereportedtoday(String samplereportedtoday) {
this.samplereportedtoday = samplereportedtoday;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public String getTestpositivityrate() {
return testpositivityrate;
}

public void setTestpositivityrate(String testpositivityrate) {
this.testpositivityrate = testpositivityrate;
}

public String getTestsconductedbyprivatelabs() {
return testsconductedbyprivatelabs;
}

public void setTestsconductedbyprivatelabs(String testsconductedbyprivatelabs) {
this.testsconductedbyprivatelabs = testsconductedbyprivatelabs;
}

public String getTestsperconfirmedcase() {
return testsperconfirmedcase;
}

public void setTestsperconfirmedcase(String testsperconfirmedcase) {
this.testsperconfirmedcase = testsperconfirmedcase;
}

public String getTotalindividualstested() {
return totalindividualstested;
}

public void setTotalindividualstested(String totalindividualstested) {
this.totalindividualstested = totalindividualstested;
}

public String getTotalpositivecases() {
return totalpositivecases;
}

public void setTotalpositivecases(String totalpositivecases) {
this.totalpositivecases = totalpositivecases;
}

public String getTotalsamplestested() {
return totalsamplestested;
}

public void setTotalsamplestested(String totalsamplestested) {
this.totalsamplestested = totalsamplestested;
}

public String getUpdatetimestamp() {
return updatetimestamp;
}

public void setUpdatetimestamp(String updatetimestamp) {
this.updatetimestamp = updatetimestamp;
}

}