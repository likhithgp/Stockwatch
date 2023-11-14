package com.learn.stockwatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.web.client.RestTemplate;

import com.learn.stockwatch.model.dao.StockDetails;
import com.learn.stockwatch.model.dto.StockDetailsDTO;
import com.learn.stockwatch.repository.StockwatchRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class StockWatchIntegrationTest {

	@Autowired
	private StockwatchRepository repo;

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost:";

	private static RestTemplate restTemplate;

	@BeforeAll
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public static void intit() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setup() {

		baseUrl = baseUrl.concat(port + "").concat("/stockwatch/api/v1/");

	}

	@Test
	@Sql(statements = "Delete from stock_details where id BETWEEN 1 AND 1001;", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testPostMethosForAddStcokDetails() {

		StockDetails data = new StockDetails(null, "ABC INC.", "AB", "Pound", 400.00, 301.55, 456.82);
		baseUrl = baseUrl.concat("stock");
		StockDetailsDTO response = restTemplate.postForObject(baseUrl, data, StockDetailsDTO.class);
		assertNotNull(response);
		assertEquals("ABC INC.", response.getStockName());

	}

	@Test
	@Sql(statements = "insert into stock_details (id, stock_Name, stock_Symbol, Currency_Type, current_Price, high_Of52weeks, low_Of52weeks) values (1002, 'Likhith INC', 'ARL', 'Euro', 5487.22, 1254.91, 3731.53);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from stock_details where id BETWEEN 1 AND 1002;", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetStcokDetails() {

		baseUrl = baseUrl.concat("stock/Likhith INC");
		StockDetailsDTO response = restTemplate.getForObject(baseUrl, StockDetailsDTO.class);
		assertNotNull(response);
		assertEquals("Likhith INC", response.getStockName());

	}

	@Test
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from stock_details where id BETWEEN 1 AND 1002;", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetAllStcokDetails() {

		baseUrl = baseUrl.concat("stocks");
		List<StockDetails> response = restTemplate.getForObject(baseUrl, List.class);
		assertNotNull(response);
		assertEquals(1000, response.size());

	}

}
