package com.wsgc.rule.api;

import org.cups4j.CupsPrinter;
import org.cups4j.PrintJob;
import org.cups4j.PrintRequestResult;

public interface CupsService {

    CupsPrinter selectPrinter(String printer, String IP);

    PrintRequestResult print(String jobName, byte[] zpl);

}
