package com.alimert.service;

import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;

import java.util.List;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

    public List<DtoCustomer> getAllCustomers();

    public DtoCustomer getCustomerById(Long id);

    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);
}
