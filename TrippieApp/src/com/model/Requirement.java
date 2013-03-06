package com.model;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class Requirement {
    private long Id;
    private String Name;
    private long Amount;
    private String Description;
    private Trip Trip;

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

    public Trip getTrip() {
        return Trip;
    }

    public void setTrip(Trip trip) {
        Trip = trip;
    }
}
