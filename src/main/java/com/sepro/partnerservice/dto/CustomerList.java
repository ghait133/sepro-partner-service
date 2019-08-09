package com.sepro.partnerservice.dto;


import java.util.List;

public class CustomerList {

    List<CustomerDto> customers;

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDto> customers) {
        this.customers = customers;
    }
}
