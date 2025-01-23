package com.wsgc.rule.repository;

import com.wsgc.rule.entity.Facility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepo extends CrudRepository<Facility, Integer> {

    @Query(value = "SELECT * FROM Facility f WHERE f.facility_ID = :facilityId", nativeQuery = true)
    Facility findFacilityById(String facilityId);

    List<Facility> findAll();

}
