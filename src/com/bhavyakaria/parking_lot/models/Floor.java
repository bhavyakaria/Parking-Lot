package com.bhavyakaria.parking_lot.models;

import com.bhavyakaria.parking_lot.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    public int number;
    public List<ParkingSpot> parkingSpots = new ArrayList<>();
    public Status status;

    public Floor(int number) {
        this.number = number;
        this.status = Status.OPEN;
    }

    public void addParkingSpot(int num) {
        ParkingSpot parkingSpot = new ParkingSpot(num, true, Status.OPEN);
        this.parkingSpots.add(parkingSpot);
    }

    public ParkingSpot getEmptyParkingSpot() {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isAvailable) {
                parkingSpot.setAvailable(false);

                if (countOfParkingSpotsAvailable() == 0) {
                    status = Status.CLOSED;
                }
                return parkingSpot;
            }
        }
        return null;
    }

    public int countOfParkingSpotsAvailable() {
        int count = 0;
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isAvailable) {
                count++;
            }
        }
        return count;
    }
}
