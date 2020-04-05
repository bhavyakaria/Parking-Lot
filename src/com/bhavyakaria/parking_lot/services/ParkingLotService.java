package com.bhavyakaria.parking_lot.services;

import com.bhavyakaria.parking_lot.enums.Key;
import com.bhavyakaria.parking_lot.enums.ParkingStrategy;
import com.bhavyakaria.parking_lot.enums.Status;
import com.bhavyakaria.parking_lot.models.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    public Building building;
    int NO_OF_FLOORS;
    int NO_OF_PARKING_SPOTS;

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
        }

        building = new Building(1, "Building One", listOfFloors, Status.OPEN, ParkingStrategy.BOTTOM_UP);
    }

    public boolean park(Vehicle vehicle) {
        return building.park(vehicle);
    }

    public void unPark(Vehicle vehicle) {
        building.unPark(vehicle);
    }

    public void changeParkingStrategy(ParkingStrategy parkingStrategy) {
        building.setParkingStrategy(parkingStrategy);
    }

    public int availableParkingSpotsOnAFloor(int floorNumber) {
        return building.floors.get(floorNumber-1).countOfParkingSpotsAvailable();
    }

    public void getBuildingAnalytics(Building building, int floorNumber, Key key, String value) {
        building.getFloorAnalytics(floorNumber, key, value);
    }

}
