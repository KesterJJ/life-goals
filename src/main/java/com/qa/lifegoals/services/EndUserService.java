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

	public EndUser getOneEndUser(Long id) {
		Optional<EndUser> existenceCheckerOptional = this.repo.findById(id);
		EndUser existingEndUser = existenceCheckerOptional.orElseThrow();
		return existingEndUser;
	}

	/*
	 * public List<EndUser> searchByName(String name) { return
	 * repo.findEndUserBySearchName("a"); }
	 * 
	 * public List<EndUser> searchByDescription(String description) { return
	 * repo.findEndUserBySearchDescription("a"); }
	 */

	// UPDATE
	public EndUser updateEndUser(Long id, EndUser endUser) {
		Optional<EndUser> existenceCheckerOptional = this.repo.findById(id);
		EndUser existingEndUser = existenceCheckerOptional.orElse(new EndUser());

		existingEndUser.setName(endUser.getName());

		return this.repo.save(existingEndUser);
	}

	// DELETE
	public boolean removeEndUser(Long id) {
		repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}
