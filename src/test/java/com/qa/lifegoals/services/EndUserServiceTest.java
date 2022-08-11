package com.qa.lifegoals.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.lifegoals.LifeGoalsApplication;
import com.qa.lifegoals.config.Config;
import com.qa.lifegoals.dtos.EndUserDTO;
import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.repositories.EndUserRepo;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class EndUserServiceTest {

	@Autowired
	EndUserService service;

	@MockBean
	EndUserRepo repo;

	// CREATE TEST
	@Test
	public void testAddEndUser() {
		EndUser endUser = new EndUser("kjones@qa.com", false);

		Mockito.when(repo.save(endUser)).thenReturn(endUser);

		Assertions.assertEquals(service.addEndUser(endUser), endUser);

		Mockito.verify(this.repo, Mockito.times(1)).save(endUser);
	}

	// READ TESTS

	@Test
	public void testGetAllEndUsers() {
		EndUser endUser1 = new EndUser(1L, "user1", false);
		EndUser endUser2 = new EndUser(2L, "user2", false);

		List<EndUser> EndUsers = List.of(endUser1, endUser2);

		List<EndUserDTO> EndUserDTOs = List.of(
				new EndUserDTO(endUser1.getEndUserId(), endUser1.getEndUserName(), endUser1.getIsLoggedin()),
				new EndUserDTO(endUser2.getEndUserId(), endUser2.getEndUserName(), endUser2.getIsLoggedin()));

		Mockito.when(repo.findAll()).thenReturn(EndUsers);

		Assertions.assertEquals(service.getAllEndUsers(), EndUserDTOs);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		;
	}

	@Test
	public void testSearchByIsLoggedIn() {
		EndUser expected = new EndUser(1L, "user1", true);

		Mockito.when(repo.findByIsLoggedin(true)).thenReturn(expected);

		Assertions.assertEquals(service.searchByIsLoggedin(true), expected);

		Mockito.verify(this.repo, Mockito.times(1)).findByIsLoggedin(true);
	}

	// UPDATE TEST

	@Test
	public void testUpdateEndUser() {
		String search = "user1";
		EndUser expected = new EndUser(1L, "user1", true);

		Mockito.when(repo.findByEndUserName(search)).thenReturn(Optional.of(expected));
		Mockito.when(repo.setOtherLoggedInToFalse(search)).thenReturn(1);
		Mockito.when(repo.save(expected)).thenReturn(expected);

		Assertions.assertEquals(service.updateEndUser(search, expected), expected);

		Mockito.verify(this.repo, Mockito.times(1)).findByEndUserName(search);
		Mockito.verify(this.repo, Mockito.times(1)).setOtherLoggedInToFalse(search);
		Mockito.verify(this.repo, Mockito.times(1)).save(expected);
	}

	// DELETE TEST

	@Test
	public void testRemoveEndUser() {

		Long endUserId = 1L;
		boolean expected = true;

		// Mockito.when(repo.deleteById(endUserId)).thenReturn(1);

		Mockito.when(repo.existsById(endUserId)).thenReturn(false);

		Assertions.assertEquals(service.removeEndUser(endUserId), expected);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(endUserId);
	}
}
