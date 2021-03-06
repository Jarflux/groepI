package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import java.io.Serializable;
import javax.persistence.*;
import java.text.SimpleDateFormat;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Message
 * Description:
 */
@Entity
@Table(name = "T_MESSAGE")
public class
        Message implements Serializable, Comparable {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    long fId;
    @Column(name = "content")
    String fContent;
    @Column(name = "date")
    Long fDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_instance_id", nullable = true)
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
        this.fTripInstance.addMessageToTripInstance(this);
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
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Message message = (Message) o;
        if (!CompareUtil.compareString(fContent, message.getContent())) {
            return false;
        }
        if (!CompareUtil.compareLong(fDate, message.getDate())) {
            return false;
        }
        if (!fTripInstance.equals(message.getTripInstance())) {
            return false;
        }
        if (!fUser.equals(message.getUser())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Message message = (Message) o;
        //Newest messages come first
        return -this.getDate().compareTo(message.getDate());

    }
}
