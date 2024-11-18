package com.alimert.controller;

import com.alimert.dto.DtoSoldCar;
import com.alimert.dto.DtoSoldCarIU;

public interface ISoldCarController {
    public RootEntity<DtoSoldCar> buyCar(DtoSoldCarIU dtoSoldCarIU);

}
