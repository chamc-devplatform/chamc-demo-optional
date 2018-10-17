package com.chamc.demo.op;

import java.util.Optional;

public class Person {
	//private Optional<Car> car;
	//public Optional<Car> getCar() { return car; }
	
	private Car car;
	public Optional<Car> getCar() {
		return Optional.ofNullable(car);
	}
}
