package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.

		Driver newDriver = new Driver();
		newDriver.setMobile(mobile);
		newDriver.setPassword(password);
		//newDriver.setCab(createdCab);
		newDriver = driverRepository3.save(newDriver);

		Cab newCab = new Cab();
		newCab.setPerKmRate(10);
		newCab.setAvailable(Boolean.TRUE);
		newCab.setDriver(newDriver);
		newCab = cabRepository3.save(newCab);
		return;
	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function
		driverRepository3.deleteById(driverId);
		return;
	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
		Optional<Driver> optionalDriver = driverRepository3.findById(driverId);
		if(!optionalDriver.isEmpty()) {
			Driver driver = optionalDriver.get();
			Cab cab = driver.getCab();
			cab.setAvailable(Boolean.FALSE);
			cabRepository3.save(cab);
		}
		return;
	}
}
