package com.qa.lifegoals.controllers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.entities.Task;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;

	private final EndUser user1 = new EndUser(1L, "user1", false);

	private final Goal goal1 = new Goal(1L, "goal1", "user1", user1);
	private final Goal goal2 = new Goal(2L, "goal2", "user1", user1);

	private final Task newTask = new Task(3L, "task3", "goal1", goal1);

	private final Task task1 = new Task(1L, "task1", "goal1", goal1);
	private final Task task2 = new Task(2L, "task2", "goal2", goal2);

	@Test
	public void testCreate() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/createTask");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		try {
			mockRequest.content(this.jsonifier.writeValueAsString(newTask));

			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content()
					.json(this.jsonifier.writeValueAsString(newTask));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Test public void testGetAllTasks() {
	 * 
	 * MockHttpServletRequestBuilder mockRequest =
	 * MockMvcRequestBuilders.request(HttpMethod.GET, "/getAllTasks/2"); //
	 * mockRequest.contentType(MediaType.APPLICATION_JSON); try {
	 * 
	 * mockRequest.accept(MediaType.APPLICATION_JSON);
	 * 
	 * ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
	 * 
	 * ResultMatcher matchContent =
	 * MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(task2)
	 * );
	 * this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent)
	 * ; } catch (JsonProcessingException e) { e.printStackTrace(); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	@Test
	public void create() {
		try {
			mock.perform(post("/createTask").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(newTask)))

					.andExpect(status().isOk())
					.andExpect((ResultMatcher) content().json(this.jsonifier.writeValueAsString(newTask)));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * @Test public void testUpdate() { MockHttpServletRequestBuilder mockRequest =
	 * MockMvcRequestBuilders.request(HttpMethod.PATCH, "/updateTask/1");
	 * mockRequest.contentType(MediaType.APPLICATION_JSON); try {
	 * mockRequest.content(this.jsonifier.writeValueAsString(task1));
	 * mockRequest.accept(MediaType.APPLICATION_JSON);
	 * 
	 * ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
	 * 
	 * ResultMatcher matchContent =
	 * MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(task1)
	 * );
	 * this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent)
	 * ; } catch (JsonProcessingException e) { e.printStackTrace(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	@Test
	public void testDelete() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/deleteTask/1");
		try {
			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(task1));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
