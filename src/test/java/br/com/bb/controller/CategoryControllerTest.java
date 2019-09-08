package br.com.bb.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bb.Application;
import br.com.bb.entity.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryControllerTest {

	private Logger log = LoggerFactory.logger(CategoryControllerTest.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void a_post() throws Exception {
		Category category = new Category(4L, "Carro");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(category);

		mockMvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string("{\"id\":4,\"name\":\"Carro\"}")).andExpect(jsonPath("$.id").value("4"))
				.andExpect(jsonPath("$.name").value("Carro"));

		log.info("::: Create - " + category.toString());
	}

	@Test
	public void b_put() throws Exception {
		Category category = new Category(4L, "Casa");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(category);

		mockMvc.perform(put("/category").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string("{\"id\":4,\"name\":\"Casa\"}")).andExpect(jsonPath("$.id").value("4"))
				.andExpect(jsonPath("$.name").value("Casa"));

		log.info("::: Update - " + category.toString());
	}

	@Test
	public void c_listAll() throws Exception {
		mockMvc.perform(get("/category/listAll")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Alimentos")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Eletrodomésticos")))
				.andExpect(jsonPath("$[2].id", is(3))).andExpect(jsonPath("$[2].name", is("Móveis")))
				.andExpect(jsonPath("$[3].id", is(4))).andExpect(jsonPath("$[3].name", is("Casa")));
	}

	@Test
	public void d_delete() throws Exception {
		Category category = new Category(4L, "Casa");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(category);

		mockMvc.perform(delete("/category").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());

		log.info("::: Delete - " + category.toString());
	}

	@Test
	public void e_listAll() throws Exception {
		mockMvc.perform(get("/category/listAll")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Alimentos")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Eletrodomésticos")))
				.andExpect(jsonPath("$[2].id", is(3))).andExpect(jsonPath("$[2].name", is("Móveis")));
	}
}