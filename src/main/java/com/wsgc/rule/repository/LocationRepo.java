package com.wsgc.rule.repository;

import com.wsgc.rule.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends CrudRepository<Location, Integer> {

    List<Location> findAll();

    @Query(value = "SELECT * FROM Location l WHERE l.location_barcode = :locationBarcode", nativeQuery = true)
    List<Location> findLocationByLocationId(String locationBarcode);

    @Query(value = "SELECT * FROM Location l WHERE l.location_class = :locationClass", nativeQuery = true)
    List<Location> findLocationByClass(String locationClass);

    @Query(value = "SELECT * FROM Location l WHERE l.location_class = :locationClass AND l.item_ID= :itemId", nativeQuery = true)
    List<Location> findLocationByClassAndItem(String locationClass, Integer itemId);

}
