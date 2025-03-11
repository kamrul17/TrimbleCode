package com.trimble.controller;
import com.trimble.entities.*;
import com.trimble.service.*;

import com.trimble.service.impl.EndCustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class EndCustomerController {

    private static final Logger logger= LoggerFactory.getLogger(EndCustomerController.class);
    private EndCustomerService service;

    public EndCustomerController(EndCustomerServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<EndCustomer> registerCustomer(@RequestBody EndCustomer customer) {
        logger.info("Received request to register EndCustomer: {}",customer);
         EndCustomer endCustomer = service.registerCustomer(customer);
        if (endCustomer!=null){
            logger.info("endCustomer registered successfully: {}", endCustomer);
            return new ResponseEntity<>(endCustomer, HttpStatus.CREATED);
        }else {
            logger.warn("endCustomer registration Failed.");
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EndCustomer>> getAllCustomers() {
        logger.info("Fetching all Customers");
         List<EndCustomer> allCustomers = service.getAllCustomers();
        if (allCustomers!=null){
            logger.info("Fetched {} allCustomers", allCustomers.size());
            return new ResponseEntity<>(allCustomers, HttpStatus.CREATED);
        }else {
            logger.warn("No allCustomers found.");
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
    }
}
