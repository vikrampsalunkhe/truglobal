package com.truglobal.repository;

import com.truglobal.entity.TestCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestCaseRepository
        extends CrudRepository<TestCase, Long>, PagingAndSortingRepository<TestCase, Long> {

}
