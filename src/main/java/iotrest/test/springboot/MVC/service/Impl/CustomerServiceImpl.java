package iotrest.test.springboot.MVC.service.Impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import iotrest.test.springboot.MVC.entity.CustomerBean;
import iotrest.test.springboot.MVC.repository.CustomerRepository;
import iotrest.test.springboot.MVC.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	CustomerRepository customerRepository;
	
	@Override
	public CustomerBean getById(Integer id) {
		return customerRepository.getById(id);
	}
	
	@Override
	public void save(CustomerBean customerBean) {
		customerRepository.save(customerBean);
	}
	
}