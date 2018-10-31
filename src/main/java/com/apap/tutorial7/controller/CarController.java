package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

/**
 * 
 * @author faisalridwan
 * CarController
 */
@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;

	@PutMapping(value = "{id}")
	private String updateCarSubmit(
			@PathVariable(value = "id") long id,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "amount", required = false) String amount,
			@RequestParam(value = "dealerID", required = false) String dealerID
			){
		CarModel car = carService.getCarDetailById(id).get();
		if (car == null) {
			return "Couldn't find your car";
		}
		if (!(brand == null)) {
			car.setBrand(brand);
		}
		if (!(type == null)) {
			car.setType(type);
		}
		if (!(price == null)) {
			car.setPrice(Long.valueOf(price));
		}
		if (!(amount == null)) {
			car.setAmount(Integer.parseInt(amount));
		}
		if (!(dealerID == null)) {
			DealerModel dealer = dealerService.getDealerDetailById(Long.valueOf(dealerID)).get();
			car.setDealer(dealer);
		}
		carService.editCar(car, id);
		return "car update success";
	}
	
//	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
//	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
//		DealerModel dummyDealer = new DealerModel();
//		ArrayList<CarModel> dummyList = new ArrayList<CarModel>();
//		dummyList.add(new CarModel());
//		dummyDealer.setListCar(dummyList);
//
//		model.addAttribute("dealerId", dealerId);
//		model.addAttribute("dealer", dummyDealer);
//		model.addAttribute("pageTitle", "Add Car");
//		return "addCar";
//	}
//
//	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params= {"addAllCar"})
//	private String addCarSubmit(
//			@ModelAttribute DealerModel dealer,
//			@PathVariable(value = "dealerId") Long dealerId,
//			Model model
//			) {
//		DealerModel dealerTarget = dealerService.getDealerDetailById(dealerId).get();
//		for (CarModel car : dealer.getListCar()) {
//			car.setDealer(dealerTarget);
//			carService.addCar(car);
//		}
//
//		model.addAttribute("dealerId", dealerId);
//		return "redirect:/dealer/view?dealerId=" + Long.toString(dealerId);
//	}
//
//	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"addRow"})
//	public String addRow(@ModelAttribute DealerModel dealer,
//			BindingResult bindingResult,
//			@PathVariable(value = "dealerId") Long dealerId,
//			Model model) {
//		dealer.getListCar().add(new CarModel());
//
//		model.addAttribute("dealerId", dealerId);
//		model.addAttribute("dealer", dealer);
//		model.addAttribute("pageTitle", "Add Car");
//		return "addCar";
//	}
//
//	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"removeRow"})
//	public String removeRow(@ModelAttribute DealerModel dealer,
//			BindingResult bindingResult,
//			@PathVariable(value = "dealerId") Long dealerId,
//			final HttpServletRequest req,
//			Model model) {
//	    dealer.getListCar().remove(Integer.valueOf(req.getParameter("removeRow")).intValue());
//
//		model.addAttribute("dealerId", dealerId);
//		model.addAttribute("dealer", dealer);
//		model.addAttribute("pageTitle", "Add Car");
//		return "addCar";
//	}
//
//	@RequestMapping(value = {"/car/delete/{dealerId}", "/car/delete/{dealerId}/{carId}"}, method = RequestMethod.POST)
//	private String deleteCar(
//			@ModelAttribute DealerModel dealer,
//			@PathVariable(value = "dealerId") Optional<Long> dealerId,
//			@PathVariable(value = "carId") Optional<Long> carId
//			) {
//		if (carId.isPresent()) {
//			carService.deleteCar(carId.get());
//		} else {
//			for (CarModel car : dealer.getListCar()) {
//				carService.deleteCar(car.getId());
//			}
//		}
//		return "redirect:/dealer/view?dealerId=" + Long.toString(dealerId.get());
//	}
//
//	@RequestMapping(value = "/car/edit/{dealerId}/{carId}", method = RequestMethod.GET)
//	private String updateCar(
//			@PathVariable(value = "dealerId") Long dealerId,
//			@PathVariable(value = "carId") Long carId,
//			Model model
//			) {
//		CarModel car = carService.getCarDetailById(carId).get();
//
//		String brand = car.getBrand();
//		String type = car.getType();
//		Long price = car.getPrice();
//		Integer amount = car.getAmount();
//
//		model.addAttribute("dealerId", dealerId);
//		model.addAttribute("carId", carId);
//		model.addAttribute("brand", brand);
//		model.addAttribute("type", type);
//		model.addAttribute("price", price);
//		model.addAttribute("amount", amount);
//		model.addAttribute("pageTitle", "Edit Car");
//		return "edit-car";
//	}
//
//	@RequestMapping(value = "/car/edit/{dealerId}/{carId}", method = RequestMethod.POST)
//	private String updateCar(
//			@PathVariable(value = "dealerId") Long dealerId,
//			@PathVariable(value = "carId") Long carId,
//			@ModelAttribute CarModel newCar
//			) {
//		carService.editCar(newCar, carId);
//		return "redirect:/dealer/view?dealerId=" + Long.toString(dealerId);
//	}


	
}
