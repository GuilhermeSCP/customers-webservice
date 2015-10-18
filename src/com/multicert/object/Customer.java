package com.multicert.object;

public class Customer {

    private String name;
    private String nif;
    private String address;
    private String telephone;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getNif() {
	return nif;
    }

    public void setNif(String nif) {
	this.nif = nif;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    @Override
    public String toString() {
	return "Customer [name=" + name + ", nif=" + nif + ", address=" + address + ", telephone=" + telephone + "]";
    }
    
}
