package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String mobile;

    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<TripBooking> tripBookingList = new ArrayList<>();

    public Customer() {
    }

    public Customer(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Customer(int customerId, String mobile, String password) {
        this.customerId = customerId;
        this.mobile = mobile;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}