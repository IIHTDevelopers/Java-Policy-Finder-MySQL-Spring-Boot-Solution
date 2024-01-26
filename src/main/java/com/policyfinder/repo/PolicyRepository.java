package com.policyfinder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policyfinder.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

	List<Policy> findByCoverageContaining(String coverage);

	List<Policy> findByNameContaining(String name);
}
