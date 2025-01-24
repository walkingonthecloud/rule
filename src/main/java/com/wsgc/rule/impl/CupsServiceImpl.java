package com.wsgc.rule.impl;

import com.wsgc.rule.api.CupsService;
import lombok.extern.slf4j.Slf4j;
import org.cups4j.CupsClient;
import org.cups4j.CupsPrinter;
import org.cups4j.PrintJob;
import org.cups4j.PrintRequestResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CupsServiceImpl implements CupsService {

    static CupsPrinter selectedPrinter = null;
    static PrintJob printJob = null;

    @Override
    public CupsPrinter selectPrinter(String printerName, String IP) {
        CupsClient client = null;
        try {
            log.info("CupsServiceImpl>>selectPrinter: Gathering printer info");
            client = new CupsClient(IP, 631);
            List<CupsPrinter> printers = null;
            printers = client.getPrinters();
            if (printers.size() != 0) {
                for (CupsPrinter cupsPrinter : printers) {
                    if (cupsPrinter.getName().equals(printerName)) {
                        selectedPrinter = cupsPrinter;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("CupsServiceImpl<<selectPrinter");
        return  selectedPrinter;
    }

    @Override
    public PrintRequestResult print(String jobName, byte[] zpl) {
        log.info("CupsServiceImpl>>print");

        PrintJob printJob = new PrintJob.Builder(zpl).jobName(jobName).build();

        if (Objects.nonNull(selectedPrinter) && Objects.nonNull(printJob)) {
            try {
                PrintRequestResult result = selectedPrinter.print(printJob);
                log.info("Print result: " + result.getResultDescription() + " code:" + result.getResultCode() + " message:" + result.getResultMessage());
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new RuntimeException("Invalid printer or print job, unable to print...");
        }
    }
}
