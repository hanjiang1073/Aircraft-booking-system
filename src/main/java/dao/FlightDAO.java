package dao;

import java.util.List;

import pojo.Flight;

public interface FlightDAO {
	
 List<Flight> getFlight(
         String departureCity,
         String departureDate, 
         String arrivalCity, 
         String arrivetime);
 
}