package com.utk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Shipment;

public interface ShipmentRepo extends JpaRepository<Shipment, Long> {

}
