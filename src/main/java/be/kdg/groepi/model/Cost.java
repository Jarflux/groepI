package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Cost
 * Description:
 */
@Entity
@Table(name = "T_COST")
public class Cost implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "cost_id")
    long fId;
    @Column(name = "description")
    String fDescription;
    @Column(name = "amount")
    Double fAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance fTripInstance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fUser;

    // Hibernates needs empty constructor
    public Cost() {
    }

    public Cost(String fDescription, Double fAmount, TripInstance fTripInstance, User fUser) {
        this.fDescription = fDescription;
        this.fAmount = fAmount;
        this.fTripInstance = fTripInstance;
        this.fUser = fUser;
    }

    public long getId() {
        return fId;
    }

    public void setId(long fId) {
        this.fId = fId;
    }

    public String getDescription() {
        return fDescription;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Double getAmount() {
        return fAmount;
    }

    public void setAmount(Double fAmount) {
        this.fAmount = fAmount;
    }

    public TripInstance getTripInstance() {
        return fTripInstance;
    }

    public void setTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }

    public User getUser() {
        return fUser;
    }

    public void setUser(User fUser) {
        this.fUser = fUser;
    }

    @Override
    public String toString() {
        return "Cost:" + fDescription + " Amount:" + fAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Cost cost = (Cost) o;
        if (!CompareUtil.compareString(fDescription, cost.getDescription())) {
            return false;
        }
        if (!this.fAmount.equals(cost.getAmount())) {
            return false;
        }
        if (!this.fTripInstance.equals(cost.getTripInstance())) {
            return false;
        }
        if (!this.fUser.equals(cost.getUser())) {
            return false;
        }
        return true;
    }
}
