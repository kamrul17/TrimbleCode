package com.trimble.service.impl;

import com.trimble.entities.EndCustomer;
import com.trimble.repositories.EndCustomerRepository;
import com.trimble.service.EndCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EndCustomerServiceImpl implements EndCustomerService {
    @Autowired
    private EndCustomerRepository endCustomerRepository;

    @Override
    public EndCustomer registerCustomer(EndCustomer customer) {
        return  endCustomerRepository.save(customer);
    }

    @Override
    public List<EndCustomer> getAllCustomers() {
return endCustomerRepository.findAll();

    }




}
