package be.kdg.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User
 * Description:
 */

@Entity
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String fEmail;
    @Column(name = "password")
    String fPassword;
    @Column(name = "date_of_birth")
    Date fDateOfBirth;

    // Hibernates needs empty constructor
    public User() {
    }
}
