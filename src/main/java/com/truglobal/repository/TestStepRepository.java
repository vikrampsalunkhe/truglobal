package com.truglobal.repository;

import com.truglobal.entity.TestStep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestStepRepository
        extends CrudRepository<TestStep, Long>, PagingAndSortingRepository<TestStep, Long> {

}
