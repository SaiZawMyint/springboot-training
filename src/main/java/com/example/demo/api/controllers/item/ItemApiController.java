package com.example.demo.api.controllers.item;

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

import com.example.demo.api.requests.Item.ItemCreateRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.Item.ItemResponse;
import com.example.demo.dtos.item.ItemDTO;
import com.example.demo.services.item.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemApiController {
	@Autowired
	private ItemService itemService;

	@GetMapping("/item-list")
	public ResponseEntity<BaseResponse<?>> getItemList() {
		BaseResponse<List<ItemResponse>> response = new BaseResponse<>();

		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(itemService.getAllItemList().stream().map(t -> new ItemResponse().copyFormDTO(t))
					.toList());

		
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setStatusCode(-1);
			return ResponseEntity.internalServerError().body(response);
		}

	}

	@PostMapping("/item-create")
	public ResponseEntity<BaseResponse<?>> createItem(@RequestBody ItemCreateRequest request) {
		BaseResponse<ItemResponse> response = new BaseResponse<ItemResponse>();
		try {
			ItemDTO saved = itemService.saveItem(request.convertToDTO());

			response.setData(new ItemResponse().copyFormDTO(saved));
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
	
	@PutMapping("/item/{id}")
	public ResponseEntity<BaseResponse<?>> updateItem(
	        @PathVariable("id") Long id,
	        @RequestBody ItemCreateRequest request) {

	    BaseResponse<ItemResponse> response = new BaseResponse<>();

	    try {
	        // 1. Fetch existing item by ID
	        ItemDTO existingItem = itemService.getById(id);
	        if (existingItem == null) {
	            response.setStatusCode(-1);
	            response.setSuccess(false);
	            response.setMessage("Item not found with ID: " + id);
	    		return ResponseEntity.internalServerError().body(response);
	        }

	        // 2. Update entity fields from request
	        existingItem.setName(request.getName());
	        existingItem.setSellPrice(request.getSellPrice());
	        existingItem.setOriginalPrice(request.getOriginalPrice());
	        existingItem.setQuantity(request.getQuantity());
	        existingItem.setStatus(request.getStatus());
	        
	        existingItem.setStatusDesc(request.getStatusDesc());

	        // 3. Save updated item via service
	        ItemDTO updatedItem = itemService.saveItem(existingItem);

	        // 4. Convert to response DTO
	        response.setData(new ItemResponse().copyFormDTO(updatedItem));
	        response.setStatusCode(1);
	        response.setSuccess(true);
	        response.setMessage("Item updated successfully!");

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.setStatusCode(-1);
	        response.setSuccess(false);
	        response.setMessage("Error updating item: " + e.getMessage());
	        return ResponseEntity.internalServerError().body(response);
	    }
	}

	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<?>> deleteProduct(@PathVariable("id") Long pId) {
		BaseResponse<Long> response = new BaseResponse<Long>();
		try {
			this.itemService.deleteItem(pId);
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
