package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private long fId;
    private String fContent;
    private Long fDate;
    private TripInstance fTripInstance;
    private User fUser;

    public long getfId() {
        return fId;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public Long getfDate() {
        return fDate;
    }

    public void setfDate(Long fDate) {
        this.fDate = fDate;
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
