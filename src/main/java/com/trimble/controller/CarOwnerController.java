package com.trimble.controller;
import com.trimble.entities.*;
import com.trimble.service.*;

import com.trimble.service.impl.CarOwnerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-owner")
public class CarOwnerController {
private static final Logger logger= LoggerFactory.getLogger(CarOwnerController.class);
    private CarOwnerService service;

    public CarOwnerController(CarOwnerServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<CarOwner > registerOwner(@RequestBody CarOwner owner) {
        logger.info("Received request to register owner: {}",owner);
         CarOwner carOwner = service.registerOwner(owner);
         if (carOwner!=null){
             logger.info("CarOwner registered successfully: {}", carOwner);
             return new ResponseEntity<>(carOwner, HttpStatus.CREATED);
         }else {
             logger.warn("CarOwner registration Failed.");
             return new ResponseEntity<>( HttpStatus.NO_CONTENT);
         }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarOwner>> getAllOwners() {
        logger.info("Fetching all admins");
         List<CarOwner> allOwners = service.getAllOwners();
        if (allOwners!=null){
            logger.info("Fetched {} CarOwner", allOwners.size());
            return new ResponseEntity<>(allOwners, HttpStatus.CREATED);
        }else {
            logger.warn("No CarOwner found.");
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
    }
}

