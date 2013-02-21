package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import be.kdg.groepi.utils.CompareUtil;

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
    @Column(name = "trip_id")
    private Long fId;
    @Column(name = "title")
    private String fTitle;
    @Column(name = "description")
    private String fDescription;
    @Column(name = "public")
    private Boolean fAvailable;
    @Column(name = "start")
    private Long fStart;
    @Column(name = "end")
    private Long fEnd;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fOrganiser;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_PARTICIPANT", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> fParticipants = new HashSet<User>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_COST", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "cost_id")})
    private Set<User> fCosts = new HashSet<User>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_REQUIREMENT", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "requirement_id")})
    private Set<User> fRequirements = new HashSet<User>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_MESSAGE", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "message_id")})
    private Set<User> fMessages = new HashSet<User>();

    // Hibernates needs empty constructor
    public Trip() {
    }

    public Trip(String fTitle, String fDescription, Boolean fAvailable, Long fStart, Long fEnd, User fOrganiser) {
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fAvailable = fAvailable;
        this.fStart = fStart;
        this.fEnd = fEnd;
        this.fOrganiser = fOrganiser;
    }

    public Long setId() {
        return fId;
    }

    public Long getId() {
        return fId;
    }

    public String getTitle() {
        return fTitle;
    }

    public void setTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public String getDescription() {
        return fDescription;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
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

    public User getOrganiser() {
        return fOrganiser;
    }

    public void setOrganiser(User fOrganiser) {
        this.fOrganiser = fOrganiser;
    }

    public Set<User> getParticipants() {
        return this.fParticipants;
    }

    public void setParticipants(Set<User> participants) {
        this.fParticipants = participants;
    }

    public Set<User> getCosts() {
        return fCosts;
    }

    public void setCosts(Set<User> fCosts) {
        this.fCosts = fCosts;
    }

    public Set<User> getRequirements() {
        return fRequirements;
    }

    public void setRequirements(Set<User> fRequirements) {
        this.fRequirements = fRequirements;
    }

    public Set<User> getMessages() {
        return fMessages;
    }

    public void setMessages(Set<User> fMessages) {
        this.fMessages = fMessages;
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


        if (!(CompareUtil.compareSet(this.fParticipants, trip.getParticipants()))){
            return false;
        }

        if (!(CompareUtil.compareSet(this.fCosts, trip.getCosts()))){
            return false;
        }

        if (!(CompareUtil.compareSet(this.fRequirements, trip.getRequirements()))){
            return false;
        }

        if (!(CompareUtil.compareSet(this.fMessages, trip.getMessages()))){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
