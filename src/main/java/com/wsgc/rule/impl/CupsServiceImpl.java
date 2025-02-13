package com.wsgc.rule.impl;

import com.wsgc.rule.api.CupsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.cups4j.CupsClient;
import org.cups4j.CupsPrinter;
import org.cups4j.PrintJob;
import org.cups4j.PrintRequestResult;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CupsServiceImpl implements CupsService {

    static CupsPrinter selectedPrinter = null;
    static PrintJob printJob = null;
    static URL printerURL = null;

    @Override
    public CupsPrinter selectPrinter(String printerName, String IP) {
        CupsClient client = null;
        try {
            log.info("CupsServiceImpl>>selectPrinter: Gathering printer info");

            printerURL = new URL("http://" + IP + ":631/printers/" + printerName);
            log.info("Printer URL: {}", printerURL.toString());

            client = new CupsClient(IP, 631);
            CupsPrinter cupsPrinter = client.getPrinter(printerURL);
            log.info("Selected Printer: {}", cupsPrinter.getDescription());
            selectedPrinter = cupsPrinter;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("CupsServiceImpl<<selectPrinter");
        return selectedPrinter;
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
        } else {
            throw new RuntimeException("Invalid printer or print job, unable to print...");
        }
    }

//    public static String PostZplAndReturnImageName(String zpl, String printerIpAddress) throws IOException {
//        String response = null;
//        // Setup the post parameters.
//        String parameters = "data=" + zpl;
//        parameters = parameters + "&" + "dev=R";
//        parameters = parameters + "&" + "oname=UNKNOWN";
//        parameters = parameters + "&" + "otype=ZPL";
//        parameters = parameters + "&" + "prev=Preview Label";
//        parameters = parameters + "&" + "pw=";
//
//        // Post to the printer
//        //response = HttpPost("http://"+ printerIpAddress +"/zpl", parameters);
//
//
//        HttpURLConnection connection = null;
//        URL url = new URL("http://" + printerIpAddress + "/zpl");
//        connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        connection.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));
//        connection.setRequestProperty("charset", "utf-8");
//        connection.setInstanceFollowRedirects(false);
//        connection.setDoOutput(true);
//        connection.setUseCaches(false);
//
//        //Send request
//        DataOutputStream wr = new DataOutputStream(
//                connection.getOutputStream());
//        wr.writeBytes(parameters);
//        wr.close();
//
//        //Get Response
//        InputStream is = connection.getInputStream();
//        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//        StringBuilder res = new StringBuilder(); // or StringBuffer if Java version 5+
//        String line;
//        while ((line = rd.readLine()) != null) {
//            res.append(line);
//            res.append('\r');
//        }
//        rd.close();
//        String result = res.toString();
//        return result;
//    }
//
//    public static Image LoadImageFromPrinter(String imageName, String printerIpAddress) {
//        String url = "http://" + printerIpAddress + "/png?prev=Y&dev=R&oname=" + imageName + "&otype=PNG";
//
//        var response = Http.Get(url);
//
//        using(var ms = new MemoryStream(response))
//        {
//            Image image = Image.FromStream(ms);
//
//            return image;
//        }
//    }


}
    

