package com.trimble.service;

import com.trimble.entities.EndCustomer;

import java.util.List;

public interface EndCustomerService {
    EndCustomer registerCustomer(EndCustomer customer);
    List<EndCustomer> getAllCustomers();
}
