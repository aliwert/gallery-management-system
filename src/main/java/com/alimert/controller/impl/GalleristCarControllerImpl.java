package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.IGalleristCarController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoGalleristCar;
import com.alimert.dto.DtoGalleristCarIU;
import com.alimert.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class GalleristCarControllerImpl extends BaseController implements IGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;


    @Override
    @PostMapping("/save")
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dto) {
        return ok(galleristCarService.saveGalleristCar(dto));
    }

}
