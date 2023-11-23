package com.truglobal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto {

    private String testCaseName;

    private String testCaseDescription;

    private long testSuiteId;

}
