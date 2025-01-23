package com.wsgc.rule.impl;

import com.wsgc.rule.api.FacilityService;
import com.wsgc.rule.entity.Facility;
import com.wsgc.rule.repository.FacilityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepo facilityRepo;

    @Autowired
    public FacilityServiceImpl(FacilityRepo facilityRepo) {
        this.facilityRepo = facilityRepo;
    }

    @Override
    public Facility findByFacilityId(String facilityId) {
        log.info("Finding facility by Id {}", facilityId);
        return facilityRepo.findFacilityById(facilityId);
    }

    @Override
    public void saveFacility(Facility facility) {
        log.info("Saving facility by Id {}", facility.getFacilityId());
        facilityRepo.save(facility);
    }
}
