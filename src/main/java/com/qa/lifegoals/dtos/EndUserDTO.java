package com.qa.lifegoals.dtos;

import com.qa.lifegoals.entities.EndUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndUserDTO {

	private Long endUserId;

	private String endUserName;

	private Boolean isLoggedin;

	public EndUserDTO(EndUser endUser) {
		this.endUserId = endUser.getEndUserId();
		this.endUserName = endUser.getEndUserName();
		this.isLoggedin = endUser.getIsLoggedin();
	}
}
