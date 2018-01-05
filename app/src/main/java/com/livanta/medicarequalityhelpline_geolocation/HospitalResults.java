package com.livanta.medicarequalityhelpline_geolocation;


/**
 * Created by bradcollins on 11/19/15.
 */
public class HospitalResults {
    private String id = "";
    private String name = null;
    private String state = "";
    private double lat = 0;
    private double lng = 0;

    HospitalResults(String _id, String _name, String _state, double _lat, double _lng){
        id = _id;
        name = _name;
        state = _state;
        lat = _lat;
        lng = _lng;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public void setState(String _state){
        this.state = _state;
    }

    public String getState() {
        return state;
    }

    public void setLat(Double _lat) {
        this.lat = _lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double _lng) {
        this.lng = _lng;
    }

    public double getLng() {
        return lng;
    }

}
