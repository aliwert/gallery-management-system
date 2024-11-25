package com.alimert.service;

import com.alimert.dto.DtoGalleristCar;
import com.alimert.dto.DtoGalleristCarIU;

import java.util.List;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);


    public DtoGalleristCar getGalleristCarById(Long id);

    public List<DtoGalleristCar> getAllGalleristCar();

}
