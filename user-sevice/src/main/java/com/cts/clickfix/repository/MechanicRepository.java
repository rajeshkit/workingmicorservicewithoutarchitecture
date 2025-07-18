package com.cts.clickfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.clickfix.entity.Mechanic;
 
@Repository
public interface MechanicRepository extends JpaRepository<Mechanic , Integer> {

}
