package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private long Id;
    private String Content;
    private Long Date;
    private TripInstance TripInstance;
    private User User;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public TripInstance getTripInstance() {
        return TripInstance;
    }

    public void setTripInstance(TripInstance tripInstance) {
        TripInstance = tripInstance;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }
}
