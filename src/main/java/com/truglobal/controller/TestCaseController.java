package com.truglobal.controller;

import com.truglobal.dto.ResponseDto;
import com.truglobal.dto.TestCaseDto;
import com.truglobal.entity.TestCase;
import com.truglobal.exception.CustomException;
import com.truglobal.response.ResponseHandler;
import com.truglobal.service.TestCaseService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;

@RestController
@RequestMapping(value = "/api/v1/testcase")
@Log
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createTestCase(@RequestBody TestCaseDto requestData)
            throws CustomException {
        log.info("Creating new Test Case");
        try {
            TestCase testCase = testCaseService.createTestCase(requestData);
            if (testCase != null && testCase.getTestCaseId() > 0) {
                log.info("Test Case saved successfully");
                return ResponseHandler.generateResponse("Test Case saved successfully", HttpStatus.OK, testCase);
            } else {
                log.log(Level.SEVERE, " Error while creating the Test Case");
                return ResponseHandler.generateResponse("Error while creating the Test Case",
                        HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, " Error while creating the Test Case");
            return ResponseHandler.generateResponse("Error while creating the Test Case",
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping(path = "/maptestsuite/{testCaseId}/")
    public ResponseEntity<ResponseDto> mapTestSuite(@PathVariable(value = "testCaseId") Long testCaseId, @RequestParam("testSuiteId") Long testSuiteId)
            throws CustomException {
        log.info("Mapping Test Suite");
        try {
            TestCase testCase = testCaseService.mapTestSuite(testCaseId, testSuiteId);
            if (testCase != null && testCase.getTestCaseId() > 0) {
                log.info("Test Suite mapped successfully");
                return ResponseHandler.generateResponse("Test Suite mapped successfully", HttpStatus.OK, testCase);
            } else {
                log.log(Level.SEVERE, " Error while mapping the Test Suite");
                return ResponseHandler.generateResponse("Error while mapping the Test Suite",
                        HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, " Error while mapping the Test Suite");
            return ResponseHandler.generateResponse("Error while mapping the Test Suite",
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
