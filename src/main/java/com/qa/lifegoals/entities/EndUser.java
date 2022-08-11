package com.qa.lifegoals.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndUser {

	public EndUser(String endUserName) {
		this.endUserName = endUserName;
	}

	public EndUser(String endUserName, Boolean isLoggedin) {
		this.endUserName = endUserName;
		this.isLoggedin = isLoggedin;
	}

	public EndUser(Boolean isLoggedin) {
		this.isLoggedin = isLoggedin;
	}

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long endUserId;

	@Column(nullable = false, unique = true)
	@NotNull
	private String endUserName;

	@Column(nullable = false)
	@NotNull
	private Boolean isLoggedin = false;

}
