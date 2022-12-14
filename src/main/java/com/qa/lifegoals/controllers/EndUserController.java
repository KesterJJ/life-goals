package com.qa.lifegoals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.services.EndUserService;

@RestController
public class EndUserController {

	@Autowired
	private EndUserService service;

	@PostMapping("/create")
	public EndUser create(@RequestBody EndUser endUser) {
		return service.addEndUser(endUser);
	}

	// READ

	@GetMapping("/search")
	public EndUser search() {
		return service.searchByIsLoggedin(true);
	}

	@PatchMapping("/update/{search}")
	public EndUser update(@PathVariable("search") String search, @RequestBody EndUser endUser) {
		return service.updateEndUser(search, endUser);
	}

	@DeleteMapping("/delete/{endUserId}")
	public boolean delete(@PathVariable("endUserId") Long endUserId) {
		return service.removeEndUser(endUserId);
	}
}
