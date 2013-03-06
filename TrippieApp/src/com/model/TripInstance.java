package com.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
public class TripInstance {
    private Long Id;
    private String Title;
    private String Description;
    private Boolean Available;
    private long StartDate;
    private long EndDate;
    private Trip Trip;
    private User Organiser;
    private Set<User> Participants = new HashSet<User>();
    private Set<RequirementInstance> RequirementInstances = new HashSet<RequirementInstance>();
    private Set<Cost> Costs = new HashSet<Cost>();
    private Set<Message> Messages = new HashSet<Message>();

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

    public long getStartDate() {
        return StartDate;
    }

    public void setStartDate(long startDate) {
        StartDate = startDate;
    }

    public long getEndDate() {
        return EndDate;
    }

    public void setEndDate(long endDate) {
        EndDate = endDate;
    }

    public Trip getTrip() {
        return Trip;
    }

    public void setTrip(Trip trip) {
        Trip = trip;
    }

    public User getOrganiser() {
        return Organiser;
    }

    public void setOrganiser(User organiser) {
        Organiser = organiser;
    }

    public Set<User> getParticipants() {
        return Participants;
    }

    public void setParticipants(Set<User> participants) {
        Participants = participants;
    }

    public Set<RequirementInstance> getRequirementInstances() {
        return RequirementInstances;
    }

    public void setRequirementInstances(Set<RequirementInstance> requirementInstances) {
        RequirementInstances = requirementInstances;
    }

    public Set<Cost> getCosts() {
        return Costs;
    }

    public void setCosts(Set<Cost> costs) {
        Costs = costs;
    }

    public Set<Message> getMessages() {
        return Messages;
    }

    public void setMessages(Set<Message> messages) {
        Messages = messages;
    }
}
