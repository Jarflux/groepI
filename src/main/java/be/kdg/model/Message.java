package be.kdg.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class:
 * Description:
 */

@Entity
@Table(name = "T_MESSAGE")
public class Message {
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "content")
    String fContent;
    @Column(name = "date")
    Date fDate;

    // Hibernates needs empty constructor
    public Message() {
    }
}
