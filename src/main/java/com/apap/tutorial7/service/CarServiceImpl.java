package com.apap.tutorial7.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.CarDB;

/**
 * 
 * @author faisalridwan
 * CarServiceImpl
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDB carDb;
	
	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		carDb.save(car);
	}

	@Override
	public void deleteCar(long carId) {
		// TODO Auto-generated method stub
		carDb.deleteById(carId);
	}

	@Override
	public Optional<CarModel> getCarDetailById(Long id) {
		// TODO Auto-generated method stub
		return carDb.findById(id);
	}

	@Override
	public void editCar(CarModel newCar, Long id) {
		// TODO Auto-generated method stub
		CarModel updatedCar = carDb.getOne(id);
		updatedCar.setAmount(newCar.getAmount());
		updatedCar.setBrand(newCar.getBrand());
		updatedCar.setPrice(newCar.getPrice());
		updatedCar.setType(newCar.getType());
		carDb.save(updatedCar);
	}
}
