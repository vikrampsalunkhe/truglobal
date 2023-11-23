package com.truglobal.controller;

import com.truglobal.dto.ImpactedParentCntByStepIdDto;
import com.truglobal.dto.ResponseDto;
import com.truglobal.dto.TestStepDto;
import com.truglobal.entity.TestStep;
import com.truglobal.service.TestStepService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class TestStepControllerTest extends AbstractController {

    @MockBean
    private TestStepService testStepService;

    @Test
    @DisplayName("POST /create - Success")
    void testCreateTestStepSuccess() throws Exception {

        // Setup mocked service
        TestStepDto testStepDto = new TestStepDto();
        testStepDto.setTestStepName("Account");
        testStepDto.setTestStepDescription("Account");

        //Return Object
        TestStep testStep = new TestStep();
        testStep.setTestStepId(1L);
        testStep.setTestStepName("Account");
        testStep.setTestStepDescription("Account");
        doReturn(testStep).when(testStepService).createTestStep(testStepDto);

        // Call API
        RequestBuilder request = post("/api/v1/teststep/create").content(asJsonString(testStepDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        ResponseDto responseDto = convertToResponseDto(response.getContentAsString());
        // Validate response
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(responseDto.getMessage(), "Test Step saved successfully");

    }

    @Test
    @DisplayName("POST /create - Failure")
    void testCreateTestStepFailure() throws Exception {

        // Setup mocked service
        TestStepDto testStepDto = new TestStepDto();
        testStepDto.setTestStepName("Account");
        testStepDto.setTestStepDescription("Account");

        when(testStepService.createTestStep(testStepDto)).thenThrow(new RuntimeException());
        // Call API
        RequestBuilder request = post("/api/v1/teststep/create").content(asJsonString(testStepDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        ResponseDto responseDto = convertToResponseDto(response.getContentAsString());

        // Validate response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    @DisplayName("GET /impactedparentcount - Success")
    void testImpactedparentcountSuccess() throws Exception {

        // Setup mocked service
        Long testStepId = 5L;

        //Return Object
        ImpactedParentCntByStepIdDto impactedParentCntByStepIdDto = new ImpactedParentCntByStepIdDto();
        impactedParentCntByStepIdDto.setImpactedTestCases(1);
        impactedParentCntByStepIdDto.setImpactedTestCases(1);
        impactedParentCntByStepIdDto.setTestStepName("Account");
        impactedParentCntByStepIdDto.setTestStepDescription("Account");
        doReturn(impactedParentCntByStepIdDto).when(testStepService).getImaptectedParentCnt(any(Long.class));

        // Call API
        RequestBuilder request = get("/api/v1/teststep/impactedparentcount/{testStepId}", testStepId);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        ResponseDto responseDto = convertToResponseDto(response.getContentAsString());
        // Validate response
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(responseDto.getMessage(), "Count fetched successfully");

    }

    @Test
    @DisplayName("GET /impactedparentcount - Failure")
    void testImpactedparentcountFailure() throws Exception {

        // Setup mocked service
        Long testStepId = 5L;

        //Return Object
        when(testStepService.getImaptectedParentCnt(any(Long.class))).thenThrow(new RuntimeException());

        // Call API
        RequestBuilder request = get("/api/v1/teststep/impactedparentcount/{testStepId}", testStepId);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        ResponseDto responseDto = convertToResponseDto(response.getContentAsString());

        // Validate response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals(responseDto.getMessage(), "Error while Fetching the impacted parent count");

    }

    @Test
    @DisplayName("PUT /maptestcase - Success")
    void testMapTestCaseSuccess() throws Exception {

        // Setup mocked service
        Long testStepId = 5L;
        Long testCaseId = 1L;

        //Return Object
        TestStep testStep = new TestStep();
        testStep.setTestStepId(1L);
        testStep.setTestStepName("Account");
        testStep.setTestStepDescription("Account");
        doReturn(testStep).when(testStepService).mapTestCase(any(Long.class), any(Long.class));

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("testCaseId", testCaseId + "");
        // Call API
        RequestBuilder request = put("/api/v1/teststep/maptestcase/{testStepId}/?testCaseId=" + testCaseId, Long.valueOf(testStepId))
                .queryParams(requestParams)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        ResponseDto responseDto = convertToResponseDto(response.getContentAsString());
        // Validate response
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(responseDto.getMessage(), "Test Suite mapped successfully");

    }

}
