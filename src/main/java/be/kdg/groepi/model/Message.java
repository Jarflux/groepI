package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Message
 * Description:
 */

@Entity
@Table(name = "T_MESSAGE")
public class Message implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "message_id")
    long fId;
    @Column(name = "content")
    String fContent;
    @Column(name = "date")
    Long fDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance fTripInstance;

    // Hibernates needs empty constructor
    public Message() {
    }

    public Message(String fContent, Long fDate, TripInstance fTripInstance) {
        this.fContent = fContent;
        this.fDate = fDate;
        this.fTripInstance = fTripInstance;
    }

    public long getId() {
        return fId;
    }

    public void setId(long fId) {
        this.fId = fId;
    }

    public String getContent() {
        return fContent;
    }

    public void setContent(String fContent) {
        this.fContent = fContent;
    }

    public Long getDate() {
        return fDate;
    }

    public void setDate(Long fDate) {
        this.fDate = fDate;
    }

    public TripInstance getTripInstance() {
        return fTripInstance;
    }

    public void setTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }

    @Override
    public String toString() {
        return "Message:" + fDate + ": " + fContent;
    }
}
