package com.example.demo.services.item;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.item.ItemDTO;
import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.persistence.model.item.Item;
import com.example.demo.persistence.model.product.Product;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.item.ItemRepository;
import com.example.demo.persistence.repositories.product.ProductRepository;
import com.example.demo.persistence.repositories.user.UserRepository;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ItemDTO saveItem(ItemDTO itemDto) {
		Product product = productRepository.findById(itemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
		
		User user = userRepository.findById(itemDto.getCreatedBy())
		            .orElseThrow(() -> new RuntimeException("User not found"));
		
		Item item = new Item();
		item.setName(itemDto.getName());
		item.setSellPrice(itemDto.getSellPrice());
		item.setOriginalPrice(itemDto.getOriginalPrice());
		item.setQuantity(itemDto.getQuantity());
		item.setStatus(itemDto.getStatus());
		item.setProduct(product);
		item.setCreatedBy(user);
		item.setCreatedAt(new Date()); // set current timestamp
		
		Item saved = this.itemRepository.save(item);
	
		return new ItemDTO(saved);
	}

	@Override
	public List<ItemDTO> getAllItemList() {
		List<Item> dataList = this.itemRepository.findAll();

		if(dataList != null && !dataList.isEmpty()) {
			return dataList.stream().map(ItemDTO::new).toList();
		}

		return Collections.emptyList();
	}

	@Override
	public ItemDTO getById(Long id) {
		Item item = itemRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Item not found for id : " + id));

	    return new ItemDTO(item);
	}

	@Override
	public void deleteItem(long id) {
		itemRepository.deleteById(id);
		
	}

}
