package com.qa.lifegoals.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long endUserId;

	@Column(unique = true)
	private String endUserName;

	@Column
	private Boolean isLoggedin = false;

}
