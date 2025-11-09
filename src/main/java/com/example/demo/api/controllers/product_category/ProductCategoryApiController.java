package com.example.demo.api.controllers.product_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.requests.ProductCategory.ProductCategoryCreateRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.ProductCategory.ProductCategoryResponse;
import com.example.demo.dtos.product.ProductCategoryDTO;
import com.example.demo.services.product.ProductCategoryService;

@RestController
@RequestMapping("/api/productcategory")
public class ProductCategoryApiController {
	
	@Autowired
	private ProductCategoryService pdtCategoryService;
	
	@GetMapping("/pdtCategorylist")
	public ResponseEntity<BaseResponse<?>> getpdtCategoryList() {
		BaseResponse<List<ProductCategoryResponse>> response = new BaseResponse<List<ProductCategoryResponse>>();

		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(pdtCategoryService.getAllProductCategoryList().stream().map(t -> new ProductCategoryResponse().copyFormDTO(t))
					.toList());

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setStatusCode(-1);
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@PostMapping("/createpdtCategory")
	public ResponseEntity<BaseResponse<?>> createProduct(@RequestBody ProductCategoryCreateRequest request) {
		BaseResponse<ProductCategoryResponse> response = new BaseResponse<ProductCategoryResponse>();
		try {
			ProductCategoryDTO saved = pdtCategoryService.saveProductCategory(request.convertToDTO());

			response.setData(new ProductCategoryResponse().copyFormDTO(saved));
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setMessage("Create product category success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}

		return ResponseEntity.ok(response);
	}   
    
	//update
	@PutMapping("/pdtcategory/{id}")
    public ResponseEntity<BaseResponse<?>> updatepdtCategory(@PathVariable Long id, @RequestBody ProductCategoryCreateRequest request) {
    	BaseResponse<ProductCategoryResponse> response = new BaseResponse<ProductCategoryResponse>();
    	
    	try {
    		ProductCategoryDTO updtpdtCategory = pdtCategoryService.getById(id);
	        // 1. Fetch existing product
	    
	        if (updtpdtCategory == null) {
	            response.setStatusCode(-1);
	            response.setSuccess(false);
	            response.setMessage("Product Category not found with ID: " + id);
	            return ResponseEntity.internalServerError().body(response);
	        }

	        // 2. Update fields using the request
	        updtpdtCategory.setCode(request.getCode());
	        updtpdtCategory.setName(request.getName());
	        updtpdtCategory.setImageUrl(request.getImageUrl());
	        
	        // ... update other fields as needed

	        // 3. Save updated product
	        ProductCategoryDTO updatedPdtCategory = pdtCategoryService.saveProductCategory(updtpdtCategory);

	        // 4. Set response
	        response.setData(new ProductCategoryResponse().copyFormDTO(updatedPdtCategory));
	        response.setStatusCode(1);
	        response.setSuccess(true);
	        response.setMessage("Product updated successfully!");

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.setStatusCode(-1);
	        response.setSuccess(false);
	        response.setMessage("Error updating product: " + e.getMessage());
	        return ResponseEntity.internalServerError().body(response);
	    }
	}

    
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<?>> deletepdtCategory(@PathVariable("id") Long pId){
		BaseResponse<Long> response = new BaseResponse<Long>();
		try {
			this.pdtCategoryService.deleteProductCategory(pId);
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setMessage("Delete product category success!");
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
