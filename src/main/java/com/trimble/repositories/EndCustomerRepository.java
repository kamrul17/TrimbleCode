package com.trimble.repositories;

import com.trimble.entities.EndCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndCustomerRepository extends JpaRepository<EndCustomer, Long> {}