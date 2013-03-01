package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTrip")
    @Cascade(CascadeType.DELETE)
    private Set<Stop> fStops = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTrip")
    @Cascade(CascadeType.DELETE)
    private Set<Requirement> fRequirements = new HashSet<>();

    // @ManyToMany(fetch = FetchType.EAGER)
    // @Cascade(CascadeType.ALL)
    // @JoinTable(name = "T_TRIP_REQUIREMENT", joinColumns = {@JoinColumn(name = "trip_id")}, inverseJoinColumns = {@JoinColumn(name = "requirement_id")})
    // private Set<Requirement> fRequirements = new HashSet<>();

    // Hibernates needs empty constructor
    public Trip() {
    }

    public Trip(String fTitle, String fDescription, Boolean fAvailable, Boolean fRepeatable, User fOrganiser) {
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fAvailable = fAvailable;
        this.fRepeatable = fRepeatable;
        this.fOrganiser = fOrganiser;
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

    public Set<Requirement> getRequirements() {
        return fRequirements;
    }

    public void setRequirements(Set<Requirement> fRequirements) {
        this.fRequirements = fRequirements;
    }

    public void addRequirementToTrip(Requirement requirement) {
        this.fRequirements.add(requirement);
    }

    public void removeRequirementFromTrip(Requirement requirement) {
        this.fRequirements.remove(requirement);
    }

    public Set<Stop> getStops() {
        return fStops;
    }

    public void setStops(Set<Stop> fStops) {
        this.fStops = fStops;
    }
    /*public void addStopToTrip(Stop stop) {
        this.fStops.add(stop);
    }
    public void removeStop(Stop stop) {
        this.fStops.add(stop);
    }*/

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
        //  if (!(CompareUtil.compareSet(this.fRequirements, trip.getRequirements()))) {
        //      return false;
        //  }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
