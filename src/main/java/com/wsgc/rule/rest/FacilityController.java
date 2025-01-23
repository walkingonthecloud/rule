package com.wsgc.rule.rest;

import com.wsgc.rule.api.FacilityService;
import com.wsgc.rule.entity.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FacilityController {

    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping("/facility")
    public ResponseEntity<Facility> saveFacility(@RequestBody Facility facility) {
        facilityService.saveFacility(facility);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/facility/{facilityId}")
    public ResponseEntity<Facility> getByFacilityId(@PathVariable String facilityId) {
        Facility facility = facilityService.findByFacilityId(facilityId);
        if (!ObjectUtils.isEmpty(facility))
            return new ResponseEntity<>(facility, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
