package com.policyfinder.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policyfinder.dto.PolicyDTO;
import com.policyfinder.entity.Policy;
import com.policyfinder.exception.ResourceNotFoundException;
import com.policyfinder.repo.PolicyRepository;
import com.policyfinder.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	private final PolicyRepository policyRepository;

	@Autowired
	public PolicyServiceImpl(PolicyRepository policyRepository) {
		this.policyRepository = policyRepository;
	}

	@Override
	public PolicyDTO createPolicy(PolicyDTO policyDTO) {
		Policy Policy = new Policy();
		BeanUtils.copyProperties(policyDTO, Policy);
		Policy savedPolicy = policyRepository.save(Policy);
		PolicyDTO savedDTO = new PolicyDTO();
		BeanUtils.copyProperties(savedPolicy, savedDTO);
		return savedDTO;
	}

	@Override
	public PolicyDTO getPolicyById(Long id) {
		Optional<Policy> policyOptional = policyRepository.findById(id);
		if (policyOptional.isPresent()) {
			PolicyDTO policyDTO = new PolicyDTO();
			BeanUtils.copyProperties(policyOptional.get(), policyDTO);
			return policyDTO;
		} else {
			throw new ResourceNotFoundException("Policy not found with id: " + id);
		}
	}

	@Override
	public List<PolicyDTO> getAllPolicies() {
		List<Policy> policyEntities = policyRepository.findAll();
		return policyEntities.stream().map(Policy -> {
			PolicyDTO policyDTO = new PolicyDTO();
			BeanUtils.copyProperties(Policy, policyDTO);
			return policyDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public PolicyDTO updatePolicy(Long id, PolicyDTO policyDTO) {
		Optional<Policy> policyOptional = policyRepository.findById(id);
		if (policyOptional.isPresent()) {
			Policy Policy = policyOptional.get();
			BeanUtils.copyProperties(policyDTO, Policy);
			Policy updatedPolicy = policyRepository.save(Policy);
			PolicyDTO updatedDTO = new PolicyDTO();
			BeanUtils.copyProperties(updatedPolicy, updatedDTO);
			return updatedDTO;
		} else {
			throw new ResourceNotFoundException("Policy not found with id: " + id);
		}
	}

	@Override
	public boolean deletePolicy(Long id) {
		Optional<Policy> policyOptional = policyRepository.findById(id);
		if (policyOptional.isPresent()) {
			policyRepository.deleteById(id);
			return true;
		} else {
			throw new ResourceNotFoundException("Policy not found with id: " + id);
		}
	}

	@Override
	public List<PolicyDTO> searchPoliciesByName(String name) {
		List<Policy> policyEntities = policyRepository.findByNameContaining(name);
		return policyEntities.stream().map(Policy -> {
			PolicyDTO policyDTO = new PolicyDTO();
			BeanUtils.copyProperties(Policy, policyDTO);
			return policyDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<PolicyDTO> searchPoliciesByCoverage(String coverage) {
		List<Policy> policyEntities = policyRepository.findByCoverageContaining(coverage);
		return policyEntities.stream().map(Policy -> {
			PolicyDTO policyDTO = new PolicyDTO();
			BeanUtils.copyProperties(Policy, policyDTO);
			return policyDTO;
		}).collect(Collectors.toList());
	}
}
