package com.rabo.csp.controller;

import com.rabo.csp.CustomerStatementProcessorApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = CustomerStatementProcessorApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class CustomerStatementProcessorControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void testUploadFileSuccess() throws Exception {

        File inputCsvFile = new File(getClass().getClassLoader().getResource("records.csv")
                .getFile());
        byte[] inputFileBytes = Files.readAllBytes(Path.of(inputCsvFile.getPath()));

        File expectedResponseFile = new File(getClass().getClassLoader().getResource("expected-response.csv")
                .getFile());
        URI expectedResponseFileUri = expectedResponseFile.toURI();
        String expectedContent = Files.readString(Paths.get(expectedResponseFileUri));

        MockMultipartFile inputFile = new MockMultipartFile(
                "inputFile",
                "records.csv",
                "text/csv",
                inputFileBytes
        );

        MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/v1/process-customer-statement");

        String responseString = mockMvc.perform(requestBuilder.file(inputFile))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        List<String> expected = Arrays.asList(expectedContent.split(System.lineSeparator()));
        List<String> actual = Arrays.asList(responseString.split("\n"));

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

    }
}
