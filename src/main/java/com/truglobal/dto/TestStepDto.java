package com.truglobal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestStepDto {

    private String testStepName;

    private String testStepDescription;

    private long testCaseId;

}
