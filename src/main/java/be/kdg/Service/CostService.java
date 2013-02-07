package be.kdg.Service;

import javax.persistence.*;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: CostService
 * Description:
 */

@Entity
@Table(name = "T_COST")
public class CostService {
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "description")
    String fDescription;
    @Column(name = "amount")
    Double fAmount;

    // Hibernates needs empty constructor
    public CostService() {
    }
}
