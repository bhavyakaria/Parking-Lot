package com.bhavyakaria.parking_lot.models;

import com.bhavyakaria.parking_lot.enums.VehicleType;

public class Vehicle {
    public VehicleType vehicleType;
    public String name;
    public User user;
    public String registrationNumber;
    public String color;

    public Vehicle(VehicleType vehicleType, String name, User user, String registrationNumber, String color) {
        this.vehicleType = vehicleType;
        this.name = name;
        this.user = user;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
}
