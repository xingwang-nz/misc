package nz.net.orcon.service;

import java.util.List;

import nz.co.ecngroup.droolstest.helper.Car;
import nz.co.ecngroup.service.DbService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
public class DbServiceTest {
	@Autowired
	@Qualifier("dbService")
	private DbService dbService;
	
	@Test
	public void testDbService() {
		List<Car> cars = dbService.getCars();
		System.out.println(cars.size());
	}
}
