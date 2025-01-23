package com.wsgc.rule.rest;

import com.wsgc.rule.api.FacilityService;
import com.wsgc.rule.impl.LabelAryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LabelController {

    private final FacilityService facilityService;
    private final LabelAryServiceImpl labelAryService;

    @Autowired
    public LabelController(FacilityService facilityService, LabelAryServiceImpl labelAryService) {
        this.facilityService = facilityService;
        this.labelAryService = labelAryService;
    }

    @GetMapping("/render")
    public ResponseEntity<byte[]> renderLabelFromZPLString(@RequestBody String zpl) {;
        return new ResponseEntity<byte[]>(labelAryService.convertZPLToPNG(zpl),HttpStatus.OK);
    }

}
