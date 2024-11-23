package com.alimert.service;

import com.alimert.dto.DtoGallerist;
import com.alimert.dto.DtoGalleristIU;

import java.util.List;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

    public List<DtoGallerist> getAllGallerist();

    public DtoGallerist getGalleristById(Long id);
}
