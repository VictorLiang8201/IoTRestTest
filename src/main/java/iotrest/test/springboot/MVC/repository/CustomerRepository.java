package iotrest.test.springboot.MVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iotrest.test.springboot.MVC.entity.CustomerBean;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer> {
	
}