package com.multicert.middleman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.multicert.object.Customer;


public class CustomerInterface 
{
	Connection c = null;
	
	public Customer getCustomerByNIF (String nif){
		
		Statement query = null;
		ResultSet set = null;
		Customer cust = null;
		try{
			openDatabaseConnection();
			query = c.createStatement();
			set = query.executeQuery( "SELECT * FROM customers WHERE nif = '" + nif + "'");
			while(set.next()){
				String cname = set.getString("name");
				String caddress = set.getString("address");
				String ctelephone = set.getString("telephone");
				cust = new Customer();
				cust.setName(cname);
				cust.setNif(nif);
				cust.setAddress(caddress);
				cust.setTelephone(ctelephone);
			}
		} catch (Exception e){
			System.err.println(e);
		}
		finally {
			try {
				set.close();
				query.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeDatabaseConnection();
		}
		return cust;
	}
	
	public List<Customer> getCustomersByName (String name){
		
		Statement query = null;
		ResultSet set = null;
		List<Customer> customers = new ArrayList<Customer>();
		Customer cust = null;
		try{
			openDatabaseConnection();
			query = c.createStatement();
			set = query.executeQuery("SELECT * FROM customers WHERE LOWER(name) LIKE LOWER('%" + name + "%')");
			while(set.next()){
				String cname = set.getString("name");
				String cnif = set.getString("nif");
				String caddress = set.getString("address");
				String ctelephone = set.getString("telephone");
				cust = new Customer();	
				cust.setName(cname);
				cust.setNif(cnif);
				cust.setAddress(caddress);
				cust.setTelephone(ctelephone);
				customers.add(cust);
			}
		} catch (Exception e){
			System.err.println(e);
		}
		finally {
			try {
				set.close();
				query.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeDatabaseConnection();
		}
		return customers;
		
	}
	
	public List<Customer> getAllCustomers()
	{
		List<Customer> customers = new ArrayList<Customer>();
		Statement query = null;
		ResultSet set = null;
		Customer cust = null;
		try{
			openDatabaseConnection();
			query = c.createStatement();
			set = query.executeQuery( "SELECT * FROM customers");
			while(set.next()){
				String cname = set.getString("name");
				String cnif = set.getString("nif");
				String caddress = set.getString("address");
				String ctelephone = set.getString("telephone");
				cust = new Customer();
				cust.setName(cname);
				cust.setNif(cnif);
				cust.setAddress(caddress);
				cust.setTelephone(ctelephone);
				customers.add(cust);
			}
			return customers;
		} catch (Exception e){
			System.err.println(e);
		}
		finally{
			try{
			set.close();
			query.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			closeDatabaseConnection();
		}
		return customers;
	}
	
	public boolean addCustomer(String name, String nif, String address, String telephone)
	{
		Statement update = null;
		try{
			openDatabaseConnection();
			update = c.createStatement();
			update.executeUpdate("INSERT INTO customers (name, nif, address, telephone) VALUES ('" +
										name + "', '" + nif + "', '" + address + "', '" + telephone + "');");
			return true;
		} catch (Exception e){
			System.err.println(e);
			return false;
		}
		finally {
			try {
				update.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeDatabaseConnection();
		}
	}
	
	public boolean deleteCustomer(String nif)
	{
		Statement update = null;
		try{
			openDatabaseConnection();
			update = c.createStatement();
			update.executeUpdate("DELETE FROM customers WHERE nif = '" + nif + "'");
			return true;
		} catch (Exception e){
			System.err.println(e);
			return false;
		}
		finally {
			try {
				update.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeDatabaseConnection();
		}
	}
	
	private void openDatabaseConnection(){
		
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/multicert", "postgres", "admin");
		} catch (SQLException e) {
			System.out.println("Unable to open database connection!");
			e.printStackTrace();
		}
	}
	
	private void closeDatabaseConnection(){
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Unable to close database connection!");
			e.printStackTrace();
		}
	}
}
