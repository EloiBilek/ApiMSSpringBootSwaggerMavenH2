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
import br.com.bb.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

	private Logger log = LoggerFactory.logger(ProductControllerTest.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void a_post() throws Exception {
		Product product = new Product(9L, "Pipoca", new Category(1L, "Alimentos"));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content()
						.string("{\"id\":9,\"name\":\"Pipoca\",\"category\":{\"id\":1,\"name\":\"Alimentos\"}}"))
				.andExpect(jsonPath("$.id").value("9")).andExpect(jsonPath("$.name").value("Pipoca"));

		log.info("::: Create - " + product.toString());
	}

	@Test
	public void b_put() throws Exception {
		Product product = new Product(9L, "Pipoca MicroOndas", new Category(1L, "Alimentos"));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string(
						"{\"id\":9,\"name\":\"Pipoca MicroOndas\",\"category\":{\"id\":1,\"name\":\"Alimentos\"}}"))
				.andExpect(jsonPath("$.id").value("9")).andExpect(jsonPath("$.name").value("Pipoca MicroOndas"));

		log.info("::: Update - " + product.toString());
	}

	@Test
	public void c_listAll() throws Exception {
		mockMvc.perform(get("/product/listAll")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(9)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Arroz")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Feijão")))
				.andExpect(jsonPath("$[2].id", is(3))).andExpect(jsonPath("$[2].name", is("Aspirador de pó")))
				.andExpect(jsonPath("$[3].id", is(4))).andExpect(jsonPath("$[3].name", is("Batedeira")))
				.andExpect(jsonPath("$[4].id", is(5))).andExpect(jsonPath("$[4].name", is("Liquidificador")))
				.andExpect(jsonPath("$[5].id", is(6))).andExpect(jsonPath("$[5].name", is("Sofá")))
				.andExpect(jsonPath("$[6].id", is(7))).andExpect(jsonPath("$[6].name", is("Mesa")))
				.andExpect(jsonPath("$[7].id", is(8))).andExpect(jsonPath("$[7].name", is("Estante")))
				.andExpect(jsonPath("$[8].id", is(9))).andExpect(jsonPath("$[8].name", is("Pipoca MicroOndas")));
	}

	@Test
	public void d_delete() throws Exception {
		Product product = new Product(9L, "Pipoca MicroOndas", new Category(1L, "Alimentos"));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(delete("/product").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());

		log.info("::: Delete - " + product.toString());
	}

	@Test
	public void listByCategoryAlimentos() throws Exception {
		mockMvc.perform(get("/product/listByCategory/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("Arroz"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Feijão")));
	}

	@Test
	public void listByCategoryEletrodomésticos() throws Exception {
		mockMvc.perform(get("/product/listByCategory/2")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].id", is(3)))
				.andExpect(jsonPath("$[0].name", is("Aspirador de pó"))).andExpect(jsonPath("$[1].id", is(4)))
				.andExpect(jsonPath("$[1].name", is("Batedeira"))).andExpect(jsonPath("$[2].id", is(5)))
				.andExpect(jsonPath("$[2].name", is("Liquidificador")));
	}

	@Test
	public void listByCategoryMóveis() throws Exception {
		mockMvc.perform(get("/product/listByCategory/3")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].id", is(6)))
				.andExpect(jsonPath("$[0].name", is("Sofá"))).andExpect(jsonPath("$[1].id", is(7)))
				.andExpect(jsonPath("$[1].name", is("Mesa"))).andExpect(jsonPath("$[2].id", is(8)))
				.andExpect(jsonPath("$[2].name", is("Estante")));
	}
}