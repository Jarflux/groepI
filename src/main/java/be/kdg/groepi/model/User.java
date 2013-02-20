package be.kdg.groepi.model;

import com.sun.org.apache.xpath.internal.operations.Equals;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Ben Oeyen
 * Long: 6/02/13
 * Class: User
 * Description:
 */
//TODO: boolean veld 'Active' toevoegen

@Entity
@Table(name = "T_USER")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private long fId;
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

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;

        // if (this == user) return false;

        int comparison = this.fName.compareTo(user.getName());
        if (comparison != 0) return false;

        comparison = this.fEmail.compareTo(user.getEmail());
        if (comparison != 0) return false;

        comparison = this.fDateOfBirth.compareTo(user.getDateOfBirth());
        if (comparison != 0) return false;  

        comparison = this.fPassword.compareTo(user.getPassword());
        if (comparison != 0) return false;

        if (this.fProfilePicture != null && user.getProfilePicture() != null) {
            comparison = this.fProfilePicture.compareTo(user.getProfilePicture());
            if (comparison != 0) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
