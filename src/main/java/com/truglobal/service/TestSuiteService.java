package com.truglobal.service;

import com.truglobal.dto.TestSuiteDto;
import com.truglobal.entity.TestSuite;
import com.truglobal.repository.TestSuiteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSuiteService {

    @Autowired
    private TestSuiteRepository testSuiteRepository;

    @Autowired
    private ModelMapper mapper;

    public TestSuite createTestSuite(TestSuiteDto requestData) {
        TestSuite testSuite = mapper.map(requestData, TestSuite.class);
        return testSuiteRepository.save(testSuite);
    }

}
