package com.cts.clickfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.clickfix.entity.ServiceType;
 
@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType , Integer> {

}
