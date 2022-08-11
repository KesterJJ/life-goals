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
import com.qa.lifegoals.dtos.GoalDTO;
import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.repositories.GoalRepo;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class GoalServiceTest {

	@Autowired
	GoalService service;

	@MockBean
	GoalRepo repo;

	@MockBean
	EndUser endUser;

	// CREATE TEST
	@Test
	public void testAddGoal() {
		Goal goal = new Goal("goal4", "user2", endUser);

		Mockito.when(repo.save(goal)).thenReturn(goal);

		Assertions.assertEquals(service.addGoal(goal), goal);

		Mockito.verify(this.repo, Mockito.times(1)).save(goal);
	}

	// READ TESTS

	@Test
	public void testGetAllGoals() {
		EndUser user1 = new EndUser("user1", false);
		Goal goal1 = new Goal("goal1", "user1", user1);
		Goal goal2 = new Goal("goal2", "user1", user1);

		Long endUserId = 1L;

		List<Goal> Goals = List.of(goal1, goal2);

		List<GoalDTO> GoalDTOs = List.of(
				new GoalDTO(goal1.getGoalId(), goal1.getGoalName(), goal1.getGoalDescription()),
				new GoalDTO(goal2.getGoalId(), goal2.getGoalName(), goal2.getGoalDescription()));

		Mockito.when(repo.findAllByEndUserId(endUserId)).thenReturn(Goals);

		Assertions.assertEquals(service.getAllGoals(endUserId), GoalDTOs);

		Mockito.verify(this.repo, Mockito.times(1)).findAllByEndUserId(endUserId);
		;
	}

	@Test
	public void testGetOneGoal() {
		EndUser user1 = new EndUser("user1", false);

		Goal expected = new Goal(1L, "goal1", "user1", user1);
		Long goalId = 1L;

		Mockito.when(repo.findById(goalId)).thenReturn(Optional.of(expected));

		Assertions.assertEquals(service.getOneGoal(1L), expected);

		Mockito.verify(this.repo, Mockito.times(1)).findById(goalId);
	}

	// UPDATE TEST

	@Test
	public void testUpdateGoal() {
		String search = "user1";
		Long id = 1L;
		EndUser user1 = new EndUser("user1", false);
		Goal expected = new Goal(1L, "goal1", "user1", user1);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expected));
		Mockito.when(repo.save(expected)).thenReturn(expected);

		Assertions.assertEquals(service.updateGoal(id, expected), expected);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(expected);
	}

	// DELETE TEST

	@Test
	public void testRemoveGoal() {

		Long GoalId = 1L;
		boolean expected = true;

		// Mockito.when(repo.deleteById(GoalId)).thenReturn(1);

		Mockito.when(repo.existsById(GoalId)).thenReturn(false);

		Assertions.assertEquals(service.removeGoal(GoalId), expected);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(GoalId);
	}
}
