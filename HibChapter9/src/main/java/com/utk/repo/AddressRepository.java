package com.utk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
