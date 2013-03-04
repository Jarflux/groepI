package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Requirement
 * Description:
 */
@Entity
@Table(name = "T_REQUIREMENT_INSTANCE")
public class RequirementInstance implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "requirement_instance_id")
    long fId;
    @Column(name = "name")
    String fName;
    @Column(name = "amount")
    long fAmount;
    @Column(name = "description")
    String fDescription;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User fUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance fTripInstance;

    // Hibernates needs empty constructor
    public RequirementInstance() {
    }

    public RequirementInstance(Requirement requirement, TripInstance tripInstance) {
        this.fName = requirement.getName();
        this.fAmount = requirement.getAmount();
        this.fDescription = requirement.getDescription();
        this.fTripInstance = tripInstance;
    }

    public RequirementInstance(String fName, long fAmount, String fDescription, TripInstance fTripInstance) {
        this.fName = fName;
        this.fAmount = fAmount;
        this.fDescription = fDescription;
        this.fTripInstance = fTripInstance;
    }

    public RequirementInstance(String fName, long fAmount, String fDescription, TripInstance fTripInstance, User fUser) {
        this.fName = fName;
        this.fAmount = fAmount;
        this.fDescription = fDescription;
        this.fUser = fUser;
        this.fTripInstance = fTripInstance;
    }

    public User getUser() {
        return fUser;
    }

    public void setUser(User fUser) {
        this.fUser = fUser;
    }

    public Long getId() {
        return fId;
    }

    public String getName() {
        return fName;
    }

    public void setName(String fName) {
        this.fName = fName;
    }

    public long getAmount() {
        return fAmount;
    }

    public void setAmount(long fAmount) {
        this.fAmount = fAmount;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getDescription() {
        return fDescription;
    }

    public TripInstance getTripInstance() {
        return fTripInstance;
    }

    public void setTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        RequirementInstance requirementInstance = (RequirementInstance) o;
        if (!CompareUtil.compareString(fName, requirementInstance.getName())) {
            return false;
        }
        if (!CompareUtil.compareString(fDescription, requirementInstance.getDescription())) {
            return false;
        }
        if (!CompareUtil.compareLong(fAmount, requirementInstance.getAmount())) {
            return false;
        }
        if (!CompareUtil.compareUser(fUser, requirementInstance.getUser())) {
            return false;
        }
        if (!this.fTripInstance.equals(requirementInstance.getTripInstance())) {
            return false;
        }
        return true;
    }
}
