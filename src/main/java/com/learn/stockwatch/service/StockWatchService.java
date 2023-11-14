package com.learn.stockwatch.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.learn.stockwatch.model.dao.StockDetails;
import com.learn.stockwatch.model.dto.StockDetailsDTO;

public interface StockWatchService {

	/**
	 * To add new company Stock details
	 * 
	 * @param stockDetails
	 * @return
	 */
	public StockDetailsDTO saveStockDetails(StockDetails stockDetails);

	/**
	 * To Update Stock details of existing company
	 * 
	 * @param stockDetailsDTO
	 * @param stockName
	 * @return
	 */

	public StockDetailsDTO updateStockDetails(StockDetailsDTO stockDetailsDTO, String stockName);

	/**
	 * To get Single company stock details by its name
	 * 
	 * @param stockName
	 * @return
	 */
	public StockDetailsDTO getStockDetails(String stockName);

	/**
	 * To get Single company stock details by its symbol
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public StockDetailsDTO getStockDetailsBySymbol(String stockSymbol);

	/**
	 * To get all the company stock details which are present in DataBase
	 * 
	 * @return
	 */
	public List<StockDetails> getAllStocks();

	/**
	 * To get all the company stock details which are present in DataBase in SOrted
	 * based on preferred column data
	 * 
	 * @param sortColoumName
	 * @param sortType
	 * @return
	 */
	public List<StockDetails> getAllStocksBySorting(String sortColoumName, String sortType);

	/**
	 * To get company stock details in chunk of pages of preferred size
	 * 
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public Page<StockDetails> getAllStocksByPagination(int offset, int pageSize);

	/**
	 * To get company stock details in chunk of pages of preferred size for sorted
	 * data of preferred column
	 * 
	 * @param offset
	 * @param pageSize
	 * @param sortColoumName
	 * @return
	 */
	public Page<StockDetails> getAllStocksByPaginationAndSort(int offset, int pageSize, String sortColoumName);

	/**
	 * To delete stock details of the company based on stock name
	 * 
	 * @param stockName
	 */
	public void deleteStockDetails(String stockName);

}
