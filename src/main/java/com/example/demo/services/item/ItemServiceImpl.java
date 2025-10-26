package com.example.demo.services.item;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.item.ItemDTO;
import com.example.demo.persistence.model.item.Item;
import com.example.demo.persistence.model.product.Product;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.item.ItemRepository;
import com.example.demo.persistence.repositories.product.ProductRepository;
import com.example.demo.persistence.repositories.user.UserRepository;
import com.example.demo.services.auth.AuthenticationService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private AuthenticationService authenticationService;

	@Override
	public ItemDTO saveItem(ItemDTO itemDto) {

	    Item item;
	    if (itemDto.getId() != null) {
			// Update
	    	item = itemRepository.findById(itemDto.getId())
					.orElseThrow(() -> new RuntimeException("Item not found"));
		} else {
			item = new Item();

		}

	    item.setName(itemDto.getName());
	    item.setSellPrice(itemDto.getSellPrice());
	    item.setOriginalPrice(itemDto.getOriginalPrice());
	    item.setQuantity(itemDto.getQuantity());
	    item.setStatus(itemDto.getStatus());
	    if (itemDto.getProduct() != null) {
	    	Product product = this.productRepository.findById(itemDto.getProduct())
	                .orElse(null);

	        if (product == null) {
	            throw new RuntimeException(
	                "Invalid product ID: " + itemDto.getProduct() + ". Please select a valid product.");
	        }

	        item.setProduct(product);

	    } else {
	        throw new RuntimeException("Product category ID is required.");
	    }

	    User currentUser = authenticationService.getCurrentUser();
	    item.setCreatedBy(currentUser);

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

		User currentUser = authenticationService.getCurrentUser();
		item.setUpdatedBy(currentUser);
	    return new ItemDTO(item);
	}

	@Override
	public void deleteItem(long id) {
		itemRepository.deleteById(id);

	}

	@Override
	public boolean isNameAlreadyExit(String name, Long ignoreId) {
		// TODO Auto-generated method stub
		return (this.itemRepository.getNameByIgnoreId(name, ignoreId) != null);
	}

}
