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
import com.qa.lifegoals.dtos.EndUserDTO;
import com.qa.lifegoals.entities.EndUser;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EndUserControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;

	private EndUserDTO mapToDTO(EndUser EndUser) {
		return mapper.map(EndUser, EndUserDTO.class);
	}

	private final EndUser newUser = new EndUser(3L, "user3", false);

	private final String user1Name = "user1";
	private final EndUser user1 = new EndUser(1L, "user1", false);
	private final EndUser updatedUser1 = new EndUser(1L, "user1", false);
	private final EndUser user2 = new EndUser(2L, "user2", true);

	@Test
	public void testCreate() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/create");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		try {
			mockRequest.content(this.jsonifier.writeValueAsString(newUser));

			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content()
					.json(this.jsonifier.writeValueAsString(newUser));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Test public void testGetAll() { MockHttpServletRequestBuilder mockRequest =
	 * MockMvcRequestBuilders.request(HttpMethod.GET, "/getAll"); //
	 * mockRequest.contentType(MediaType.APPLICATION_JSON); try {
	 * 
	 * mockRequest.accept(MediaType.APPLICATION_JSON);
	 * 
	 * ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
	 * 
	 * ResultMatcher matchContent = MockMvcResultMatchers.content()
	 * .json(this.jsonifier.writeValueAsString(newUser));
	 * this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent)
	 * ; } catch (JsonProcessingException e) { e.printStackTrace(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */
	@Test
	public void testSearch() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/search");
		// mockRequest.contentType(MediaType.APPLICATION_JSON);
		try {

			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(user2));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Test public void testUpdate() { MockHttpServletRequestBuilder mockRequest =
	 * MockMvcRequestBuilders.request(HttpMethod.PATCH, "/update/user1");
	 * mockRequest.contentType(MediaType.APPLICATION_JSON); try {
	 * mockRequest.content(this.jsonifier.writeValueAsString(user1));
	 * mockRequest.accept(MediaType.APPLICATION_JSON);
	 * 
	 * ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
	 * 
	 * ResultMatcher matchContent = MockMvcResultMatchers.content()
	 * .json(this.jsonifier.writeValueAsString(newUser));
	 * this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent)
	 * ; } catch (JsonProcessingException e) { e.printStackTrace(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */
	@Test
	public void testDelete() {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/delete/1");
		// mockRequest.contentType(MediaType.APPLICATION_JSON);
		try {
			// mockRequest.content(this.jsonifier.writeValueAsString(user1));
			mockRequest.accept(MediaType.APPLICATION_JSON);

			ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

			ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(user1));
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
