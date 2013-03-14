package com.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 6-3-13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Stop implements Serializable {
    private Long fId;
    private String fName;
    private String fLongitude;
    private String fLatitude;
    private Integer fStopnumber;
    private Integer fType;
    private Integer fDisplayMode;
    private String fStopText;
    private Trip fTrip;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfLongitude() {
        return fLongitude;
    }

    public void setfLongitude(String fLongitude) {
        this.fLongitude = fLongitude;
    }

    public String getfLatitude() {
        return fLatitude;
    }

    public void setfLatitude(String fLatitude) {
        this.fLatitude = fLatitude;
    }

    public Integer getfStopnumber() {
        return fStopnumber;
    }

    public void setfStopnumber(Integer fStopnumber) {
        this.fStopnumber = fStopnumber;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfDisplayMode() {
        return fDisplayMode;
    }

    public void setfDisplayMode(Integer fDisplayMode) {
        this.fDisplayMode = fDisplayMode;
    }

    public String getfStopText() {
        return fStopText;
    }

    public void setfStopText(String fStopText) {
        this.fStopText = fStopText;
    }

    public Trip getfTrip() {
        return fTrip;
    }

    public void setfTrip(Trip fTrip) {
        this.fTrip = fTrip;
    }
}
