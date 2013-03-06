package com.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class Trip {
    private Long Id;
    private String Title;
    private String Description;
    private Boolean Available;
    private Boolean Repeatable;
    private User Organiser;
    private Set<Stop> Stops = new HashSet<Stop>();
    private Set<Requirement> Requirements = new HashSet<Requirement>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getAvailable() {
        return Available;
    }

    public void setAvailable(Boolean available) {
        Available = available;
    }

    public Boolean getRepeatable() {
        return Repeatable;
    }

    public void setRepeatable(Boolean repeatable) {
        Repeatable = repeatable;
    }

    public User getOrganiser() {
        return Organiser;
    }

    public void setOrganiser(User organiser) {
        Organiser = organiser;
    }

    public Set<Stop> getStops() {
        return Stops;
    }

    public void setStops(Set<Stop> stops) {
        Stops = stops;
    }

    public Set<Requirement> getRequirements() {
        return Requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        Requirements = requirements;
    }
}
