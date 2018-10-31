package com.apap.tutorial7.controller;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author faisalridwan
 * DealerController
 */
@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	private DealerService dealerService;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
//	@Autowired
//	private CarService carService;
//
	
	@PostMapping(value = "/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		return dealerService.addDealer(dealer);
	}
	
	@GetMapping(value = "/{dealerId}")
	public DealerModel viewDealer(@PathVariable("dealerId") long dealerId,Model model) {
		return dealerService.getDealerDetailById(dealerId).get();
	}
	
	@DeleteMapping(value = "/delete")
	private String deleteDealer(@RequestParam("dealerId") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "Success";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
			@PathVariable(value = "id") long id,
			@RequestParam("alamat") String alamat,
			@RequestParam("telp") String telp
			) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();

		if (dealer.equals(null)) {
			return "Couldn't find your dealer";
		}
		dealer.setAlamat(alamat);
		dealer.setNoTelp(telp);
		dealerService.editDealer(dealer, id);
		return "update success";
	}

	@GetMapping()
	private List<DealerModel> viewAllDealer(Model model) {
		return dealerService.getAllDealer();
	}

	@GetMapping(value = "/status/{dealerId}")
	private String getStatus(@PathVariable("dealerId") long dealerId) throws Exception {
		String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
		return restTemplate.getForEntity(path, String.class).getBody();
	}

	@GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable("dealerId") long dealerId) throws Exception {
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path, dealer, DealerDetail.class);
		return detail;
	}

//	@RequestMapping("/")
//	private String home(Model model) {
//		model.addAttribute("dealer_list", dealerService.getAllDealer());
//		model.addAttribute("pageTitle", "Home");
//		return "home";
//	}
//
//	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
//	private String add(Model model) {
//		model.addAttribute("dealer", new DealerModel());
//		model.addAttribute("pageTitle", "Add Dealer");
//		return "addDealer";
//	}
//
//	@RequestMapping(value = "/dealer/edit/{dealerId}", method = RequestMethod.POST)
//	private String editDealer(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel newDealer) {
//		dealerService.editDealer(newDealer, dealerId);
//		return "redirect:/dealer/view?dealerId=" + Long.toString(dealerId);
//	}
}
