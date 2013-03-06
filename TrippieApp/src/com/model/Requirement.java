package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class Requirement {
    private long fId;
    private String fName;
    private long fAmount;
    private String fDescription;
    private Trip fTrip;

    public long getfId() {
        return fId;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public long getfAmount() {
        return fAmount;
    }

    public void setfAmount(long fAmount) {
        this.fAmount = fAmount;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Trip getfTrip() {
        return fTrip;
    }

    public void setfTrip(Trip fTrip) {
        this.fTrip = fTrip;
    }
}
