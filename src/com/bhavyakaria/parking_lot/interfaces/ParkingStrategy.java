package com.bhavyakaria.parking_lot.interfaces;

import com.bhavyakaria.parking_lot.exceptions.BaseException;
import com.bhavyakaria.parking_lot.models.Building;
import com.bhavyakaria.parking_lot.models.ParkingSpot;

public interface ParkingStrategy {

    public ParkingSpot fetchAvailableParkingSpot(Building building) throws BaseException;
}
