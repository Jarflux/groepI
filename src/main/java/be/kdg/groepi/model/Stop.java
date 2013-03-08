package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "longitude")
    private String fLongitude;
    @Column(name = "latitude")
    private String fLatitude;
    @Column(name = "stopnumber")
    private Integer fStopnumber;
    @Column(name = "type")
    private Integer fType;
    @Column(name = "displayMode")
    private Integer fDisplayMode;
    @Column(name = "stopText")
    private String fStopText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip fTrip;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fStop")
    @Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    private List<Answer> fAnswers = LazyList.decorate(new ArrayList<Answer>(), FactoryUtils.instantiateFactory(Answer.class));

    public Stop() {
    }

    public Stop(String fName, String fLongitude, String fLatitude, Integer fStopnumber, Integer fType, Integer fDisplayMode, String fStopText, Trip fTrip) {
        this.fName = fName;
        this.fLongitude = fLongitude;
        this.fLatitude = fLatitude;
        this.fStopnumber = fStopnumber;
        this.fType = fType;
        this.fDisplayMode = fDisplayMode;
        this.fStopText = fStopText;
        this.fTrip = fTrip;
    }

    public Long getId() {
        return fId;
    }

    public String getName() {
        return fName;
    }

    public void setName(String fName) {
        this.fName = fName;
    }

    public String getLongitude() {
        return fLongitude;
    }

    public void setLongitude(String fLongitude) {
        this.fLongitude = fLongitude;
    }

    public String getLatitude() {
        return fLatitude;
    }

    public void setLatitude(String fLatitude) {
        this.fLatitude = fLatitude;
    }

    public Integer getStopnumber() {
        return fStopnumber;
    }

    public void setStopnumber(Integer fStopnumber) {
        this.fStopnumber = fStopnumber;
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

    public Trip getTrip() {
        return fTrip;
    }

    public void setTrip(Trip fTrip) {
        this.fTrip = fTrip;
    }

    public List<Answer> getAnswers() {
        return fAnswers;
    }

    public void setAnswers(List<Answer> fAnswers) {
        this.fAnswers = fAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Stop stop = (Stop) o;
        if (!CompareUtil.compareString(fName, stop.getName())) {
            return false;
        }
        if (!CompareUtil.compareString(fLatitude, stop.getLatitude())) {
            return false;
        }
        if (!CompareUtil.compareString(fLongitude, stop.getLongitude())) {
            return false;
        }
        if (!CompareUtil.compareInteger(fStopnumber, stop.getStopnumber())) {
            return false;
        }
        if (!CompareUtil.compareString(fStopText, stop.getStopText())) {
            return false;
        }
        if (!CompareUtil.compareInteger(fDisplayMode, stop.getDisplayMode())) {
            return false;
        }
        if (!CompareUtil.compareInteger(fType, stop.getType())) {
            return false;
        }

        // cant test because trip is lazyloaded and cant do get<trip on trip proxy
        //   if (!(this.fTrip.equals(stop.getTrip()))) return false;
        return true;
    }
}
