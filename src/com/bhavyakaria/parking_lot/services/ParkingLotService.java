package com.bhavyakaria.parking_lot.services;

import com.bhavyakaria.parking_lot.enums.Key;
import com.bhavyakaria.parking_lot.enums.Status;
import com.bhavyakaria.parking_lot.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotService {

    public Building building;
    int NO_OF_FLOORS;
    int NO_OF_PARKING_SPOTS;
    List<Floor> availableFloors = new ArrayList<>();
    List<Floor> unavailableFloors = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();

    public ParkingLotService(int NO_OF_FLOORS, int NO_OF_PARKING_SPOTS) {
        this.NO_OF_FLOORS = NO_OF_FLOORS;
        this.NO_OF_PARKING_SPOTS = NO_OF_PARKING_SPOTS;
        List<Floor> listOfFloors = new ArrayList<>();
        for (int i = 0; i < NO_OF_FLOORS; i++) {
            Floor floor = new Floor(i+1);
            for (int j = 0; j < NO_OF_PARKING_SPOTS; j++) {
                floor.addParkingSpot(j+1);
            }
            listOfFloors.add(floor);
            availableFloors.add(floor);
        }

        building = new Building(1, "Building One", listOfFloors, Status.OPEN);
    }

    public boolean park(Vehicle vehicle) {
        ParkingSpot parkingSpot = this.availableFloors.get(0).getEmptyParkingSpot();
        if (parkingSpot == null) {
            return false;
        } else {
            Reservation reservation = new Reservation(vehicle, parkingSpot, null, null);
            reservations.add(reservation);

            if (this.availableFloors.get(0).countOfParkingSpotsAvailable() == 0) {
                Floor floor = this.availableFloors.get(0);
                this.availableFloors.remove(floor);
                this.unavailableFloors.add(floor);
            }
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

    public int availableParkingSpotsOnAFloor(int floorNumber) {
        return building.floors.get(floorNumber-1).countOfParkingSpotsAvailable();
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
        List<ParkingSpot> parkingSpotsOnFloor = building.floors.get(floorNumber-1).parkingSpots;
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpotsOnFloor.contains(parkingSpot)) {
                count++;
            }
        }
        System.out.println("Count Of " + value + " Vehicles On Floor " + floorNumber + " : " + count);
    }

}
