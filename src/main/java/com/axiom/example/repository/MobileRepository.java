package com.axiom.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axiom.example.entity.Mobile;

/**
 * @author admin
 *
 */
@Repository
public interface MobileRepository extends CrudRepository<Mobile, Long> {

}
