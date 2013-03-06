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
    private Long fId;
    private String fTitle;
    private String fDescription;
    private Boolean fAvailable;
    private long fStartDate;
    private long fEndDate;
    private Trip fTrip;
    private User fOrganiser;
    private Set<User> fParticipants = new HashSet<User>();
    private Set<RequirementInstance> fRequirementInstances = new HashSet<RequirementInstance>();
    private Set<Cost> fCosts = new HashSet<Cost>();
    private Set<Message> fMessages = new HashSet<Message>();

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfTitle() {
        return fTitle;
    }

    public void setfTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Boolean getfAvailable() {
        return fAvailable;
    }

    public void setfAvailable(Boolean fAvailable) {
        this.fAvailable = fAvailable;
    }

    public long getfStartDate() {
        return fStartDate;
    }

    public void setfStartDate(long fStartDate) {
        this.fStartDate = fStartDate;
    }

    public long getfEndDate() {
        return fEndDate;
    }

    public void setfEndDate(long fEndDate) {
        this.fEndDate = fEndDate;
    }

    public Trip getfTrip() {
        return fTrip;
    }

    public void setfTrip(Trip fTrip) {
        this.fTrip = fTrip;
    }

    public User getfOrganiser() {
        return fOrganiser;
    }

    public void setfOrganiser(User fOrganiser) {
        this.fOrganiser = fOrganiser;
    }

    public Set<User> getfParticipants() {
        return fParticipants;
    }

    public void setfParticipants(Set<User> fParticipants) {
        this.fParticipants = fParticipants;
    }

    public Set<RequirementInstance> getfRequirementInstances() {
        return fRequirementInstances;
    }

    public void setfRequirementInstances(Set<RequirementInstance> fRequirementInstances) {
        this.fRequirementInstances = fRequirementInstances;
    }

    public Set<Cost> getfCosts() {
        return fCosts;
    }

    public void setfCosts(Set<Cost> fCosts) {
        this.fCosts = fCosts;
    }

    public Set<Message> getfMessages() {
        return fMessages;
    }

    public void setfMessages(Set<Message> fMessages) {
        this.fMessages = fMessages;
    }
}
