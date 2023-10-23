package project_0.layers.service;

import java.util.List;

import project_0.layers.dao.CarDAO;
import project_0.layers.dao.CarDAOImpl;
import project_0.layers.models.Car;

public class CarService {

	public static CarDAO cd = new CarDAOImpl();
	
	public static boolean addCar(Car c) {
		return cd.addCar(c);
	}
	
	public static Car getCar(int id) {
		return cd.getCar(id);
	}
	
	public static Car getCar(String name) {
		return cd.getCar(name);
	}
	
	public static List<Car> getAllCars(){
		return cd.getAllCars();
	}
	
	public static boolean deleteCar(int id) {
		return cd.deleteCar(id);
	}
	
	public static List<Car> getMyCars(int u_id){
		return cd.getMyCars(u_id);
	}
}
