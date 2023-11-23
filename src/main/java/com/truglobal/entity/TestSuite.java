package com.truglobal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "TEST_SUITE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestSuite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long testSuiteId;

    @Column(name = "NAME")
    private String testSuiteName;

    @Column(name = "DESCRIPTION")
    private String testSuiteDescription;

//    @ManyToMany(mappedBy = "testSuites", cascade = { CascadeType.ALL })
//    @JsonIgnore
//    private Set<TestCase> testCases = new HashSet<>();
}
