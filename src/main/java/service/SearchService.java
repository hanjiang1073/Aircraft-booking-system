package service;

import java.util.List;

import pojo.Flight;

public interface SearchService {
	
 List<Flight> searchFlight(
		             String departureCity,
		             String departureDate, 
		             String arrivalCity);
 
}
