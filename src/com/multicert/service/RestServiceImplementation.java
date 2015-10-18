package com.multicert.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;	

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.multicert.RestService;
import com.multicert.middleman.CustomerInterface;
import com.multicert.object.Customer;

public class RestServiceImplementation implements RestService {

    @Autowired
    private CustomerInterface customerService = new CustomerInterface();

    private Log LOGGER = LogFactory.getLog(this.getClass().getName());

    public Response getCustomersbyName(String name) {
    	
    	LOGGER.info(name);
    	
		if (name == null) {
			System.out.println("Bad name!");
		    return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(customerService.getCustomersByName(name).toArray()).build();
    }

    public Response getAllCustomers() {
    	
    	System.out.println("Getting all customers");
    	
    	return Response.ok(customerService.getAllCustomers()).build();
    	//return Response.ok(localCustomers.toArray()).build();
    	
    }

    public Response addCustomer(Customer request) {
    	
    	boolean status=false;
		LOGGER.info(request);
		
		//localCustomers.add(request);
		if(request != null && request.getName() != "" && request.getNif().length() == 9 && request.getAddress() != "" && request.getTelephone() != ""){
			status = customerService.addCustomer(request.getName(), request.getNif(), request.getAddress(), request.getTelephone());
		}
		if(status){
			return Response.ok(Response.Status.ACCEPTED).build();
		}
		else {
			System.out.println("Operation unsuccessful!");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
    }

    public Response deleteCustomer(String nif) {
    	
    	boolean status = false;
    	LOGGER.info(nif);
    	/*Customer exists = customerService.getCustomerByNIF(nif);
		if(exists == new Customer()){
			System.out.println("Customer do not exist!");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
    	*/
		if (nif == null || nif.length()!= 9) {
			System.out.println("Bad NIF!");
		    return Response.status(Response.Status.BAD_REQUEST).build();
		}
		status = customerService.deleteCustomer(nif);
		if(status){
			return Response.ok(Response.Status.ACCEPTED).build();
		}
		else {
			System.out.println("Operation unsuccessful!");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
    }

    public Response getCustomerbyNIF(String nif) {
    	
    	Customer cust = null;
	    LOGGER.info(nif);
	    
		if (nif == null || nif.length() != 9) {	
			System.out.println("Bad NIF!");
		    return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		cust = customerService.getCustomerByNIF(nif);
		if(cust != null){
			return Response.ok(cust).build();
		}
		else {
			return Response.ok(new Customer()).build();
		}
		//return Response.ok(localCustomers.toArray()).build(); // returns Customer
    }
}
