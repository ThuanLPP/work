package interview.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import interview.rest.entity.WorkEntity;

@AutoConfigureMockMvc
@SpringBootTest
public class WorkControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testInit() throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/work")).andExpect(status().isOk()).andReturn();

		String viewName = result.getModelAndView().getViewName();
		assertEquals("index", viewName);

		Map<String, Object> map = result.getModelAndView().getModel();
		Page<WorkEntity> workPage = (Page<WorkEntity>) map.get("workPage");
		assertEquals(3, workPage.getTotalPages());
	}
}
