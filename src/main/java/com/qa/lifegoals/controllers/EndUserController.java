package com.qa.lifegoals.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lifegoals.dtos.EndUserDTO;
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
	@GetMapping("/getAll")
	public List<EndUserDTO> getAll() {
		return this.service.getAllEndUsers();
	}

	@GetMapping("/getOne/{id}")
	public EndUser getOne(@PathVariable("id") Long id) {
		return service.getOneEndUser(id);
	}

	/*
	 * @GetMapping("/search") public List<EndUser> search(@PathParam("search")
	 * String search) { if (service.searchByName("a").size() >= 1) { return
	 * 
	 * service.searchByName("a"); } else if (service.searchByDescription("a").size()
	 * >= 1) { return service.searchByName("a"); } else { return null; } }
	 */

	@PatchMapping("/update")
	public EndUser update(@PathParam("id") Long id, @RequestBody EndUser endUser) {
		return service.updateEndUser(id, endUser);
	}

	@DeleteMapping("/delete")
	public boolean delete(@PathParam("id") Long id) {
		return service.removeEndUser(id);
	}
}
