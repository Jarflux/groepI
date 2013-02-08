package be.kdg.groepi.model;

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
public class Cost implements Serializable{
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "description")
    String fDescription;
    @Column(name = "amount")
    Double fAmount;

    // Hibernates needs empty constructor
    public Cost() {
    }

    public Cost(String description, Double amount) {
        this.fDescription = description;
        this.fAmount = amount;
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
}
