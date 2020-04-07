package com.bhavyakaria.parking_lot.models;

import com.bhavyakaria.parking_lot.enums.Key;
import com.bhavyakaria.parking_lot.enums.Status;
import com.bhavyakaria.parking_lot.exceptions.BaseException;
import com.bhavyakaria.parking_lot.interfaces.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Building {
    public int number;
    public String name;
    public List<Floor> floors;
    public Status status;
    public ParkingStrategy parkingStrategy;
    List<Reservation> reservations = new ArrayList<>();

    public Building(int number, String name, List<Floor> floors, Status status, ParkingStrategy parkingStrategy) {
        this.number = number;
        this.name = name;
        this.floors = floors;
        this.status = status;
        this.parkingStrategy = parkingStrategy;
    }

    public boolean park(Vehicle vehicle) throws BaseException {
        ParkingSpot parkingSpot = getEmptyParkingSpot();
        if (parkingSpot == null) {
            return false;
        } else {
            Reservation reservation = new Reservation(vehicle, parkingSpot, null, null);
            reservations.add(reservation);
            return true;
        }
    }

    public void unPark(Vehicle vehicle) {
        ParkingSpot parkingSpot;
        for (Reservation reservation : reservations) {
            if (reservation.vehicle.equals(vehicle)) {
                parkingSpot = reservation.parkingSpot;
                parkingSpot.setAvailable(true);
                break;
            }
        }
    }

    private ParkingSpot getEmptyParkingSpot() throws BaseException {
        return parkingStrategy.fetchAvailableParkingSpot(this);
    }

    public List<Floor> getAvailableFloors() {
        List<Floor> floorList = new ArrayList<>();
        for (Floor floor : floors) {
            if (floor.status.equals(Status.OPEN)) {
                floorList.add(floor);
            }
        }
        return floorList;
    }

    public void getFloorAnalytics(int floorNumber, Key key, String value) {
        List<Reservation> reservations1 = new ArrayList<>();
        switch (key) {
            case COLOR:
                reservations1 = reservations.stream().filter(reservation -> reservation.vehicle.color.equals(value)).collect(Collectors.toList());
                break;

            case BRAND:
                reservations1 = reservations.stream().filter(reservation -> reservation.vehicle.name.equals(value)).collect(Collectors.toList());
                break;
        }

        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for (Reservation reservation : reservations1) {
            parkingSpots.add(reservation.parkingSpot);
        }

        int count = 0;
        List<ParkingSpot> parkingSpotsOnFloor = floors.get(floorNumber-1).parkingSpots;
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpotsOnFloor.contains(parkingSpot)) {
                count++;
            }
        }
        System.out.println("Count Of " + value + " Vehicles On Floor " + floorNumber + " : " + count);
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }
}

