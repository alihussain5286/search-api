package com.axiom.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.axiom.example.entity.Device;

/**
 * @author admin
 *
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, Long> {

}
