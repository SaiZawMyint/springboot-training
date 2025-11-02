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
		BaseResponse<List<ItemDTO>> response = new BaseResponse<>();

		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(itemService.getAllItemList());

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
			// response.setMessage("Create product success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			// response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	//update
    @PutMapping("/item/{id}")
    public ResponseEntity<BaseResponse<?>> updateItem(@PathVariable Long id, @RequestBody ItemCreateRequest request) {
    	BaseResponse<ItemResponse> response = new BaseResponse<ItemResponse>();
    	
    	try {
    		ItemDTO updttem = itemService.getById(id); //get data fromm req go service, entity update, convert dto and , convert to response

			response.setData(new ItemResponse().copyFormDTO(updttem));
			response.setStatusCode(1);
			response.setSuccess(true);
			//response.setMessage("Update Item success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			//response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
    	return ResponseEntity.ok(response);
    }
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<?>> deleteProduct(@PathVariable("id") Long pId) {
		BaseResponse<Long> response = new BaseResponse<Long>();
		try {
			this.itemService.deleteItem(pId);
			response.setStatusCode(1);
			response.setSuccess(true);
			// response.setMessage("Delete product success!");
			response.setData(pId);
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			// response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
