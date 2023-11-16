package com.learn.stockwatch.service;

import static com.learn.stockwatch.constant.StockWatchConstant.SORTING_TYPE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.stockwatch.model.dao.StockDetails;
import com.learn.stockwatch.model.dto.StockDetailsDTO;
import com.learn.stockwatch.repository.StockwatchRepository;
import com.learn.stockwatch.util.StockWatchConversionUtility;

@Service
public class StockWatchServiceImpl implements StockWatchService {

	private final Logger logger = LoggerFactory.getLogger(StockWatchServiceImpl.class);

	private StockwatchRepository stockRepo;

	public StockWatchServiceImpl(StockwatchRepository stockRepo) {
		this.stockRepo = stockRepo;
	}

	@Override
	public StockDetailsDTO saveStockDetails(StockDetails stockDetails) {
		// TODO Auto-generated method stub
		logger.info("Started executing method to save data to DB");
		StockDetails details = stockRepo.save(stockDetails);
		logger.info("End of method execution save data to DB");
		return conversionToDtoUsingUtil(details);
	}

	@Override
	public StockDetailsDTO updateStockDetails(StockDetailsDTO stockDetailsDTO, String stockName) {
		// TODO Auto-generated method stub
		logger.info("Started executing method to update stock details for company {}", stockName);
		StockDetails details = conversionToEnityUsingUtil(stockDetailsDTO);

		StockDetailsDTO detailsFromDB = getStockDetails(stockName);
		details.setId(detailsFromDB.getId());
		details = stockRepo.save(details);

		logger.info("End of method execution update stock details for company {}", stockName);
		return conversionToDtoUsingUtil(details);
	}

	@Override
	public StockDetailsDTO getStockDetails(String stockName) {
		// TODO Auto-generated method stub
		logger.info("Started executing method to get stock details for company {}", stockName);
		StockDetails detailsFromDB = stockRepo.findByStockName(stockName);
		logger.info("End of method execution to get stock details for company {}", stockName);
		return conversionToDtoUsingUtil(detailsFromDB);
	}

	@Override
	public StockDetailsDTO getStockDetailsBySymbol(String stockSymbol) {
		// TODO Auto-generated method stub
		logger.info("Started executing method to get stock details from its symbol {}", stockSymbol);
		StockDetails details = stockRepo.findByStockSymbol(stockSymbol);
		logger.info("End of method execution to get stock details from its symbol {}", stockSymbol);
		return conversionToDtoUsingUtil(details);
	}

	@Override
	public List<StockDetails> getAllStocks() {
		// TODO Auto-generated method stub
		logger.info("Executing method to get stock details all company {}");
		return stockRepo.findAll();
	}

	@Override
	public List<StockDetails> getAllStocksBySorting(String sortColoumName, String sortType) {
		// TODO Auto-generated method stub
		logger.info("started Executing method to get stock details sorted by coloum {}", sortColoumName);
		Direction direction = Direction.ASC;

		if (SORTING_TYPE.equals(sortType))
			direction = Direction.DESC;
		logger.info("End of Executing method to get stock details sorted by coloum {}", sortColoumName);
		return stockRepo.findAll(Sort.by(direction, sortColoumName));
	}

	@Override
	public Page<StockDetails> getAllStocksByPagination(int offset, int pageSize) {
		// TODO Auto-generated method stub
		logger.info("Executing method to get stock details by pagination for page size {} and offset {}", pageSize,
				offset);
		return stockRepo.findAll(PageRequest.of(offset, pageSize));
	}

	@Override
	@Transactional
	public void deleteStockDetails(String stockName) {
		// TODO Auto-generated method stub
		logger.info("Executing method to delete stock details of a company", stockName);
		stockRepo.deleteByStockName(stockName);
	}

	@Override
	public Page<StockDetails> getAllStocksByPaginationAndSort(int offset, int pageSize, String sortColoumName,
			String sortOder) {
		// TODO Auto-generated method stub
		logger.info("Executing method to get stock details by pagination and sort");
		Direction direction = Direction.ASC;

		if (SORTING_TYPE.equals(sortOder))
			direction = Direction.DESC;
		return stockRepo.findAll(PageRequest.of(offset, pageSize, Sort.by(direction, sortColoumName)));
	}

	private StockDetailsDTO conversionToDtoUsingUtil(StockDetails details) {

		return StockWatchConversionUtility.stockEntityToStockDTO(details);
	}

	private StockDetails conversionToEnityUsingUtil(StockDetailsDTO details) {
		// TODO change method name
		return StockWatchConversionUtility.stockDTOToStockEntity(details);
	}

	@Override
	public Page<StockDetails> getAllStocksByFilter(int pageSize, int offset, Double min, Double max, String sortField,
			String sortOrder) {
		// TODO Auto-generated method stub
		logger.info("Executing method to get stock details to filter by current price min value {} and max value {}",
				min, max);
		Direction direction = Direction.ASC;

		if (SORTING_TYPE.equals(sortOrder))
			direction = Direction.DESC;
		PageRequest pageable = PageRequest.of(offset, pageSize, Sort.by(direction, sortField));
		return stockRepo.findByCurrentPriceBetween(min, max, pageable);
	}

}
