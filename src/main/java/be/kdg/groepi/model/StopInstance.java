package be.kdg.groepi.model;

import javax.persistence.*;

//public enum STOPTYPE {
//    INFORMATIVE (0),
//    INTERACTOVE(1);
//}

@Entity
@Table(name = "T_STOP_INSTANCE")
public class StopInstance {
    @Id
    @GeneratedValue
    @Column(name = "stop_instance_id")
    private Long fId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop fStop;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance fTripInstance;

    public StopInstance() {
    }

    public StopInstance(Stop fStop, TripInstance fTripInstance) {
        this.fStop = fStop;
        this.fTripInstance = fTripInstance;
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public Stop getStop() {
        return fStop;
    }

    public void setStop(Stop fStop) {
        this.fStop = fStop;
    }

    public TripInstance getTripInstance() {
        return fTripInstance;
    }

    public void setTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }

    @Override
    public boolean equals(Object o) {
        StopInstance stopInstance = (StopInstance) o;
        if (this == stopInstance) return false;

        if (!fStop.getId().equals(stopInstance.getStop().getId())) return false;
        if (!fTripInstance.getId().equals(stopInstance.getTripInstance().getId())) return false;

        return true;
    }
}
