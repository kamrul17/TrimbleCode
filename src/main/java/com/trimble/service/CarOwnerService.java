package com.trimble.service;

import com.trimble.entities.CarOwner;

import java.util.List;

public interface CarOwnerService {
    CarOwner registerOwner(CarOwner owner);
    List<CarOwner> getAllOwners();
}
