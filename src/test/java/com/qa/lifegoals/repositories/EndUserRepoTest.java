package com.qa.lifegoals.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.lifegoals.LifeGoalsApplication;
import com.qa.lifegoals.config.Config;
import com.qa.lifegoals.entities.EndUser;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class EndUserRepoTest {
	@Autowired
	EndUserRepo repo;

	@MockBean
	EndUser endUser;

	@Test
	public void testSetLoggedInToTrue() {
		String search = "user1";
		Integer expected = 1;

		Assertions.assertEquals(repo.setLoggedInToTrue(search), expected);

	}

	@Test
	public void testSetOtherLoggedInToFalse() {
		String search = "user1";
		Integer expected = 1;

		Assertions.assertEquals(repo.setOtherLoggedInToFalse(search), expected);

	}

	@Test
	public void testFindByEndUserName() {
		String search = "user1";
		EndUser endUser = new EndUser(1L, "user1", false);
		Optional<EndUser> expected = Optional.of(endUser);

		Assertions.assertEquals(repo.findByEndUserName(search), expected);

	}

	@Test
	public void testFindByIsLoggedin() {
		Boolean search = true;

		EndUser expected = new EndUser(2L, "user2", true);

		Assertions.assertEquals(expected, repo.findByIsLoggedin(search));

	}
}
