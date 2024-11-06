package com.alimert.service;

import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
