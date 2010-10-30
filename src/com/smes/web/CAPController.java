package com.smes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.CustomerAccountPreference;
import com.smes.service.CustomerAccountPreferenceService;

@Controller
@RequestMapping ("/customerAcountPreferences")
public class CAPController {
	private final CustomerAccountPreferenceService customerAccountPreferenceService;
	
	@Autowired
	public CAPController (CustomerAccountPreferenceService customerAccountPreferenceService){
		this.customerAccountPreferenceService = customerAccountPreferenceService;
	}
	
	@RequestMapping (value="/{customerId}", method=RequestMethod.GET)
	public String showPreferences (@PathVariable ("customerId") String strCustomerId, Model model){
		int customerId = Integer.valueOf(strCustomerId);
		CustomerAccountPreference pref = 
			customerAccountPreferenceService.getCustomerAccountPreference(Integer.valueOf(customerId));
		if (pref == null)
			pref = new CustomerAccountPreference();
		model.addAttribute(pref);
		return "customerAccountPreferences";
	}
}
