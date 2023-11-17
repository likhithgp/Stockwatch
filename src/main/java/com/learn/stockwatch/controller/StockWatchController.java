package com.learn.stockwatch.controller;

import static com.learn.stockwatch.constant.StockWatchConstant.SORTING_TYPE_DEFAULT;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.stockwatch.model.dao.StockDetails;
import com.learn.stockwatch.model.dto.StockDetailsDTO;
import com.learn.stockwatch.service.StockWatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("stockwatch/api/v1")
@Tag(name = "Stock Watch", description = "Stock Details API's")
@CrossOrigin(value="http://localhost:4200")
public class StockWatchController {

	private final Logger logger = LoggerFactory.getLogger(StockWatchController.class);

	private StockWatchService stockService;

	public StockWatchController(StockWatchService stockService) {
		this.stockService = stockService;
	}

	@Operation(summary = "To get Stock Details by companyName")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Retrived Stock details Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No detaild found for entered company Name")
			})
	@GetMapping("/stock/{stockName}")
	public ResponseEntity<StockDetailsDTO> getstockDetails(@PathVariable String stockName) {

		logger.info("Called API to get stock of the company {}", stockName);

		return new ResponseEntity<StockDetailsDTO>(stockService.getStockDetails(stockName), HttpStatus.OK);

	}
	
	@Operation(summary = "To get Stock Details by StockSymbol")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Retrived Stock details Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No detaild found for entered company Name") })
	@GetMapping("/stock/symbol/{stockSymbol}")
	public ResponseEntity<StockDetailsDTO> getstockDetailsbyStockSymbol(@PathVariable String stockSymbol) {

		logger.info("Called API to get stock of the company {}", stockSymbol);

		return new ResponseEntity<StockDetailsDTO>(stockService.getStockDetailsBySymbol(stockSymbol), HttpStatus.OK);

	}

	@Operation(summary = "To get all companyName Stock details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Retrived all Stock details Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Data found in server") })
	@GetMapping("/stocks")
	public ResponseEntity<List<StockDetails>> getAllstockDetails() {

		logger.info("API call to get all stock details");
		return new ResponseEntity<List<StockDetails>>(stockService.getAllStocks(), HttpStatus.OK);

	}

	@Operation(summary = "To get all companyName Stock details in Sorted order based on coloum name."
			+ "(If you want in decending order pass DESC)")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retrived all Stock details in sorted Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Data found in server") })
	@GetMapping("/stocks/order/{sortField}")
	public ResponseEntity<List<StockDetails>> getAllstockDetailsBySOrt(@PathVariable String sortField,
			@RequestParam(name = "sortOrder", required = false) String sortOrder) {

		sortOrder = sortOrder == null ? SORTING_TYPE_DEFAULT : sortOrder;

		logger.info("API call to get all stock sorted by coloumn {} in oreder {}", sortField, sortOrder);
		return new ResponseEntity<List<StockDetails>>(stockService.getAllStocksBySorting(sortField, sortOrder),
				HttpStatus.OK);

	}

	@Operation(summary = "To get all companyName Stock details by pagination based on particular size")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Retrived all Stock details in Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Data found in server") })
	@GetMapping("/stocks/Pagenation/{offset}/{pageSize}")
	public ResponseEntity<Page<StockDetails>> getAllstockDetailsPagenation(@PathVariable int offset,
			@PathVariable int pageSize) {

		logger.info("API call to get all stock for offset value {} having page size {}", offset, pageSize);

		return new ResponseEntity<Page<StockDetails>>(stockService.getAllStocksByPagination(offset, pageSize),
				HttpStatus.OK);

	}

	@Operation(summary = "To get all companyName Stock details by pagination of particular size and sorted based "
			+ "on prefernace coloumn name")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retrived  Stock details of given page size in Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Data found in server") })
	@GetMapping("/stocks/Pagenationandsort/{offset}/{pageSize}")
	public ResponseEntity<Page<StockDetails>> getAllstockDetailsPagenationAndSort(@PathVariable int offset,
			@PathVariable int pageSize, @RequestParam String feildName,
			@RequestParam(defaultValue = SORTING_TYPE_DEFAULT)String sortOder) {

		logger.info("API call to get all stock for offset value {} having page size {} Sorted by coloumn {}", offset,
				pageSize, feildName);
		return new ResponseEntity<Page<StockDetails>>(
				stockService.getAllStocksByPaginationAndSort(offset, pageSize, feildName,sortOder), HttpStatus.OK);

	}
	
	
	@Operation(summary = "To get all companyName Stock details by pagination of particular size and sorted and filtered by Current price column ")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retrived  Stock details of given page size in Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Data found in server,Enter Valid details") })
	@GetMapping("/stocks/filterBycurrentPrice/{min}/{max}")
	public ResponseEntity<Page<StockDetails>> getAllstockDetailsPagenationAndSort(@PathVariable Double max,
			@PathVariable Double min,
			@RequestParam(defaultValue = "0") int offset,
			@RequestParam( defaultValue = "10")int pageSize,
			@RequestParam(defaultValue = "currentPrice") String feildName,
			@RequestParam(defaultValue = SORTING_TYPE_DEFAULT) String sortOrder ) {

		logger.info("Filter API call to get all stock for min value {} and max {} Sorted by coloumn {}", min,
				max, feildName);
		return new ResponseEntity<Page<StockDetails>>(
				stockService.getAllStocksByFilter(pageSize, offset,min,max, feildName,sortOrder), HttpStatus.OK);

	}
	
	
	

	@Operation(summary = "To add new companyName Stock details")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Added Stock details Sucessfully"),
			@ApiResponse(responseCode = "500", description = "Enter valid data with all field values") })
	@PostMapping("/stock")
	public ResponseEntity<StockDetailsDTO> savestockDetails(@RequestBody StockDetails stockDetails) {
		logger.debug("Called Post API to add Stock details for payload {}", stockDetails);
		logger.info(" Post API call to add Stock details for company {}", stockDetails.getStockName());
		return new ResponseEntity<StockDetailsDTO>(stockService.saveStockDetails(stockDetails), HttpStatus.CREATED);

	}

	@Operation(summary = "To update companyName Stock details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "updated Stock details Sucessfully"),
			@ApiResponse(responseCode = "204", description = "Stock details of entered Company name doesn't Exists") })
	@PutMapping("/stock/{stockName}")
	public ResponseEntity<StockDetailsDTO> updatestockDetails(@RequestBody StockDetailsDTO updateDetails,
			@PathVariable String stockName) {
		logger.debug("API call to update Stock details of payload {}", updateDetails);
		logger.info(" API call to update Stock details of company {}", stockName);
		return new ResponseEntity<StockDetailsDTO>(stockService.updateStockDetails(updateDetails, stockName),
				HttpStatus.OK);

	}

	@Operation(summary = "To delete companyName Stock details")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Deleted Stock details Sucessfully") })
	@DeleteMapping("/stock/{stockName}")
	public ResponseEntity<String> deletestockDetails(@PathVariable String stockName) {
		stockService.deleteStockDetails(stockName);
		logger.info(" API call to delete Stock details of company {}", stockName);
		return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.NO_CONTENT);

	}

}
