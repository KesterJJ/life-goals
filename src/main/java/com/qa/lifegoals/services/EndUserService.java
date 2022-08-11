package com.qa.lifegoals.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.lifegoals.dtos.EndUserDTO;
import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.repositories.EndUserRepo;

@Service
public class EndUserService {
	@Autowired
	private EndUserRepo repo;

	@Autowired
	private ModelMapper mapper;

	private EndUserDTO mapToDTO(EndUser endUser) {
		return mapper.map(endUser, EndUserDTO.class);
	}

	// CREATE
	public EndUser addEndUser(EndUser endUser) {
		return this.repo.save(endUser);
	}

	// READ
	public List<EndUserDTO> getAllEndUsers() {
		return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public EndUser searchByIsLoggedin(Boolean isTrue) {
		return repo.findByIsLoggedin(isTrue);
	}

	// UPDATE
	public EndUser updateEndUser(String search, EndUser endUser) {
		Optional<EndUser> existenceCheckerOptional = this.repo.findByEndUserName(search);
		EndUser existingEndUser = existenceCheckerOptional.orElse(new EndUser());
		System.out.println(this.repo.setOtherLoggedInToFalse(search));

		System.out.println(endUser.getIsLoggedin());
		existingEndUser.setIsLoggedin(endUser.getIsLoggedin());

		return this.repo.save(existingEndUser);
	}

	// DELETE
	public boolean removeEndUser(Long endUserId) {
		repo.deleteById(endUserId);
		boolean exists = this.repo.existsById(endUserId);
		return !exists;
	}
}
