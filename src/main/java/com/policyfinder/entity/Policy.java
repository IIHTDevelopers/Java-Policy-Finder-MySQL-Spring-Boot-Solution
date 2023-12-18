package com.policyfinder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "policies")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Policy name is required")
	private String name;

	@NotNull(message = "Tenure is required")
	private Integer tenure; // Tenure in months

	@NotNull(message = "Amount is required")
	private Double amount;

	@NotNull(message = "Interest rate is required")
	private Double interestRate;

	@NotBlank(message = "Coverage information is required")
	private String coverage;

	public Policy() {
		super();
	}

	public Policy(Long id, @NotBlank(message = "Policy name is required") String name,
			@NotNull(message = "Tenure is required") Integer tenure,
			@NotNull(message = "Amount is required") Double amount,
			@NotNull(message = "Interest rate is required") Double interestRate,
			@NotBlank(message = "Coverage information is required") String coverage) {
		super();
		this.id = id;
		this.name = name;
		this.tenure = tenure;
		this.amount = amount;
		this.interestRate = interestRate;
		this.coverage = coverage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", name=" + name + ", tenure=" + tenure + ", amount=" + amount + ", interestRate="
				+ interestRate + ", coverage=" + coverage + "]";
	}
}
