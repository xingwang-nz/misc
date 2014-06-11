package nz.co.ecngroup.service;

import java.util.List;

import nz.co.ecngroup.droolstest.helper.Car;

public interface DbService {
	
	
	public boolean isValid(int age);
	
	public List<Car> getCars();
}
