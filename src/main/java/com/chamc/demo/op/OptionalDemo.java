package com.chamc.demo.op;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@SuppressWarnings("all")
public class OptionalDemo {

	//private static final String UKN = "Unknown";

	public static void main(String[] args) {
		
	}
	
	public void create(Car car) {
		//Optional<Car> optCar = Optional.empty();
		
		//Optional<Car> optCar = Optional.of(car);
		
		Optional<Car> optCar = Optional.ofNullable(car);
	}
	
	public String getManufacturerNameByMap(Manufacturer manufacturer) {
		Optional<Manufacturer> optManufacturer = Optional.ofNullable(manufacturer);
		
		return optManufacturer.map(Manufacturer::getName).orElse("Unknown");
	}
	
	public String getManufacturerNameByMap(Person person) {
		Optional<Person> optPerson = Optional.of(person);
		
//		Optional<String> name = optPerson.map(Person::getCar)
//				.map(Car::getManufacturer)
//				.map(Manufacturer::getName);
		
		return optPerson.flatMap(Person::getCar)
				.flatMap(Car::getManufacturer)
				.map(Manufacturer::getName)
				.orElse("Unknown");
	}
	
	public Optional<Manufacturer> nullSafeFindCheapestManufacturer1(Optional<Person> person, Optional<Car> car) {
		if (person.isPresent() && car.isPresent()) {
			return Optional.of(findCheapestManufacturer(person.get(), car.get()));
		} else {
			return Optional.empty();
		}
	}

	public Optional<Manufacturer> nullSafeFindCheapestManufacturer(Optional<Person> person, Optional<Car> car) {
		return person.flatMap(p -> car.map(c -> findCheapestManufacturer(p, c)));
	}
	
	private Manufacturer findCheapestManufacturer(Person person, Car car) {
		//  ....
		return null;
	}
	
	public void manufacturerFilter(Manufacturer manufacturer) {
		Optional<Manufacturer> optManufacturer = Optional.ofNullable(manufacturer);
		optManufacturer.filter(tmp -> "Mercedes-Benz".equals(tmp.getName()))
				.ifPresent(x -> System.out.println("ok"));
	}
	
	public void optionalDemo1(Map<String, Object> map) {
		Optional<Object> value = Optional.ofNullable(map.get("key"));
	}
	
	public Integer optionalDemo2(String s) {
		try {
			return Integer.parseInt("aaa");
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static Optional<Integer> optionalDemo3(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	
	public int readDuration(Properties props, String name) {
		String value = props.getProperty(name);
		
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException nfe) {
			}
		}
		return 0;
	}
	
	public int readDurationOptional(Properties props, String name) {
		return Optional.ofNullable(props.getProperty(name)).flatMap(OptionalDemo::optionalDemo3).filter(i -> i > 0)
				.orElse(0);
	}

	
/*
 * 	 ��ָ��
	public String getCarManufacturerName(Person person) {
		return person.getCar().getManufacturer().getName();
	}

 *	����ʽ��顪���������
	public String getCarManufacturerName2(Person person) {
		if (person != null) {
			Car car = person.getCar();
			if (car != null) {
				Manufacturer manufacturer = car.getManufacturer();
				if (manufacturer != null) {
					return manufacturer.getName();
				}
			}
		}
		return UKN;
	}

	public String getCarManufacturerName3(Person person) {
		if (person == null) {
			return UKN;
		}
		Car car = person.getCar();
		if (car == null) {
			return UKN;
		}
		Manufacturer manufacturer = car.getManufacturer();
		if (manufacturer == null) {
			return UKN;
		}
		return Manufacturer.getName();
	}
*/

}
