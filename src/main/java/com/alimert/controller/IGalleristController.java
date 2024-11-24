package com.alimert.controller;

import com.alimert.dto.DtoGallerist;
import com.alimert.dto.DtoGalleristIU;

import java.util.List;

public interface IGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

    public RootEntity<List<DtoGallerist>> getAllGallerist();

    public RootEntity<DtoGallerist> findGalleristById(Long id);

    public RootEntity<DtoGallerist> updateGallerist(Long id, DtoGalleristIU dtoGalleristIU);

    public RootEntity<Void> deleteGallerist(Long id);
}
