package com.apap.tutorial7.service;

import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;

/**
 * 
 * @author faisalridwan
 * CarService
 */
public interface CarService {
	void addCar(CarModel car);
	void deleteCar(long carId);
	void editCar(CarModel newCar, Long id);
	Optional<CarModel> getCarDetailById(Long id);
}
