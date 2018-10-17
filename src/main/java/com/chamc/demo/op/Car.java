package com.chamc.demo.op;

import java.util.Optional;

public class Car {
	//private Optional<Manufacturer> manufacturer;
	//public Optional<Manufacturer> getManufacturer() { return manufacturer; }
	
	private Manufacturer manufacturer;
	public Optional<Manufacturer> getManufacturer() {
		return Optional.ofNullable(manufacturer);
	}
}
