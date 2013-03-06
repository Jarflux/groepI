package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class RequirementInstance {
    private long Id;
    private String Name;
    private long Amount;
    private String Description;
    private User User;
    private TripInstance TripInstance;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getAmount() {
        return Amount;
    }

    public void setAmount(long amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public TripInstance getTripInstance() {
        return TripInstance;
    }

    public void setTripInstance(TripInstance tripInstance) {
        TripInstance = tripInstance;
    }
}
