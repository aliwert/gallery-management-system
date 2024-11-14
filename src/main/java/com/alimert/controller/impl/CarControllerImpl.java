package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.ICarController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;
import com.alimert.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/car")
public class CarControllerImpl extends BaseController implements ICarController {


    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }
}
