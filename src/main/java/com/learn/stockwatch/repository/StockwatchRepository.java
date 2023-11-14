package com.learn.stockwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.stockwatch.model.dao.StockDetails;

@Repository
public interface StockwatchRepository extends JpaRepository<StockDetails, Integer> {

	/**
	 * Method To return Stock Details by company Name
	 * 
	 * @param stockName = company Name
	 * @return stock details of Company
	 */
	public StockDetails findByStockName(String stockName);

	/**
	 * To delete StockDetails by company name
	 * 
	 * @param stockName
	 */
	public void deleteByStockName(String stockName);

	/**
	 * To get stock details by it symbol
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public StockDetails findByStockSymbol(String stockSymbol);

}
