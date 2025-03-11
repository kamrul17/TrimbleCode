package com.trimble.service;

import com.trimble.entities.Admin;

import java.util.List;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    List<Admin> getAllAdmins();
}
