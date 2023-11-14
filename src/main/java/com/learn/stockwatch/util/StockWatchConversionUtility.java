package com.learn.stockwatch.util;

import org.springframework.beans.BeanUtils;

import com.learn.stockwatch.model.dao.StockDetails;
import com.learn.stockwatch.model.dto.StockDetailsDTO;

public class StockWatchConversionUtility {

	public static StockDetailsDTO stockEntityToStockDTO(StockDetails stockDetails) {

		StockDetailsDTO stockDetailsDTO = new StockDetailsDTO();
		BeanUtils.copyProperties(stockDetails, stockDetailsDTO);

		return stockDetailsDTO;
	}

	public static StockDetails stockDTOToStockEntity(StockDetailsDTO stockDetailsdto) {

		StockDetails stockDetails = new StockDetails();
		BeanUtils.copyProperties(stockDetailsdto, stockDetails);

		return stockDetails;
	}

}
