package com.example.EStore.Repository;

import com.example.EStore.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailId(String customerEmailId);
}
