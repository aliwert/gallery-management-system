package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.IAddressController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;
import com.alimert.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/api/address")
public class AddressControllerImpl extends BaseController implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }

    @Override
    @GetMapping("/list/{id}")
    public RootEntity<DtoAddress> findAddressById(@PathVariable(name = "id") Long id) {
        return ok(addressService.getAddressById(id));
    }

    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoAddress>> findAllAddresses() {
        return ok(addressService.getAllAddresses());
    }
}
