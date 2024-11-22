package com.alimert.controller;

import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;
import com.alimert.model.Car;

import java.util.List;

public interface ICarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

    public RootEntity<DtoCar> getCarById(Long id);

    public RootEntity<List<DtoCar>> getAllCars();

    public RootEntity<DtoCar> updateCar(Long id, DtoCarIU dtoCarIU);

    public RootEntity<Void> deleteCar(Long id);
}
