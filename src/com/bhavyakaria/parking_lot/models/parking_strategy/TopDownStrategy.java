package com.bhavyakaria.parking_lot.models.parking_strategy;

import com.bhavyakaria.parking_lot.constants.Constants;
import com.bhavyakaria.parking_lot.exceptions.BaseException;
import com.bhavyakaria.parking_lot.interfaces.ParkingStrategy;
import com.bhavyakaria.parking_lot.models.Building;
import com.bhavyakaria.parking_lot.models.Floor;
import com.bhavyakaria.parking_lot.models.ParkingSpot;

import java.util.List;

public class TopDownStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot fetchAvailableParkingSpot(Building building) throws BaseException {
        List<Floor> floorList = building.getAvailableFloors();
        if (floorList.size() > 0) {
            return floorList.get(floorList.size()-1).getEmptyParkingSpot();
        } else {
            throw new BaseException(Constants.BASE_ERROR_MSG, Constants.BASE_ERROR_CODE);
        }
    }
}
