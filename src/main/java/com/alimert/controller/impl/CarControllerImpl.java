package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.ICarController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;
import com.alimert.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Override
    @GetMapping("/list/{id}")
    public RootEntity<DtoCar> getCarById(@PathVariable(name = "id") Long id) {

        return ok(carService.getCarById(id));
    }

    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoCar>> getAllCars() {
        return ok(carService.getAllCars());
    }

    @Override
    @PutMapping("update/{id}")
    public RootEntity<DtoCar> updateCar(@PathVariable(name = "id") Long id, @RequestBody DtoCarIU dtoCarIU) {

        return ok(carService.updateCar(id, dtoCarIU));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<Void> deleteCar(@PathVariable(name = "id") Long id) {
        carService.deleteCar(id);
        return ok(null);
    }
}
