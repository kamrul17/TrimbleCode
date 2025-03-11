package com.trimble.service.impl;

import com.trimble.entities.Admin;
import com.trimble.repositories.AdminRepository;
import com.trimble.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository repository;

    @Override
    public Admin registerAdmin(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }
}
