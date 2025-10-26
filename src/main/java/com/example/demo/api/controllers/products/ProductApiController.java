package com.example.demo.api.controllers.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.requests.product.ProductCreateRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.product.ProductResponse;
import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.services.product.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public ResponseEntity<BaseResponse<?>> getProductList() {
		BaseResponse<List<ProductResponse>> response = new BaseResponse<List<ProductResponse>>();

		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(productService.getAllProductList().stream().map(t -> new ProductResponse().copyFormDTO(t))
					.toList());

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setStatusCode(-1);
			return ResponseEntity.internalServerError().body(response);
		}
	}

	
	@PostMapping("/create")
	public ResponseEntity<BaseResponse<?>> createProduct(@RequestBody ProductCreateRequest request) {
		BaseResponse<ProductResponse> response = new BaseResponse<ProductResponse>();
		try {
			ProductDTO saved = productService.saveProduct(request.convertToDTO());

			response.setData(new ProductResponse().copyFormDTO(saved));
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setMessage("Create product success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	//@PutMapping update (desire api -> /api/products/update?id={id})
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<?>> deleteProduct(@PathVariable("id") Long pId){
		BaseResponse<Long> response = new BaseResponse<Long>();
		try {
			this.productService.deleteProduct(pId);
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setMessage("Delete product success!");
			response.setData(pId);
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
}
