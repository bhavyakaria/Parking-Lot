package com.bhavyakaria.parking_lot.models;

import com.bhavyakaria.parking_lot.enums.Key;
import com.bhavyakaria.parking_lot.enums.ParkingStrategy;
import com.bhavyakaria.parking_lot.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Building {
    public int number;
    public String name;
    public List<Floor> floors;
    public Status status;
    public ParkingStrategy parkingStrategy;

    List<Floor> availableFloors = new ArrayList<>();
    List<Floor> unavailableFloors = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();

    public Building(int number, String name, List<Floor> floors, Status status, ParkingStrategy parkingStrategy) {
        this.number = number;
        this.name = name;
        this.floors = floors;
        this.status = status;
        this.availableFloors = floors;
        this.parkingStrategy = parkingStrategy;
    }

    public boolean park(Vehicle vehicle) {
        ParkingSpot parkingSpot = getEmptyParkingSpot();
        if (parkingSpot == null) {
            return false;
        } else {
            Reservation reservation = new Reservation(vehicle, parkingSpot, null, null);
            reservations.add(reservation);

            updateAvailableFloorCount();
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

    private void updateAvailableFloorCount() {
        Floor floor;
        switch (parkingStrategy) {
            case BOTTOM_UP: floor = this.availableFloors.get(0);
                            break;

            case TOP_BOTTOM: floor = this.availableFloors.get(this.availableFloors.size() - 1);
                             break;
            default:
                throw new IllegalStateException("Unexpected value: " + parkingStrategy);
        }

        if (floor.countOfParkingSpotsAvailable() == 0) {
            this.availableFloors.remove(floor);
            this.unavailableFloors.add(floor);
        }
    }

    private ParkingSpot getEmptyParkingSpot() {
        ParkingSpot parkingSpot;
        switch (parkingStrategy) {
            case BOTTOM_UP: parkingSpot = this.availableFloors.get(0).getEmptyParkingSpot();
                            break;

            case TOP_BOTTOM: parkingSpot = this.availableFloors.get(this.availableFloors.size()-1).getEmptyParkingSpot();
                             break;
            default:
                throw new IllegalStateException("Unexpected value: " + parkingStrategy);
        }
        return parkingSpot;
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

