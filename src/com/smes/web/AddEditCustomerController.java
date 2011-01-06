package com.smes.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smes.domain.hibernate.Customer;
import com.smes.domain.hibernate.CustomerAccountPreference;
import com.smes.service.CustomerAccountPreferenceService;
import com.smes.service.CustomerService;
import com.smes.validator.ca.AddCustomerValidator;
import com.smes.view.frm.Credential;
import com.smes.web.dto.CustomerForm;

@Controller
@RequestMapping ("/addCustomer")
public class AddEditCustomerController {
	private final CustomerService customerService;
	private final AddCustomerValidator addCustomerValidator;
	private final CustomerAccountPreferenceService prefService;
	@Autowired
	public AddEditCustomerController (CustomerService customerService,
			AddCustomerValidator addCustomerValidator,
			CustomerAccountPreferenceService prefService){
		this.customerService = customerService;
		this.addCustomerValidator = addCustomerValidator;
		this.prefService = prefService;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showAddCustomerForm (Model model){
		setUpModelAttribute(model, new Customer (), new CustomerAccountPreference());
		return "addCustomer";
	}

	@RequestMapping (value="/{customerId}", method = RequestMethod.GET)
	public String showEditCustomerFrom (@PathVariable ("customerId") String customerId,
			Model model){
		System.out.println("edit");
		Customer customer = customerService.getCustomer(Integer.valueOf(customerId));
		CustomerAccountPreference pref =
			prefService.getCustomerAccountPreference(customer.getCustomerId());
		setUpModelAttribute(model, customer, pref);
		return "addCustomer";
	}
	
	private void setUpModelAttribute (Model model, Customer customer,
						CustomerAccountPreference pref){
		CustomerForm dto = new CustomerForm();
		dto.setCustomer(customer);
		dto.setCustomerAccountPreference(pref);
		model.addAttribute(dto);
	}

	@RequestMapping (method = RequestMethod.POST)
	public String onSubmit (@ModelAttribute ("customerForm") CustomerForm customerForm,
			BindingResult result, HttpSession session){
		boolean successfullySaved = false;
		Customer customer = null;
		try {
			Credential c =
				CredentialHandler.getCredential(session);
			int companyId = c.getCompanyId();
			customer = customerForm.getCustomer();
			customer.setCompanyId(companyId);
			AuditUtil.addAudit(customer, c);
			addCustomerValidator.validate(customer, result);
			if (result.hasErrors())
				return "addCustomer";
			customerService.saveCustomer(customer);
			if (customer.getCustomerId() == 0)
				customer = customerService.getCustomer(customer.getFirstName(),
									customer.getLastName(), companyId);
			CustomerAccountPreference pref = customerForm.getCustomerAccountPreference();
			pref.setCustomerId(customer.getCustomerId());
			AuditUtil.addAudit(pref, c);
			prefService.saveOrUpdate(pref);
			successfullySaved = true;
		} finally {
			if (successfullySaved && customer != null)
				customerService.deleteCustomer(customer);
		}
		return "customerListSuccess";
	}
}