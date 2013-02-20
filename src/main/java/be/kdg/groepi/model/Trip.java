package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip
 * Description:
 */

/*
Hibernate Inheritance: Table Per Subclass
http://viralpatel.net/blogs/hibernate-inheritance-table-per-subclass-annotation-xml-mapping/
Hibernate Inheritance: Table Per Class Hierarchy
http://viralpatel.net/blogs/hibernate-inheritence-table-per-hierarchy-mapping/
*/

@Entity
@Table(name = "T_TRIP")
//@Inheritance(strategy=InheritanceType.JOINED)  //Hibernate Inheritance: Table Per Subclass
public class Trip implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long fId;
    @Column(name = "title")
    private String fTitle;
    @Column(name = "description")
    private String fDescription;
    @Column(name = "public")
    private Boolean fAvailable;
    @Column(name="start")
    private Long fStart;
    @Column(name="end")
    private Long fEnd;

    /*
    @OneToMany
    private Set<User> participants;
    @OneToMany
    private Set<Cost> costs;
    @OneToMany
    private Set<Requirement> requirements;
    @OneToMany
    private Set<Message> messages;
    */

    // Hibernates needs empty constructor
    public Trip() {

    }

    public Trip(String fTitle, String fDescription, Boolean fAvailable, Long fStart, Long fEnd) {
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fAvailable = fAvailable;
        this.fStart = fStart;
        this.fEnd = fEnd;
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

    public Long getStart() {
        return fStart;
    }

    public void setStart(Long fStart) {
        this.fStart = fStart;
    }

    public Long getEnd() {
        return fEnd;
    }

    public void setEnd(Long fEnd) {
        this.fEnd = fEnd;
    }

    @Override
    public boolean equals(Object o) {
        Trip trip = (Trip) o;
        if (this == trip) return false;

        int comparison = this.fTitle.compareTo(trip.getTitle());
        if (comparison != 0) return false;

        comparison = this.fDescription.compareTo(trip.getDescription());
        if (comparison != 0) return false;

        comparison = this.fAvailable.compareTo(trip.isAvailable());
        if (comparison != 0) return false;

        comparison = this.fStart.compareTo(trip.getStart());
        if (comparison != 0) return false;

        comparison = this.fEnd.compareTo(trip.getEnd());
        if (comparison != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
 
}
