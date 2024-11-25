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

    @Override
    @GetMapping("/list/{id}")
    public RootEntity<DtoGalleristCar> getGalleristCarById(@PathVariable(name = "id") Long id) {
        return ok(galleristCarService.getGalleristCarById(id));
    }

    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoGalleristCar>> getAllGalleristCar() {

        return ok(galleristCarService.getAllGalleristCar());
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoGalleristCar> updateGalleristCar(@PathVariable(name = "id") Long id, @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.updateGalleristCar(id, dtoGalleristCarIU));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<Void> deleteGalleristCar(@PathVariable(name = "id") Long id) {
        galleristCarService.deleteGalleristCar(id);
        return ok(null);
    }
}
