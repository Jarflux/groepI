package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class Cost {
    private long fId;
    private String fDescription;
    private Double fAmount;
    private TripInstance fTripInstance;
    private User fUser;

    public long getfId() {
        return fId;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Double getfAmount() {
        return fAmount;
    }

    public void setfAmount(Double fAmount) {
        this.fAmount = fAmount;
    }

    public TripInstance getfTripInstance() {
        return fTripInstance;
    }

    public void setfTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }

    public User getfUser() {
        return fUser;
    }

    public void setfUser(User fUser) {
        this.fUser = fUser;
    }
}
