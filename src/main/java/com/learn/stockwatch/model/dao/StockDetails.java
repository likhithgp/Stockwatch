package com.learn.stockwatch.model.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(nullable = false, unique = true)
	@Size(min = 3, max = 200, message = "Input Size for this field is Between 3 to 200")
	@Schema(description = "Company Name", example = "Apple INC")
	String stockName;

	@Column
	@Size(min = 3, max = 10, message = "Input Size for this field is Between 3 to 10")
	@Schema(description = "Symbol in Stock-market", example = "AAPL(Apple INC symbol in NASDAQ)")
	String stockSymbol;

	@Column
	@Schema(description = "Currency", example = "Rupee")
	String currencyType;

	@Column
	@Schema(description = "Stock price", example = "181.83")
	double currentPrice;

	@Column
	@Schema(description = "Maximum price in 52 weeks ", example = "185.65")
	double highOf52weeks;

	@Column
	@Schema(description = "Minimum price in 52 weeks", example = "180.55")
	double lowOf52weeks;

}
