package com.truglobal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "TEST_STEP")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestStep {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "TESTCASE_TESTSTEP_MAPPING",
            joinColumns = {@JoinColumn(name = "TESTCASE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TESTSTEP_ID")})
    Set<TestCase> testCases = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long testStepId;
    @Column(name = "NAME")
    private String testStepName;
    @Column(name = "DESCRIPTION")
    private String testStepDescription;
}
