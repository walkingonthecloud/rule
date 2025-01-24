package com.wsgc.rule.rest;

import com.wsgc.rule.api.FacilityService;
import com.wsgc.rule.impl.CupsServiceImpl;
import com.wsgc.rule.impl.LabelAryServiceImpl;
import org.cups4j.CupsPrinter;
import org.cups4j.PrintRequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LabelController {

    private final FacilityService facilityService;
    private final LabelAryServiceImpl labelAryService;
    private final CupsServiceImpl cupsService;

    @Autowired
    public LabelController(FacilityService facilityService, LabelAryServiceImpl labelAryService, CupsServiceImpl cupsService) {
        this.facilityService = facilityService;
        this.labelAryService = labelAryService;
        this.cupsService = cupsService;
    }

    @GetMapping("/render")
    public ResponseEntity<byte[]> renderLabelFromZPLString(@RequestBody String zpl) {
        return new ResponseEntity<byte[]>(labelAryService.convertZPLToPNG(zpl, "F"), HttpStatus.OK);
    }

    @PostMapping("/print/to/{printerName}/job/{jobName}/at/{machineIp}")
    public ResponseEntity<String> printLabel(@RequestBody String zpl, @PathVariable String machineIp, @PathVariable String printerName, @PathVariable String jobName) {
        byte[] stream = labelAryService.convertZPLToPNG(zpl, "P");
        CupsPrinter printer = cupsService.selectPrinter(printerName, machineIp);
        PrintRequestResult result = cupsService.print(jobName, stream);
        return new ResponseEntity<String>(result.getResultDescription(),HttpStatus.OK);
    }

}
