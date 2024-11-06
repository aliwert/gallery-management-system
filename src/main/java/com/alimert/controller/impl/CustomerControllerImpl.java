package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.ICustomerController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;
import com.alimert.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerControllerImpl extends BaseController implements ICustomerController {


    @Autowired
    private ICustomerService customerService;

    @RequestMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }
}
