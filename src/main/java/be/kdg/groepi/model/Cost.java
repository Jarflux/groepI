package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;

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

    // Hibernates needs empty constructor
    public Cost() {
    }

    public Cost(String fDescription, Double fAmount, TripInstance fTripInstance) {
        this.fDescription = fDescription;
        this.fAmount = fAmount;
        this.fTripInstance = fTripInstance;
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

    @Override
    public String toString() {
        return "Cost:" + fDescription + " Amount:" + fAmount;
    }
}
