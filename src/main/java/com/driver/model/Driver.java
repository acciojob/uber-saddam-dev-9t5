package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    private String mobile;

    private String password;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    public Cab cab;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    public List<TripBooking> tripBookingList = new ArrayList<>();

    public Driver() {

    }

    public Driver(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Driver(int driverId, String mobile, String password) {
        this.driverId = driverId;
        this.mobile = mobile;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public Cab getCab() {
        return cab;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }
}