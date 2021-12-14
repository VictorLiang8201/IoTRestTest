package iotrest.test.springboot.MVC.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.Customer;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.User;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.common.data.security.Authority;

import iotrest.test.springboot.MVC.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class CustomerController {

	CustomerService customerService;

	@GetMapping("/devices")
	public Map<String, Object> getDevices(
			@RequestParam(name = "account") String account,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "type") String type
	) {
		Map<String, Object> map = new HashMap<>();

		String url = "https://demo.thingsboard.io:443";
		RestClient restClient = new RestClient(url);
		restClient.login(account, password);
		List<Device> devices = new ArrayList<>();
		PageData<Device> pageData;
		PageLink pageLink = new PageLink(10);
		if (type.equals("c")) {
			User user = restClient.getUser().orElse(null);
			if (user != null) {
				do {
					pageData = restClient.getCustomerDevices(user.getCustomerId(), "", pageLink);
					for (Device device : pageData.getData()) {
						devices.add(device);
					}
					pageLink = pageLink.nextPageLink();
				} while (pageData.hasNext());
			}
		} else {
			do {
				pageData = restClient.getTenantDevices("", pageLink);
				for (Device device : pageData.getData()) {
					devices.add(device);
				}
				pageLink = pageLink.nextPageLink();
			} while (pageData.hasNext());
		}
		restClient.logout();
		restClient.close();
		if (devices.size() > 0) {
			map.put("devices", devices);
		} else {
			map.put("devices", "撈取裝置失敗");
		}
		return map;
	}

	@PostMapping("/customers")
	public void saveCustomer(
			@RequestParam(name = "custTitle") String custTitle,
			@RequestParam(name = "userEmail") String userEmail) {
		
		// Login in
		String url = "https://demo.thingsboard.io:443";
		RestClient restClient = new RestClient(url);
		restClient.login("victorliang8201@gmail.com", "ZAsw6KNji9GEcx1D");
		
		// Customer Register
		Customer customer = new Customer();
		customer.setTitle(custTitle);
		restClient.saveCustomer(customer);
		CustomerId customerId = null;
		
		// User Register
		User user = new User();
		PageLink pageLink = new PageLink(10);
		PageData<Customer> pageData = restClient.getCustomers(pageLink);
		do {
			List<Customer> customers = restClient.getCustomers(pageLink).getData();
			for (Customer cust : customers) {
				if (cust.getTitle().equals(custTitle)) {
					customerId = cust.getId();
					break;
				}
			}
			pageLink = pageLink.nextPageLink();
		} while (pageData.hasNext());
		user.setEmail(userEmail);
		user.setCustomerId(customerId);
		user.setAuthority(Authority.CUSTOMER_USER);
		restClient.saveUser(user, true);
		restClient.logout();
		restClient.close();
	}

}