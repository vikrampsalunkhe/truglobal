package com.truglobal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpactedParentCntByStepIdDto {

    private String testStepName;

    private String testStepDescription;

    private int impactedTestCases;

    private int impactedTestSuites;

}
