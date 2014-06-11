package nz.co.ecngroup.droolstest.fact;

import nz.co.ecngroup.droolstest.helper.Car;
import nz.co.ecngroup.droolstest.helper.Club;

public class Applicant {
	private String name;
	private int age;
	private boolean valid;
	private String sex;
	private String comment;
	
	private Car car;
	
	private Club club;

	public Applicant(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	public Applicant() {
		
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Applicant [age=" + age + ", coment=" + comment + ", name=" + name + ", sex=" + sex + ", valid=" + valid
				+ "]";
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
