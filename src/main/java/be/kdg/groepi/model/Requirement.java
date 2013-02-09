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
@Table(name = "T_REQUIREMENT")
public class Requirement implements Serializable {
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "description")
    String fDescription;

    // Hibernates needs empty constructor
    public Requirement() {
    }

    public Requirement(String description) {
        this.fDescription = description;
    }

    public Long setId() {
        return fId;
    }

    public Long getId() {
        return fId;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getDescription() {
        return fDescription;
    }
}
