package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int perKmRate;

    private boolean available;

    @JoinColumn
    @OneToOne
    private Driver driver;

    public Cab() {

    }

    public Cab(int perKmRate, boolean available) {
        this.perKmRate = perKmRate;
        this.available = available;
    }

    public Cab(int cabId, int perKmRate, boolean available) {
        this.id = cabId;
        this.perKmRate = perKmRate;
        this.available = available;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public int getId() {
        return id;
    }

    public boolean getAvailable() {
        return available;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setId(int id) {
        this.id = id;
    }
}