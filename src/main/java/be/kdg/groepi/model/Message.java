package be.kdg.groepi.model;

import be.kdg.groepi.utils.DateUtil;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fUser;

    // Hibernates needs empty constructor
    public Message() {
    }

    public Message(String fContent, Long fDate, TripInstance fTripInstance, User fUser) {
        this.fContent = fContent;
        this.fDate = fDate;
        this.fTripInstance = fTripInstance;
        this.fUser = fUser;
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

    public User getUser() {
        return fUser;
    }

    public void setUser(User fUser) {
        this.fUser = fUser;
    }

    @Override
    public String toString() {
        return "Message:" + fDate + ": " + fContent;
    }


    @Override
    public boolean equals(Object o) {
        Message message = (Message) o;

        int comparison = this.fContent.compareTo(message.getContent());
        if (comparison != 0) return false;

        comparison = this.fDate.compareTo(message.getDate());
        if (comparison != 0) return false;

        if (!this.fTripInstance.getId().equals(message.fTripInstance.getId())) return false;
        if (this.fUser.getId() != (message.getUser().getId())) return false;

        return true;
    }
}
