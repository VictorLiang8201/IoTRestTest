package iotrest.test.springboot.MVC.service;

import iotrest.test.springboot.MVC.entity.CustomerBean;

public interface CustomerService {

	CustomerBean getById(Integer id);

	void save(CustomerBean customerBean);

}