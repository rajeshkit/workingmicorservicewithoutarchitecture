package com.cts.clickfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.clickfix.entity.ServiceCenter;
 
@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenter , Integer> {

}
