package co.com.viveres.susy.microservicesale.client;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicesale.dto.RequestProductClientStockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-product", path = "/v1/products")
public interface IProductRestClient {

	@GetMapping(path = "/{product-id}")
	public ResponseEntity<ProductDto> getById(@PathVariable("product-id") Long id);

	@PutMapping(path = "/{product-id}/stock")
	public ResponseEntity<Void> stockManagementByProduct(
			@PathVariable("product-id") Long productId,
			@RequestBody RequestProductClientStockDto movement);

}
