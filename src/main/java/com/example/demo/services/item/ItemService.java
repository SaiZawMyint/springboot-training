package com.example.demo.services.item;

import java.util.List;

import com.example.demo.dtos.item.ItemDTO;


public interface ItemService {

	// save
	ItemDTO saveItem(ItemDTO itemDTO);

	// list
	List<ItemDTO> getAllItemList();

	// update
	ItemDTO getById(Long id);

	// delete
	void deleteItem(long id);
}
