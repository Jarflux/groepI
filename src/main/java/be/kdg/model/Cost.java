package be.kdg.model;

import javax.persistence.*;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Cost
 * Description:
 */

@Entity
@Table(name = "T_COST")
public class Cost {
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
}
