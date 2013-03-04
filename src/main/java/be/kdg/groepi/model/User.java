package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User
 * Description:
 */
//TODO: boolean veld 'Active' toevoegen
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long fId;
    @Column(name = "name")
    private String fName;
    @Column(name = "email")
    private String fEmail;
    @Column(name = "password")
    private String fPassword;
    @Column(name = "date_of_birth")
    private Long fDateOfBirth;
    @Column(name = "profile_picture")
    private String fProfilePicture;
    @Column(name = "passwordResetString")
    private String fPasswordResetString;
    @Column(name = "passwordResetTimestamp")
    private Timestamp fPasswordResetTimestamp;

    //@OneToMany
    //private Set<Trip> organizedTrips;
    // Hibernates needs empty constructor
    public User() {
    }

    public User(String name, String email, String password, Long dateOfBirth) {
        this.fName = name;
        this.fEmail = email;
        this.fPassword = password;
        this.fDateOfBirth = dateOfBirth;
    }

    public long getId() {
        return fId;
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

    public Long getDateOfBirth() {
        return fDateOfBirth;
    }

    public void setDateOfBirth(Long fDateOfBirth) {
        this.fDateOfBirth = fDateOfBirth;
    }

    public String getProfilePicture() {
        return fProfilePicture;
    }

    public void setProfilePicture(String fProfilePicture) {
        this.fProfilePicture = fProfilePicture;
    }

    public String getPasswordResetString() {
        return fPasswordResetString;
    }

    public void setPasswordResetString(String fPasswordResetString) {
        this.fPasswordResetString = fPasswordResetString;
    }

    public Timestamp getPasswordResetTimestamp() {
        return fPasswordResetTimestamp;
    }

    public void setPasswordResetTimestamp(Timestamp fPasswordResetTimestamp) {
        this.fPasswordResetTimestamp = fPasswordResetTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        User user = (User) o;
        if (!CompareUtil.compareString(fName, user.getName())) {
            return false;
        }
        if (!CompareUtil.compareString(fEmail, user.getEmail())) {
            return false;
        }
        if (!CompareUtil.compareLong(fDateOfBirth, user.getDateOfBirth())) {
            return false;
        }
        if (!CompareUtil.compareString(fPassword, user.getPassword())) {
            return false;
        }
        if (!CompareUtil.compareString(fPasswordResetString, user.getPasswordResetString())) {
            return false;
        }
        if (!CompareUtil.compareTimestamp(fPasswordResetTimestamp, user.getPasswordResetTimestamp())) {
            return false;
        }
        if (!CompareUtil.compareString(fProfilePicture, user.getProfilePicture())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
