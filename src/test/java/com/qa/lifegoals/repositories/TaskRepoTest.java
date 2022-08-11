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
import com.qa.lifegoals.entities.Task;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class TaskRepoTest {

	@Autowired
	TaskRepo repo;

	@MockBean
	Goal Goal;

	@MockBean
	EndUser endUser;

	@Test
	public void testFindAllByGoalId() {
		EndUser user = new EndUser(1L, "user1", false);
		Goal goal1 = new Goal(1L, "goal1", "user1", user);
		Task task = new Task(1L, "task1", "goal1", goal1);
		Long goal1Id = 1L;
		List<Task> expected = List.of(task);

		Assertions.assertEquals(repo.findAllByGoalId(goal1Id), expected);

	}
}
