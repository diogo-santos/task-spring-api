package com.task.service;

import com.task.service.dao.Task;
import com.task.service.dao.TaskRepository;
import com.task.service.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerIT {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TaskRepository taskRepo;

	@Test
	public void givenUserId_WhenPerformGet_ThenListTasksIsReturned() throws Exception {
		//Given
		final Long userIdTest = 1L;
		Task taskToSave = new Task();
		taskToSave.setUserId(userIdTest);
		taskToSave.setDescription("Get task description");
		taskToSave.setChecked(false);
		Task task = taskRepo.save(taskToSave);
		assertThat(task, notNullValue());

		//When
		ResultActions getTasksResponse = mockMvc.perform(get("/user/{userId}/tasks", userIdTest))
				.andExpect(status().isOk());
		//Then
		getTasksResponse
				.andExpect(jsonPath("$.[?(@.userId == %s)]", userIdTest).doesNotExist())
				.andExpect(jsonPath("$.[?(@.id == %s)]", task.getId()).exists())
				.andExpect(jsonPath("$.[?(@.description == '%s')]", taskToSave.getDescription()).exists())
				.andExpect(jsonPath("$.[?(@.checked == %s)]", false).exists())
				.andExpect(jsonPath("$.[?(@.lastUpdate == '%s')]", task.getLastUpdate()).exists());
	}
}