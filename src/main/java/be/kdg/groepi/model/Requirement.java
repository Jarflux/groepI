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
    @Column(name = "requirement_id")
    long fId;
    @Column(name = "description")
    String fDescription;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User fUser;

    // Hibernates needs empty constructor
    public Requirement() {
    }

    public Requirement(String description) {
        this.fDescription = description;
        this.fUser = null;
    }

    public Requirement(String fDescription, User fUser) {
        this.fDescription = fDescription;
        this.fUser = fUser;
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

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getDescription() {
        return fDescription;
    }
}
