package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User
 * Description:
 */
//TODO: boolean veld 'Active' toevoegen

@Entity
@Table(name = "T_USER")
public class User implements Serializable, Comparable {
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
    private Date fDateOfBirth;
    @Column(name = "profile_picture")
    private String fProfilePicture;

    //@OneToMany
    //private Set<Trip> organizedTrips;

    // Hibernates needs empty constructor
    public User() {
    }

    public User(String name, String email, String password, Date dateOfBirth) {
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

    public String getProfilePicture() {
        return fProfilePicture;
    }

    public void setProfilePicture(String fProfilePicture) {
        this.fProfilePicture = fProfilePicture;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;

        if (this == user) return 0;

        int comparison = this.fName.compareTo(user.getName());
        if (comparison != 0) return comparison;

        comparison = this.fEmail.compareTo(user.getEmail());
        if (comparison != 0) return comparison;

/*        if (this.fDateOfBirth.before(user.())) return -1;
        if (this.fDateOfBirth.after(user.getDateOfBirth())) return 1;*/

        //if (this.fDateOfBirth.)
       // this.fDateOfBirth.

        comparison = this.fDateOfBirth.compareTo(user.getDateOfBirth());
        if (comparison != 0) return comparison;

        comparison = this.fPassword.compareTo(user.getPassword());
        if (comparison != 0) return comparison;

        if (this.fProfilePicture != null) {
            if (user.getProfilePicture() != null) {
                comparison = this.fProfilePicture.compareTo(user.getProfilePicture());
                if (comparison != 0) return comparison;
            }
            return 1;
        }

        return 0;
    }
}
