package com.qa.lifegoals.repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.lifegoals.LifeGoalsApplication;
import com.qa.lifegoals.config.Config;
import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.entities.Goal;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class GoalRepoTest {

	@Autowired
	GoalRepo repo;

	@MockBean
	EndUser endUser;

	@Test
	public void testFindAllByEndUserId() {
		EndUser user2 = new EndUser(2L, "user2", true);
		Goal goal = new Goal(3L, "goal3", "user2", user2);
		Long user2Id = 2L;
		List<Goal> expected = List.of(goal);

		Assertions.assertEquals(repo.findAllByEndUserId(user2Id), expected);

	}

}
