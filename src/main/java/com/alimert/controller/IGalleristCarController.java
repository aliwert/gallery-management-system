package com.alimert.controller;

import com.alimert.dto.DtoGalleristCar;
import com.alimert.dto.DtoGalleristCarIU;

import java.util.List;

public interface IGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dto);

}
