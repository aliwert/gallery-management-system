package com.alimert.repository;

import com.alimert.model.SoldCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldCarRepository extends JpaRepository<SoldCar, Long> {
}
