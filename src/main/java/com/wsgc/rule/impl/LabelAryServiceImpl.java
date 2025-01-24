package com.wsgc.rule.impl;

import com.wsgc.rule.api.LabelAryService;
import com.wsgc.rule.util.IntegerGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

@Service
@Slf4j
public class LabelAryServiceImpl implements LabelAryService {

    @Override
    public byte[] convertZPLToPNG(String zpl, String mode)
    {
        //String zpl2 = "XlhBCl5GTzQsMTE0LDBeQTBOLDE4LDE4XkZEMTg4ODg4ODg4ODheRlMKXkZPNCwxMzIsMF5BME4sMTgsMTheRkRXRVNUIEVMTSBYMDheRlMKXkZPNCwxNTAsMF5BME4sMTgsMTheRkQ3NzIzIE4gUkVFTVMgUkReRlMKXkZPNCwxNjgsMF5BME4sMTgsMTheRkRMSVRDSEZJRUxEIFBBUksgQVogODUzNDBeRlMKXkZPMTUyLDMwMSwwXkEwTiwyNSwyNV5GRFNISVBeRlMKXkZPMTUyLDMzMiwwXkEwTiwyNSwyNV5GRFRPOl5GUwpeRk8yNTQsMzEwLDBeQTBOLDI1LDI1XkZEV0UgTkVXIE9STEVBTlNeRlMKXkZPMjU0LDMzOSwwXkEwTiwyNSwyNV5GRFdFIE5FVyBPUkxFQU5TXkZTCl5GTzI1NCwzNjcsMF5BME4sMjUsMjVeRkQyOTI5IE1BR0FaSU5FIFNUUkVFVF5GUwpeRk8yNTQsMzk4LDBeQTBOLDM2LDM2XkZETkVXIE9STEVBTlMgTEEgNzAxMTVeRlMKXkZPMjU0LDQ0MSwwXkEwTiw1OCw1OF5GRCBMQSA3MDEgOS0yMl5GUwpeRk8yMCw2MjUsMF5BME4sMzIsMzJeRkRVUFMgR1JPVU5EXkZTCl5GTzIwLDY1NiwwXkEwTiwzMiwzMl5GRFRSQUNLSU5HICM6IDFaIEIzNSAwNDAgMDMgMDAwMCAzNzI3IF5GUwpeRk83MjMsNjE1XkdCNzUsNzQsNzJeRlMKXkZPNSwzNzheR0IyMjIsMywyXkZTCl5GTzIyNyw0MzReR0I1NjQsMywyXkZTCl5GTzIyNywzNzheR0I0LDI0MCw0XkZTCl5GTzUsNjE1XkdCNzg4LDMsMl5GUwpeRk81LDY4Nl5HQjc4NywzLDJeRlMKXkZPNSw4NTFeR0I3ODgsMywyXkZTCl5GTzI4OCw1MDAsMF5CWTReQkNOLDEwMixOLE4sTixBXkZENDIwNzAxMTVeRlMKXkZPNDEsNjk2LDBeQlk0XkJDTiwxNDIsTixOLE4sQV5GRDFaQjM1MDQwMDMwMDAwMzcyN15GUwpeRk80NjcsMTE0LDBeQTBOLDMyLDMyXkZEMyBMQlNeRlMKXkZPNjI5LDExNCwwXkEwTiwzMiwzMl5GRDEgT0YgMV5GUwpeRk8yMCw4NTksMF5BME4sMjEsMjFeRkRCSUxMSU5HOiBQL1AgXkZTCl5GTzIwLDg3OSwwXkEwTiwyMSwyMV5GRFJFRjEgOiAxNTUwOTY4MzVeRlMKXkZPNDA2LDg0OSwwXkEwTiw0MCw0MF5GREFaQV5GUwpeRk84LDM5Nl5CRDJeRkheRkQwMDM4NDA3MDExNTAwMDBbKT5fMUUwMV8xRDk2MVowMDAwMzcyN18xRFVQU05fMURCMzUwNDBfMUUwN1haVk9fMERORzE1LkwtUkgxWFlfMjNFIDFORzZSKi1fMEQvXzI1Ky9NUSosJ18xQ0NGVCtfMUNTXzBEXzFFXzA0XkZTCl5GTzU1OCw5MTYsMF5BME4sMTQsMTReRkRQU0dMIDIzLjAgIEdlbmVyaWMgMTIuNVYgMDMvMjAyNF5GUwpeRk82LDcxXkdCODAwLDIsMV5GUwpeRk82LDEzMjBeR0I4MDAsMiwxXkZTCl5GTzgsNDAsMF5BME4sMzEsMzFeRkRQYWNrIERlc3Q6IFBLR15GUwpeRk8yOTgsMjYsMF5BME4sNDUsNDVeRkRIVUI6IEFaQV5GUwpeRk81NTgsNDAsMF5BME4sMzEsMzFeRkRTSElQVklBOiBVUFNHXkZTCl5GTzI4NCwxMzM2LDBeQlkzXkJDTiwxMjIsTixOLE4sQV5GRDAwMDAxOTE1MTMwOTYwMzQ1NTExXkZTCl5GTzQwNiwxNDY0LDBeQTBOLDM2LDM2XkZEMDAwMDE5MTUxMzA5NjAzNDU1MTFeRlMKXkZPNiwxMzk1LDBeQTBOLDMxLDMxXkZEQ2FydG9uIDQgb2YgNV5GUwpeRk82LDE0OTIsMF5BME4sMzEsMzFeRkRIcnpuIFJlZCBXaW5lIEdsYXNzIENsZWFyIFMvNF5GUwpeRk82LDE0MjcsMF5BME4sMzEsMzFeRkRDcnRuIFF0eTogMS4wXkZTCl5GTzYsMTMzMCwwXkEwTiwzMSwzMV5GRFN0b3JlOiAwNjA4NV5GUwpeRk82LDEzNjIsMF5BME4sMzEsMzFeRkREZXB0OiA4MTJeRlMKXkZPNiwxNTI1LDBeQTBOLDMxLDMxXkZEQ01PIC0gUmV0YWlsIFdhdmVeRlMKXkZPNiwxNDYwLDBeQTBOLDMxLDMxXkZEU0tVOiAxNDIwOTUxV0VeRlMKXkZPNTEsMTU1NywwXkEwTiwzMSwzMV5GRENhc2U6IDAwMDAxOTE1MTMwOTYwMzQ1NTExXkZTCl5GTzYsMTU4OSwwXkEwTiwzMSwzMV5GRExQTjogICAgIExhYmVsOiAgb2YgXkZTCl5GTzYwOSwxMzMwLDBeQTBOLDMxLDMxXkZET3JkZXIgI15GUwpeRk82MDksMTM1NywwXkEwTiwzMSwzMV5GRDYwMDAwMDY3NDc2ODAxXkZTCl5YWgo=";

        log.info("LabelAryServiceImpl>>convertZPLToPNG");
        var uri = URI.create("http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/");
        var request = HttpRequest.newBuilder(uri)
                .POST(HttpRequest.BodyPublishers.ofString(zpl))
                .header("Accept", "application/pdf") //comment this for PNG
                .build();
        var client = HttpClient.newHttpClient();
        HttpResponse<byte[]> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var body = response.body();
        if (response.statusCode() == 200) {
            log.info("LabelAryServiceImpl>>convertZPLToPNG:: Call to Labelary succeeded!");
            if ("F".equals(mode) || Objects.isNull(mode) || StringUtils.isEmpty(mode)) { //default is to create a PDF file
                var file = new File("label-" + String.valueOf(IntegerGenerator.next()) + ".pdf"); // change file name for PNG images
                try {
                    Files.write(file.toPath(), body);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if("P".equals(mode))
                return body;
        } else {
            log.info("LabelAryServiceImpl>>convertZPLToPNG:: Call to Labelary Failed...check detailed logs");
            var errorMessage = new String(body, StandardCharsets.UTF_8);
            System.out.println(errorMessage);
        }
        log.info("LabelAryServiceImpl<<convertZPLToPNG");
        return new byte[0];
    }
}

