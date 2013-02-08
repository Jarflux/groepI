package be.kdg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User
 * Description:
 */

@Entity
@Table(name = "T_USER")
public class User implements Serializable{
    @Id
    @GeneratedValue
    long fId;
    @Column(name = "name")
    String fName;
    @Column(name = "email")
    String fEmail;
    @Column(name = "password")
    String fPassword;
    @Column(name = "date_of_birth")
    Date fDateOfBirth;

    @OneToMany
    private Set<Trip> organizedTrips;

    // Hibernates needs empty constructor
    public User() {
    }

    public User(String name, String email, String password, Date dateOfBirth){
        this.fName = name;
        this.fEmail = email;
        this.fPassword = password;
        this.fDateOfBirth = dateOfBirth;
    }

    public long getId() {
        return fId;
    }

    public void setId(long fId) {
        this.fId = fId;
    }

    public String getName() {
        return fName;
    }

    public void setName(String name) {
        this.fName = name;
    }

    public String getEmail() {
        return fEmail;
    }

    public void setEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getPassword() {
        return fPassword;
    }

    public void setPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    public Date getDateOfBirth() {
        return fDateOfBirth;
    }

    public void setDateOfBirth(Date fDateOfBirth) {
        this.fDateOfBirth = fDateOfBirth;
    }
}