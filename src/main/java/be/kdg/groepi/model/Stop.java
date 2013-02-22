package be.kdg.groepi.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 20/02/13
 * Time: 10:14
 * To change this template use File | Settings | File Templates.
 */
//public enum STOPTYPE {
//    INFORMATIVE (0),
//    INTERACTOVE(1);
//}

@Entity
@Table(name = "T_STOP")
public class Stop {
    @Id
    @GeneratedValue
    @Column(name = "stop_id")
    private Long fId;
    @Column(name = "name")
    private String fName;
    @Column(name = "location")
    private String fLocation;
    @Column(name = "stopnumber")
    private Integer fStopnumber;
    @Column(name = "type")
    private Integer fType;
    @Column(name = "displayMode")
    private Integer fDisplayMode;
    @Column(name = "stopText")
    //@Type(type = "text")
    private String fStopText;

    public Stop() {
    }

    public Stop(String fName, String fLocation, Integer fOrder, Integer fType, Integer fDisplayMode, String fStopText) {
        this.fName = fName;
        this.fLocation = fLocation;
        this.fStopnumber = fOrder;
        this.fType = fType;
        this.fDisplayMode = fDisplayMode;
        this.fStopText = fStopText;
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public String getName() {
        return fName;
    }

    public void setName(String fName) {
        this.fName = fName;
    }

    public String getLocation() {
        return fLocation;
    }

    public void setLocation(String fLocation) {
        this.fLocation = fLocation;
    }

    public Integer getOrder() {
        return fStopnumber;
    }

    public void setOrder(Integer fOrder) {
        this.fStopnumber = fOrder;
    }

    public Integer getType() {
        return fType;
    }

    public void setType(Integer fType) {
        this.fType = fType;
    }

    public Integer getDisplayMode() {
        return fDisplayMode;
    }

    public void setDisplayMode(Integer fDisplayMode) {
        this.fDisplayMode = fDisplayMode;
    }

    public String getStopText() {
        return fStopText;
    }

    public void setStopText(String fStopText) {
        this.fStopText = fStopText;
    }
}
