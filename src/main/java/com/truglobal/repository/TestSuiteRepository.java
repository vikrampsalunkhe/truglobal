package com.truglobal.repository;

import com.truglobal.entity.TestSuite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestSuiteRepository
        extends CrudRepository<TestSuite, Long>, PagingAndSortingRepository<TestSuite, Long> {

}
