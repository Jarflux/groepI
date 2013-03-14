package be.kdg.groepi.model;

import be.kdg.groepi.annotations.ExcludeFromGson;
import be.kdg.groepi.utils.CompareUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
@Table(name = "T_TRIP_INSTANCE")
//@Inheritance(strategy=InheritanceType.JOINED)  //Hibernate Inheritance: Table Per Subclass
public class TripInstance implements Serializable, Comparable {

    @Id
    @GeneratedValue
    @Column(name = "trip_instance_id")
    private Long fId;
    @Column(name = "title")
    private String fTitle;
    @Column(name = "description")
    private String fDescription;
    @Column(name = "public")
    private Boolean fAvailable;
    @Column(name = "starttime")
    private long fStartTime;
    @Column(name = "endtime")
    private long fEndTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", nullable = false)
    @ExcludeFromGson
    private Trip fTrip;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @ExcludeFromGson
    private User fOrganiser;
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(value = CascadeType.ALL)
    @JoinTable(name = "T_TRIP_INSTANCE_PARTICIPANT", joinColumns = {
        @JoinColumn(name = "trip_instance_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id")})
    @ExcludeFromGson
    private Set<User> fParticipants = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTripInstance")
    @ExcludeFromGson
    private Set<RequirementInstance> fRequirementInstances = new HashSet<RequirementInstance>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTripInstance")
    @ExcludeFromGson
    private Set<Cost> fCosts = new HashSet<Cost>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTripInstance")
    @ExcludeFromGson
    private Set<Message> fMessages = new HashSet<Message>();

    // Hibernates needs empty constructor
    public TripInstance() {
    }

    public TripInstance(String fTitle, String fDescription, Boolean fAvailable, long fStartTime, long fEndTime, User fOrganiser, Trip fTrip) {
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fAvailable = fAvailable;
        this.fStartTime = fStartTime;
        this.fEndTime = fEndTime;
        this.fTrip = fTrip;
        this.fOrganiser = fOrganiser;
    }

    public TripInstance(TripInstance tripInstance, long fStartTime, long fEndTime) {
        this.fTitle = tripInstance.getTitle();
        this.fDescription = tripInstance.getDescription();
        this.fAvailable = tripInstance.getAvailable();
        this.fTrip = tripInstance.getTrip();
        this.fOrganiser = tripInstance.getOrganiser();
        this.fStartTime = fStartTime;
        this.fEndTime = fEndTime;
    }

    public long getStartTime() {
        return fStartTime;
    }

    public void setStartTime(long fStartTime) {
        this.fStartTime = fStartTime;
    }

    public long getEndTime() {
        return fEndTime;
    }

    public void setEndTime(long fEndTime) {
        this.fEndTime = fEndTime;
    }

    public Trip getTrip() {
        return fTrip;
    }

    public void setTrip(Trip fTrip) {
        this.fTrip = fTrip;
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

    public Boolean getAvailable() {
        return fAvailable;
    }

    public void setAvailable(Boolean fAvailable) {
        this.fAvailable = fAvailable;
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

    public Set<RequirementInstance> getRequirementInstances() {
        return fRequirementInstances;
    }

    public void setRequirementInstances(Set<RequirementInstance> fRequirementInstances) {
        this.fRequirementInstances = fRequirementInstances;
    }

    public Set<Cost> getCosts() {
        return fCosts;
    }

    public void setCosts(Set<Cost> fCosts) {
        this.fCosts = fCosts;
    }

    public Set<Message> getMessages() {
        return fMessages;
    }

    public void setMessages(Set<Message> fMessages) {
        this.fMessages = fMessages;
    }

    public void addParticipantToTripInstance(User user) {
        this.fParticipants.add(user);
    }

    public void removeParticipantFromTripInstance(User user) {
        this.fParticipants.remove(user);
    }

    public void removeCostFromTripInstance(Cost cost) {
        this.fCosts.remove(cost);
    }

    public void addCostToTripInstance(Cost cost) {
        this.fCosts.add(cost);
    }

    public void addRequirementInstanceToTripInstance(RequirementInstance requirementInstance) {
        this.fRequirementInstances.add(requirementInstance);
    }

    public void removeRequirementInstanceFromTripInstance(RequirementInstance requirementInstance) {
        this.fRequirementInstances.remove(requirementInstance);
    }

    public void addMessageToTripInstance(Message message) {
        this.fMessages.add(message);
    }

    public void removeMessageFromTripInstance(Message message) {
        this.fMessages.remove(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        TripInstance tripInstance = (TripInstance) o;
        if (!CompareUtil.compareString(fTitle, tripInstance.getTitle())) {
            return false;
        }
        if (!CompareUtil.compareString(fDescription, tripInstance.getDescription())) {
            return false;
        }
        if (fAvailable != tripInstance.getAvailable()) {
            return false;
        }
        if (!CompareUtil.compareLong(fStartTime, tripInstance.getStartTime())) {
            return false;
        }
        if (!CompareUtil.compareLong(fEndTime, tripInstance.getEndTime())) {
            return false;
        }
        if (!this.fOrganiser.equals(tripInstance.getOrganiser())) {
            return false;
        }
        if (!this.fTrip.equals(tripInstance.getTrip())) {
            return false;
        } /*   
         if (!(CompareUtil.compareSet(this.fParticipants, trip.getParticipants()))) {
         return false;
         } 
         if (!(CompareUtil.compareSet(this.fCosts, trip.getCosts()))) {
         return false;
         }
         if (!(CompareUtil.compareSet(this.fRequirementInstances, trip.getRequirementInstances()))) {
         return false;
         }
         if (!(CompareUtil.compareSet(this.fMessages, trip.getMessages()))) {
         return false;
         } */
        return true;
    }

    @Override
    public int hashCode() {
        return getId().intValue();
    }

    @Override
    public int compareTo(Object o) {
        TripInstance tripInstance = (TripInstance) o;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int dateCompare = dateFormat.format(this.fStartTime).
                compareTo(dateFormat.format(tripInstance.getStartTime()));

        if (dateCompare == 0) {
            return this.fTitle.compareToIgnoreCase(tripInstance.getTitle());
        } else {
            return dateCompare;
        }
    }
}
