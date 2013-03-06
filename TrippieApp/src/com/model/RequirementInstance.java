package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class RequirementInstance {
    private long fId;
    private String fName;
    private long fAmount;
    private String fDescription;
    private User fUser;
    private TripInstance fTripInstance;

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

    public User getfUser() {
        return fUser;
    }

    public void setfUser(User fUser) {
        this.fUser = fUser;
    }

    public TripInstance getfTripInstance() {
        return fTripInstance;
    }

    public void setfTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }
}
