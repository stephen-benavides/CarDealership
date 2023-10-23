package project_0.layers.dao;

import java.util.List;

import project_0.layers.models.Car;

public interface CarDAO {

	// DAO to represent all the changes that can be done to the database
	
	/*
	 * add cars
	 * view cars
	 * view all cars
	 * delete cars
	 */
	
	//Add cars
	public boolean addCar(Car c);
	
	//View cars
	public Car getCar(int id);
	public Car getCar(String name);
	
	//View All cars
	public List<Car> getAllCars();
	
	//Delete cars
	public boolean deleteCar(int id);
	
	//get my own cars
	public List<Car> getMyCars(int u_id);
	
}
