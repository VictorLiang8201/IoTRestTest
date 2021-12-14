package iotrest.test.springboot.MVC.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.User;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

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
			User user = restClient.getUser().orElseThrow(() -> new IllegalStateException("No logged in user has been found"));
			do {
				pageData = restClient.getCustomerDevices(user.getCustomerId(), "", pageLink);
				for (Device device : pageData.getData()) {
					devices.add(device);
				}
				pageLink.nextPageLink();
			} while(pageData.hasNext());
		} else {
				do {
					pageData = restClient.getTenantDevices("", pageLink);
					for (Device device : pageData.getData()) {
						devices.add(device);
					}
					pageLink.nextPageLink();
				} while(pageData.hasNext());
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
	
}