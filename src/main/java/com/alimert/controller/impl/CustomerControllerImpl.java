package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.ICustomerController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;
import com.alimert.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoCustomer>> getAllCustomers() {
        return ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/list/{id}")
    public RootEntity<DtoCustomer> getCustomerById(@PathVariable(name = "id") Long id) {
        return ok(customerService.getCustomerById(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoCustomer> updateCustomer(@PathVariable(name = "id") Long id, @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.updateCustomer(id, dtoCustomerIU));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<Void> deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
        return ok(null);
    }
}
