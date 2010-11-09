package com.smes.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.BaseDomain;
import com.smes.domain.hibernate.CustomerAccountPreference;
import com.smes.service.CustomerAccountPreferenceService;
import com.smes.validator.ca.CAPValidator;

@Controller
@RequestMapping ("/customerAcountPreferences")
public class CAPController {

	private final CustomerAccountPreferenceService customerAccountPreferenceService;
	private final CAPValidator validator;

	@Autowired
	public CAPController (CustomerAccountPreferenceService customerAccountPreferenceService,
			CAPValidator validator){
		this.customerAccountPreferenceService = customerAccountPreferenceService;
		this.validator = validator;
	}
	
	@RequestMapping (value="/{customerId}", method=RequestMethod.GET)
	public String showPreferences (@PathVariable ("customerId") String strCustomerId, Model model){
		System.out.println("CustomerAccountPreferenceService");
		int customerId = Integer.valueOf(strCustomerId);
		CustomerAccountPreference pref = 
			customerAccountPreferenceService.getCustomerAccountPreferenceByCustomer(customerId);
		if (pref == null)
			pref = new CustomerAccountPreference();
		pref.setCustomerId(customerId);
		model.addAttribute(pref);
		return "customerAccountPreferences";
	}
	
	@RequestMapping (method=RequestMethod.POST)
	public String onSubmit (@ModelAttribute ("CustomerAccountPreference") CustomerAccountPreference pref,
			BindingResult result) {
		validator.validate(pref, result);
		if (result.hasErrors())
			return "customerAccountPreferences";
		addAudit(pref);
		customerAccountPreferenceService.saveOrUpdate(pref);
		return "customerListSuccess";
	}

	private void addAudit (BaseDomain domain){
		domain.setCreatedBy(1);
		domain.setCreatedDate(new Date());
		domain.setModifiedBy(1);
		domain.setModifiedDate(new Date ());
	}
}
