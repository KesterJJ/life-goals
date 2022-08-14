package com.qa.lifegoals.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.qa.lifegoals.LifeGoalsApplication;
import com.qa.lifegoals.config.Config;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class EntityTests {

	@Test
	public void TaskTest() {
		Task task = new Task("khghK", "kglj");
		Assertions.assertTrue(task instanceof Task);
	}

	@Test
	public void GoalTest() {
		Goal goal = new Goal("khghK", "kglj");
		Assertions.assertTrue(goal instanceof Goal);
	}

	@Test
	public void EndUserTest() {
		EndUser endUser = new EndUser("khghK");
		Assertions.assertTrue(endUser instanceof EndUser);
		EndUser endUser2 = new EndUser(false);
		Assertions.assertTrue(endUser2 instanceof EndUser);
	}

}
