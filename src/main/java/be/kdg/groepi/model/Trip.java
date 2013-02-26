package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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
    @Column(name = "trip_id")
    private Long fId;
    @Column(name = "title")
    private String fTitle;
    @Column(name = "description")
    private String fDescription;
    @Column(name = "public")
    private Boolean fAvailable;
    @Column(name = "repeatable")
    private Boolean fRepeatable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fOrganiser;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_PARTICIPANT", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> fParticipants = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_COST", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "cost_id")})
    private Set<Cost> fCosts = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_REQUIREMENT", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "requirement_id")})
    private Set<Requirement> fRequirements = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_MESSAGE", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "message_id")})
    private Set<Message> fMessages = new HashSet<>();

    // Hibernates needs empty constructor
    public Trip() {
    }

    public Trip(String fTitle, String fDescription, Boolean fAvailable, Boolean fRepeatable, User fOrganiser) {
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fAvailable = fAvailable;
        this.fRepeatable = fRepeatable;
        this.fOrganiser = fOrganiser;
        this.fParticipants = fParticipants;
        this.fCosts = fCosts;
        this.fRequirements = fRequirements;
        this.fMessages = fMessages;
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
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

    public Boolean getAvailable() {
        return fAvailable;
    }

    public void setAvailable(Boolean fAvailable) {
        this.fAvailable = fAvailable;
    }

    public Boolean getRepeatable() {
        return fRepeatable;
    }

    public void setRepeatable(Boolean fRepeatable) {
        this.fRepeatable = fRepeatable;
    }

    public User getOrganiser() {
        return fOrganiser;
    }

    public void setOrganiser(User fOrganiser) {
        this.fOrganiser = fOrganiser;
    }

    public Set<User> getParticipants() {
        return fParticipants;
    }

    public void setParticipants(Set<User> fParticipants) {
        this.fParticipants = fParticipants;
    }

    public Set<Cost> getCosts() {
        return fCosts;
    }

    public void setCosts(Set<Cost> fCosts) {
        this.fCosts = fCosts;
    }

    public Set<Requirement> getRequirements() {
        return fRequirements;
    }

    public void setRequirements(Set<Requirement> fRequirements) {
        this.fRequirements = fRequirements;
    }

    public Set<Message> getMessages() {
        return fMessages;
    }

    public void setMessages(Set<Message> fMessages) {
        this.fMessages = fMessages;
    }


    public void addParticipantToTrip(User user) {
        this.fParticipants.add(user);
    }

    public void addCostToTrip(Cost cost) {
        this.fCosts.add(cost);
    }

    public void addRequirementToTrip(Requirement requirement) {
        this.fRequirements.add(requirement);
    }

    public void addMessageToTrip(Message message) {
        this.fMessages.add(message);
    }


    @Override
    public boolean equals(Object o) {
        Trip trip = (Trip) o;
        if (this == trip) return false;

        int comparison = this.fTitle.compareTo(trip.getTitle());
        if (comparison != 0) return false;

        comparison = this.fDescription.compareTo(trip.getDescription());
        if (comparison != 0) return false;

        comparison = this.fAvailable.compareTo(trip.getAvailable());
        if (comparison != 0) return false;

        comparison = this.fRepeatable.compareTo(trip.getRepeatable());
        if (comparison != 0) return false;

        if (!this.fOrganiser.equals(trip.getOrganiser())) {
            return false;
        }

        if (!(CompareUtil.compareSet(this.fParticipants, trip.getParticipants()))) {
            return false;
        }

        if (!(CompareUtil.compareSet(this.fCosts, trip.getCosts()))) {
            return false;
        }

        if (!(CompareUtil.compareSet(this.fRequirements, trip.getRequirements()))) {
            return false;
        }

        if (!(CompareUtil.compareSet(this.fMessages, trip.getMessages()))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
