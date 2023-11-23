package com.truglobal.controller;

import com.truglobal.dto.ResponseDto;
import com.truglobal.dto.TestSuiteDto;
import com.truglobal.entity.TestSuite;
import com.truglobal.exception.CustomException;
import com.truglobal.response.ResponseHandler;
import com.truglobal.service.TestSuiteService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@RestController
@RequestMapping(value = "/api/v1/testsuite")
@Log
public class TestSuiteController {

    @Autowired
    private TestSuiteService testSuiteService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createTestSuite(@RequestBody TestSuiteDto requestData)
            throws CustomException {
        log.info("Creating new Test Suite");
        try {
            TestSuite testSuite = testSuiteService.createTestSuite(requestData);
            if (testSuite != null && testSuite.getTestSuiteId() > 0) {
                log.info("Test Suite saved successfully");
                return ResponseHandler.generateResponse("Test Suite saved successfully", HttpStatus.OK, testSuite);
            } else {
                log.log(Level.SEVERE, " Error while creating the Test Suite");
                return ResponseHandler.generateResponse("Error while creating the Test Suite",
                        HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, " Error while creating the Test Suite");
            return ResponseHandler.generateResponse("Error while creating the Test Suite",
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
