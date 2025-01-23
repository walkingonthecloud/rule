package com.wsgc.rule.api;

import com.wsgc.rule.entity.Facility;

public interface FacilityService {

    Facility findByFacilityId(String facilityId);

    void saveFacility(Facility facility);

}
