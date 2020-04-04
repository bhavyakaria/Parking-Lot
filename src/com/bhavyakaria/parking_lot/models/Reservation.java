package com.bhavyakaria.parking_lot.models;

import java.util.Date;

public class Reservation {

    public Vehicle vehicle;
    public ParkingSpot parkingSpot;
    public Date parkedOn;
    public Date parkedTill;

    public Reservation(Vehicle vehicle, ParkingSpot parkingSpot, Date parkedOn, Date parkedTill) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.parkedOn = parkedOn;
        this.parkedTill = parkedTill;
    }


}
