package com.alimert.controller;

import com.alimert.dto.DtoGallerist;
import com.alimert.dto.DtoGalleristIU;

public interface IGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

    public RootEntity<DtoGallerist> findGalleristById(Long id);
}
