package com.learn.stockwatch;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.learn.stockwatch.model.dao.StockDetails;
import com.learn.stockwatch.repository.StockwatchRepository;
import com.learn.stockwatch.service.StockWatchServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StockwatchServiceTest {

	@Mock
	private StockwatchRepository stockRepo;

	@InjectMocks
	private StockWatchServiceImpl stockwatchService;

	@BeforeAll
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public static void init() {

	}

	@Test
	@Sql(statements = "Delete from stock_details where id= 1001;", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void afterSavingTheStockDetailsItShouldReturnStockDetailsWholeObject() {

		StockDetails details = new StockDetails(null, "London Stock Exchange", "LSGE", "EURO", 233.43, 200.87, 300.34);

		when(stockRepo.save(any())).thenReturn(details);
		stockwatchService.saveStockDetails(details);
		verify(stockRepo, times(1)).save(any());
	}

	@Test
	@Sql(statements = "Delete from stock_details where id BETWEEN 1 AND 1001;", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getTheStockDetailsByNameItShouldReturnStockDetailsWholeObject() {

		StockDetails details = new StockDetails(null, "London Stock Exchange", "LSGE", "EURO", 233.43, 200.87, 300.34);

		when(stockRepo.findByStockName(any())).thenReturn(details);
		stockwatchService.getStockDetails("London Stock Exchange");
		verify(stockRepo, times(1)).findByStockName("London Stock Exchange");
	}

	@Test
	@Sql(statements = "Delete from stock_details where id BETWEEN 1 AND 1001;", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getTheAllStockDetailsByNameItShouldReturnAllStockDetails() {

		StockDetails details = new StockDetails(null, "London Stock Exchange", "LSGE", "EURO", 233.43, 200.87, 300.34);

		when(stockRepo.findAll()).thenReturn(List.of(details));
		stockwatchService.getAllStocks();
		verify(stockRepo, times(1)).findAll();
	}
}
