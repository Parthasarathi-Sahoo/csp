package com.rabo.csp.controller;

import com.rabo.csp.service.StatementProcessorService;
import com.rabo.csp.utility.DateFormatter;
import com.rabo.csp.utility.HttpUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * CustomerStatementProcessorController : Controller class for Customer Monthly Statement Processor
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */

@RestController
@RequestMapping("/v1")
@Log4j2
public class CustomerStatementProcessorController {

    @Autowired
    StatementProcessorService statementProcessorService;

    @Value("${local-zone}")
    private ZoneId localTZ;

    @Value("${date-formatter}")
    private String dateFormatter;

    @Value("${customer-statement-recon-report-name}")
    private String customerStatementReconReportName;

    @Autowired
    public CustomerStatementProcessorController(StatementProcessorService statementProcessorService) {
        this.statementProcessorService = statementProcessorService;
    }

    /**
     * processCustomerStatement : This controller method process the customer monthly statement.
     * The monthly statement can be in file format of CSV or XML and can process one file input per each request.
     * The controller returns the validation result of the failed records in CSV file format .
     * The csv file name will be appended with the system date and time of the report generation
     *
     * @param inputFile MultiPartFile customer monthly statement file should be in format of CSV or XML.
     * @return a consolidated report of the validation result of the failed items in csv format
     * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
     */

    @PostMapping(value = "process-customer-statement", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Resource> processCustomerStatement(@RequestParam(value = "inputFile") MultipartFile inputFile) {
        log.info("Started processCustomerStatement with file : " + inputFile.getOriginalFilename());
        byte[] content = statementProcessorService.processCustomerStatement(inputFile);

        return ResponseEntity.ok()
                .headers(HttpUtility.getHttpHeadersForContentDisposition(customerStatementReconReportName + " " + DateFormatter.getFormattedLocalTime(LocalDateTime.now(localTZ), dateFormatter)))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(content));
    }


}
