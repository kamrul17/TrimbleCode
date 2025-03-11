package com.trimble.service.impl;

import com.trimble.entities.CarOwner;
import com.trimble.repositories.CarOwnerRepository;
import com.trimble.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    @Autowired
    private CarOwnerRepository repository;
    @Override
    public CarOwner registerOwner(CarOwner owner) {
        return repository.save(owner);
    }

    @Override
    public List<CarOwner> getAllOwners() {
        return repository.findAll();
    }
}
