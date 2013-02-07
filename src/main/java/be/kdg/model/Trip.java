package be.kdg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip
 * Description:
 */

@Entity
@Table(name = "T_TRIP")
public class Trip implements Serializable{
    @Id
    @GeneratedValue
    private Long fId;
    @Column(name = "title")
    private String fTitle;
    @Column(name = "description")
    private String fDescription;
    @Column(name = "public")
    private Boolean fAvailable;

    @OneToMany
    private Set<User> participants;
    @OneToMany
    private Set<Cost> costs;
    @OneToMany
    private Set<Requirement> requirements;
    @OneToMany
    private Set<Message> messages;

    // Hibernates needs empty constructor
    public Trip() {
    }

    public Trip(String title, String description, boolean available) {
        this.fTitle = title;
        this.fDescription = description;
        this.fAvailable = available;
    }

    public Long setId() {
        return fId;
    }

    public Long getId() {
        return fId;
    }

    public void setTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public String getTitle() {
        return fTitle;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getDescription() {
        return fDescription;
    }

    public void setAvailable(Boolean fAvailable) {
        this.fAvailable = fAvailable;
    }

    public Boolean isAvailable() {
        return fAvailable;
    }


}
