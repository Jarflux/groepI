package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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

    // Hibernates needs empty constructor
    public Message() {
    }

    public Message(String content, Long date) {
        this.fContent = content;
        this.fDate = date;
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

    @Override
    public String toString() {
        return "Message:" + fDate + ": " + fContent;
    }
}
