package com.alimert.controller;

import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;
import com.alimert.model.Car;

public interface ICarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
