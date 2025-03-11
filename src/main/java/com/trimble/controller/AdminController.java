package com.trimble.controller;
import com.trimble.entities.*;
import com.trimble.service.*;

import com.trimble.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService service;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    public AdminController(AdminServiceImpl service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        logger.info("Received request to register admin: {}", admin);
        Admin admin1 = service.registerAdmin(admin);
        if (admin1 != null) {
            logger.info("Admin registered successfully: {}", admin1);
            return new ResponseEntity<>(admin1, HttpStatus.CREATED);
        } else {
            logger.warn("Admin registration failed.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        logger.info("Fetching all admins");
        List<Admin> allAdmins = service.getAllAdmins();
        if (allAdmins != null && !allAdmins.isEmpty()) {
            logger.info("Fetched {} admins", allAdmins.size());
            return new ResponseEntity<>(allAdmins, HttpStatus.OK);
        } else {
            logger.warn("No admins found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
