package com.example.demo.api.controllers.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.response.BaseResponse;
import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.services.product.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<?>> getProductList(){
		BaseResponse<List<ProductDTO>> response = new BaseResponse<List<ProductDTO>>();
		
		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(productService.getAllProductList());
			
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			response.setSuccess(false);
			response.setStatusCode(-1);
			return ResponseEntity.internalServerError().body(response);
		}
		
	}

}
