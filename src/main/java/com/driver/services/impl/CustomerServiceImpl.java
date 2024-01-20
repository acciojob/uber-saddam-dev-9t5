package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
		return;
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		customerRepository2.deleteById(customerId);
		return;
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		TripBooking tripBooking = new TripBooking();
		int bookingId = 0;
		Optional<Customer> optionalCustomer = customerRepository2.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			throw new Exception("No cab available!");
		}
		Customer customer = optionalCustomer.get();

		try {
			List<Driver> driverList = driverRepository2.findAll();
			for (Driver driver: driverList) {
				Cab cab = driver.getCab();
				if(cab.getAvailable() == Boolean.TRUE) {
					tripBooking.setCustomer(customer);
					tripBooking.setDriver(driver);
					tripBooking.setFromLocation(fromLocation);
					tripBooking.setToLocation(toLocation);
					tripBooking.setDistanceInKm(distanceInKm);
					tripBooking.setBill(distanceInKm*cab.getPerKmRate());
					tripBooking.setStatus(TripStatus.CONFIRMED);
					tripBooking = tripBookingRepository2.save(tripBooking);
					bookingId = tripBooking.getTripBookingId();
				}
			}
		}
		catch (Exception e) {
			throw new Exception("No cab available!");
		}

		if(bookingId == 0) {
			throw new Exception("No cab available!");
		}
		return tripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> optionalTripBooking = tripBookingRepository2.findById(tripId);
        if(!optionalTripBooking.isEmpty()) {
			optionalTripBooking.get().setStatus(TripStatus.CANCELED);
		}
		return;
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> optionalTripBooking = tripBookingRepository2.findById(tripId);
        if(!optionalTripBooking.isEmpty()) {
			optionalTripBooking.get().setStatus(TripStatus.COMPLETED);
		}
		return;
	}
}
