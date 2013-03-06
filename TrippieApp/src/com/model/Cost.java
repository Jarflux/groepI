package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class Cost {
    private long Id;
    private String Description;
    private Double Amount;
    private TripInstance TripInstance;
    private User User;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
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
