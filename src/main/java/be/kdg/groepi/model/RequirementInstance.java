package be.kdg.groepi.model;

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

    public RequirementInstance(Requirement requirement) {
        this.fName = requirement.getName();
        this.fAmount = requirement.getAmount();
        this.fDescription = requirement.getDescription();
        //this.tripInstance = requirement.get;
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

    public Long setId() {
        return fId;
    }

    public Long getId() {
        return fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public long getfAmount() {
        return fAmount;
    }

    public void setfAmount(long fAmount) {
        this.fAmount = fAmount;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getDescription() {
        return fDescription;
    }

    public TripInstance getfTripInstance() {
        return fTripInstance;
    }

    public void setfTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }
}
