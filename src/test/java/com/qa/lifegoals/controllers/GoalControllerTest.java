package com.qa.lifegoals.controllers;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.lifegoals.dtos.GoalDTO;
import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.entities.Goal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GoalControllerTest {
	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;

	private GoalDTO mapToDTO(Goal Goal) {
		return mapper.map(Goal, GoalDTO.class);
	}

	private final EndUser user1 = new EndUser(1L, "user1", false);
	private final EndUser user2 = new EndUser(2L, "user2", true);

	private final Long newId = 4L;
	private final Goal newGoal = new Goal(4L, "goal4", "user1", user1);

	private final Long user1Id = 1L;
	private final String user1Name = "user1";

	private final Goal goal1 = new Goal(1L, "goal1", "user1", user1);
	private final Goal goal3 = new Goal(3L, "goal3", "user2", user2);

	@Test
	public void testCreate() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/createGoal");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		try {
			mockRequest.content(this.jsonifier.writeValueAsString(newGoal));

			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content()
					.json(this.jsonifier.writeValueAsString(newGoal));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Test public void testGetAllGoals() { MockHttpServletRequestBuilder
	 * mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
	 * "/getAllGoals/2"); // mockRequest.contentType(MediaType.APPLICATION_JSON);
	 * try {
	 * 
	 * mockRequest.accept(MediaType.APPLICATION_JSON);
	 * 
	 * ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
	 * 
	 * ResultMatcher matchContent =
	 * MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(goal3)
	 * );
	 * this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent)
	 * ; } catch (JsonProcessingException e) { e.printStackTrace(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * @Test public void testUpdate() { MockHttpServletRequestBuilder mockRequest =
	 * MockMvcRequestBuilders.request(HttpMethod.PATCH, "/updateGoal/1");
	 * mockRequest.contentType(MediaType.APPLICATION_JSON); try {
	 * mockRequest.content(this.jsonifier.writeValueAsString(goal1));
	 * mockRequest.accept(MediaType.APPLICATION_JSON);
	 * 
	 * ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
	 * 
	 * ResultMatcher matchContent =
	 * MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(goal1)
	 * );
	 * this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent)
	 * ; } catch (JsonProcessingException e) { e.printStackTrace(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	@Test
	public void testDelete() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/deleteGoal/1");
		// mockRequest.contentType(MediaType.APPLICATION_JSON);
		try {
			// mockRequest.content(this.jsonifier.writeValueAsString(user1));
			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(goal1));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
