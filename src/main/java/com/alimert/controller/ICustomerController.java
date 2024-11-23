package com.alimert.controller;

import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;

import java.util.List;

public interface ICustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

    public RootEntity<List<DtoCustomer>> getAllCustomers();

    public RootEntity<DtoCustomer> getCustomerById(Long id);
}
