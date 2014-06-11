package nz.co.ecngroup.service;

import java.util.List;

import nz.co.ecngroup.droolstest.helper.Car;

public class DbServiceImpl implements DbService {

	@Override
	public boolean isValid(int age) {
		return age < adultAge ? false : true;
	}
	
	private int adultAge;
	public void setAdultAge(int adultAge) {
		this.adultAge = adultAge;
	}
	
	
	private List<Car> cars;
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	

}
