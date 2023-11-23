package com.truglobal.service;

import com.truglobal.dto.TestCaseDto;
import com.truglobal.entity.TestCase;
import com.truglobal.entity.TestSuite;
import com.truglobal.exception.CustomException;
import com.truglobal.repository.TestCaseRepository;
import com.truglobal.repository.TestSuiteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestSuiteRepository testSuiteRepository;

    @Autowired
    private ModelMapper mapper;

    public TestCase createTestCase(TestCaseDto requestData) {
        TestCase testCase = mapper.map(requestData, TestCase.class);
        if (requestData.getTestSuiteId() != 0) {
            TestSuite testSuite = testSuiteRepository.findById(requestData.getTestSuiteId())
                    .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Suite Id invalid"));
            testCase.getTestSuites().add(testSuite);
        }
        return testCaseRepository.save(testCase);
    }

    public TestCase mapTestSuite(long testCaseId, long testSuiteId) {
        if (testCaseId == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Case Id Missing");
        }
        if (testSuiteId == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Suite Id Missing");
        }
        TestSuite testSuite = testSuiteRepository.findById(testSuiteId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Suite Id invalid"));

        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST.value(), "Test Case Id invalid"));

        testCase.getTestSuites().add(testSuite);
        return testCaseRepository.save(testCase);

    }

}
