package com.truglobal.service;


import com.truglobal.dto.ImpactedParentCntByStepIdDto;
import com.truglobal.entity.TestCase;
import com.truglobal.entity.TestStep;
import com.truglobal.exception.CustomException;
import com.truglobal.repository.TestStepRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class TestStepServiceTest extends AbstractService {

    @Autowired
    private TestStepService testStepService;

    @MockBean
    private TestStepRepository testStepRepository;


    @Test
    @DisplayName("Test getImaptectedParentCnt service - Success")
    void testGetImaptectedParentCntSuccess() throws CustomException {
        TestStep testStep = new TestStep();
        testStep.setTestStepId(1L);
        testStep.setTestStepName("Account");
        testStep.setTestStepDescription("Account");
        Set<TestCase> testCases = new HashSet<>();
        testCases.add(new TestCase());
        testStep.setTestCases(testCases);

        doReturn(Optional.of(testStep))
                .when(testStepRepository).findById(any(Long.class));
        ImpactedParentCntByStepIdDto returnResult = testStepService.getImaptectedParentCnt(5L);
        assertEquals(returnResult.getImpactedTestCases(), testCases.size());
    }

    @Test
    @DisplayName("Test getImaptectedParentCnt service - Failure")
    void testGetImaptectedParentCntFailure() throws CustomException {
        CustomException thrown = Assert.assertThrows(CustomException.class,
                () -> testStepService.getImaptectedParentCnt(5L));
        assertEquals(thrown.getErrorCode(), HttpStatus.BAD_REQUEST.value());
    }


}
