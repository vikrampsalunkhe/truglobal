package com.truglobal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "TEST_CASE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestCase {

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "TESTSUITE_TESTCASE_MAPPING",
            joinColumns = {@JoinColumn(name = "TESTSUITE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TESTCASE_ID")})
    Set<TestSuite> testSuites = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long testCaseId;
    @Column(name = "NAME")
    private String testCaseName;
    @Column(name = "DESCRIPTION")
    private String testCaseDescription;

//	@ManyToMany(mappedBy = "testCases", cascade = { CascadeType.ALL })
//	 @JsonIgnore
//    private Set<TestStep> testSteps = new HashSet<>();
}
