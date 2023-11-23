package com.truglobal.controller;

import com.truglobal.dto.ImpactedParentCntByStepIdDto;
import com.truglobal.dto.ResponseDto;
import com.truglobal.dto.TestStepDto;
import com.truglobal.entity.TestStep;
import com.truglobal.exception.CustomException;
import com.truglobal.response.ResponseHandler;
import com.truglobal.service.TestStepService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;

@RestController
@RequestMapping(value = "/api/v1/teststep")
@Log
public class TestStepController {

    @Autowired
    private TestStepService testStepService;

    /**
     * Create Test Step
     *
     * @param {TestStepDto} requestData
     * @return {ResponseDto}
     * @throws CustomException
     */
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createTestStep(@RequestBody TestStepDto requestData) throws CustomException {
        log.info("Creating new Test Step");
        try {
            TestStep testStep = testStepService.createTestStep(requestData);
            if (testStep != null && testStep.getTestStepId() > 0) {
                log.info("Test Step saved successfully");
                return ResponseHandler.generateResponse("Test Step saved successfully", HttpStatus.OK, testStep);
            } else {
                log.log(Level.SEVERE, " Error while creating the Test Step");
                return ResponseHandler.generateResponse("Error while creating the Test Step",
                        HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, " Error while creating the Test Step");
            return ResponseHandler.generateResponse("Error while creating the Test Step",
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * Map test cases to test step
     *
     * @param {Long} testStepId
     * @param {Long} testCaseId
     * @return {ResponseDto}
     * @throws CustomException
     */

    @PutMapping(path = "/maptestcase/{testStepId}/")
    public ResponseEntity<ResponseDto> mapTestCase(@PathVariable(value = "testStepId") Long testStepId,
                                                   @RequestParam("testCaseId") Long testCaseId) throws CustomException {
        log.info("Mapping Test Suite");
        try {
            TestStep testStep = testStepService.mapTestCase(testCaseId, testStepId);
            if (testStep != null && testStep.getTestStepId() > 0) {
                log.info("Test Suite mapped successfully");
                return ResponseHandler.generateResponse("Test Suite mapped successfully", HttpStatus.OK, testStep);
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

    /**
     * Get impacted parent count
     *
     * @param {Long} testStepId
     * @return {ResponseDto}
     * @throws CustomException
     */
    @GetMapping(path = "/impactedparentcount/{testStepId}")
    public ResponseEntity<ResponseDto> getImpactedCntById(@PathVariable(value = "testStepId") Long testStepId)
            throws CustomException {
        try {
            log.info("Fetching impacted parent count");
            ImpactedParentCntByStepIdDto impactedParentCntByStepIdDto = testStepService
                    .getImaptectedParentCnt(testStepId);
            return ResponseHandler.generateResponse("Count fetched successfully", HttpStatus.OK,
                    impactedParentCntByStepIdDto);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, " Error while Fetching the impacted parent count");
            return ResponseHandler.generateResponse("Error while Fetching the impacted parent count",
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

}
