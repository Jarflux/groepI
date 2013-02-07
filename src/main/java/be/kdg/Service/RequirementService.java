package be.kdg.Service;

import javax.persistence.*;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: RequirementService
 * Description:
 */

@Entity
@Table(name = "T_REQUIREMENT")
public class RequirementService {
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "description")
    String fDescription;

    // Hibernates needs empty constructor
    public RequirementService() {
    }
}
