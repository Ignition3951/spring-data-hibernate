package com.utk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Log;

public interface LogRepository extends JpaRepository<Log, Integer>, LogRepositoryCustom {

}
