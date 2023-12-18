package com.policyfinder.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.policyfinder.dto.PolicyDTO;
import com.policyfinder.service.PolicyService;

@RestController
@RequestMapping("/policies")
public class PolicyController {

	private final PolicyService policyService;

	@Autowired
	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}

	@PostMapping
	public ResponseEntity<PolicyDTO> createPolicy(@Valid @RequestBody PolicyDTO policyDTO) {
		PolicyDTO createdPolicy = policyService.createPolicy(policyDTO);
		return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable Long id) {
		PolicyDTO policyDTO = policyService.getPolicyById(id);
		return new ResponseEntity<>(policyDTO, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PolicyDTO>> getAllPolicies() {
		List<PolicyDTO> policies = policyService.getAllPolicies();
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PolicyDTO> updatePolicy(@PathVariable Long id, @Valid @RequestBody PolicyDTO policyDTO) {
		PolicyDTO updatedPolicy = policyService.updatePolicy(id, policyDTO);
		return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
		policyService.deletePolicy(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/searchByName")
	public ResponseEntity<List<PolicyDTO>> searchPoliciesByName(@RequestParam String name) {
		List<PolicyDTO> policies = policyService.searchPoliciesByName(name);
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}

	@GetMapping("/searchByCoverage")
	public ResponseEntity<List<PolicyDTO>> searchPoliciesByCoverage(@RequestParam String coverage) {
		List<PolicyDTO> policies = policyService.searchPoliciesByCoverage(coverage);
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}
}
