package com.qa.lifegoals.dtos;

import com.qa.lifegoals.entities.EndUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndUserDTO {

	private Long id;

	private String name;

	private String description;

	public EndUserDTO(EndUser endUser) {
		this.id = endUser.getId();
		this.name = endUser.getName();
	}
}
