package com.learn.stockwatch.model.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDetailsDTO {

	@JsonIgnore
	Integer id;

	String stockName;

	String stockSymbol;

	String currencyType;

	double currentPrice;

	double highOf52weeks;

	double lowOf52weeks;

}
