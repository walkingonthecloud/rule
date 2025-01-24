package com.wsgc.rule.api;

import org.springframework.http.ResponseEntity;

public interface LabelAryService {

    byte[] convertZPLToPNG(String zpl, String mode);

}
