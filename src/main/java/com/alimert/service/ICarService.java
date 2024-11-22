package com.alimert.service;

import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;

import java.util.List;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

    public DtoCar getCarById(Long id);

    public List<DtoCar> getAllCars();

    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);

    public RootEntity<Void> deleteCar(Long id);
}
