package com.multicert;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.multicert.object.Customer;

@Consumes("application/json")
@Produces("application/json")
public interface RestService {
    
    @GET
    @Path("/getcustomerbynif")
    public Response getCustomerbyNIF(@QueryParam("nif") String nif);

    @GET
    @Path("/getcustomerbyname")
    public Response getCustomersbyName(@QueryParam("name") String name);

    @GET
    @Path("/getallcustomers")
    public Response getAllCustomers();

    @POST
    @Path("/addcustomer")
    public Response addCustomer(Customer request);

    @POST
    @Path("/deletecustomer")
    public Response deleteCustomer(@QueryParam("nif") String nif);
}
