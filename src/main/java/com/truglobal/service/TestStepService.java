package com.truglobal.service;

import com.truglobal.dto.ImpactedParentCntByStepIdDto;
import com.truglobal.dto.TestStepDto;
import com.truglobal.entity.TestCase;
import com.truglobal.entity.TestStep;
import com.truglobal.exception.CustomException;
import com.truglobal.repository.TestCaseRepository;
import com.truglobal.repository.TestStepRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TestStepService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestStepRepository testStepRepository;

    @Autowired
    private ModelMapper mapper;

    public TestStep createTestStep(TestStepDto requestData) {
        TestStep testStep = mapper.map(requestData, TestStep.class);
        if (requestData.getTestCaseId() != 0) {
            TestCase testCase = testCaseRepository.findById(requestData.getTestCaseId())
                    .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Case Id invalid"));
            testStep.getTestCases().add(testCase);
        }
        return testStepRepository.save(testStep);
    }

    public TestStep mapTestCase(long testCaseId, long testStepId) {
        if (testCaseId == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Case Id Missing");
        }
        if (testStepId == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Step Id Missing");
        }
        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Case Id invalid"));

        TestStep testStep = testStepRepository.findById(testStepId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Step Id invalid"));

        testStep.getTestCases().add(testCase);
        return testStepRepository.save(testStep);

    }

    public ImpactedParentCntByStepIdDto getImaptectedParentCnt(long testStepId) {
        ImpactedParentCntByStepIdDto result = new ImpactedParentCntByStepIdDto();
        if (testStepId == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Step Id Missing");
        }
        TestStep testStep = testStepRepository.findById(testStepId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Step Id invalid"));

        result.setTestStepName(testStep.getTestStepName());
        result.setTestStepDescription(testStep.getTestStepDescription());
        result.setImpactedTestCases(testStep.getTestCases().size());
        int setImpactedTestSuites = 0;
        for (TestCase testCase : testStep.getTestCases()) {
            setImpactedTestSuites += testCase.getTestSuites().size();
        }
        result.setImpactedTestSuites(setImpactedTestSuites);
        return result;
    }

}
