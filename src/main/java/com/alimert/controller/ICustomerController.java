package com.alimert.controller;

import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;

public interface ICustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
