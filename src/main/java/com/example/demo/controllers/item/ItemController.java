package com.example.demo.controllers.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.item.ItemDTO;
import com.example.demo.services.item.ItemService;
import com.example.demo.services.product.ProductService;

import jakarta.validation.Valid;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ProductService productService;

	/* GET Mappings */
	@GetMapping("/item-setup")
	public String itemSetupPage(Model model) {
		model.addAttribute("itemDTO", new ItemDTO());
		model.addAttribute("productList", this.productService.getAllProductList());
		return "pages/item/item-setup";
	}

	@GetMapping("/item-list")
	public String itemListPage(Model model) {
		model.addAttribute("itemList", this.itemService.getAllItemList());
		return "pages/item/item-list";
	}

	// update
	@GetMapping("/item/edit/{id}")
	public String updateItemForm(@PathVariable("id") long id, Model model) {
		ItemDTO itemDTO = itemService.getById(id);
		model.addAttribute("productList", this.productService.getAllProductList());
		model.addAttribute("itemDTO", itemDTO); // ✅ fixed
		return "pages/item/item-setup";
	}

	// delete
	@GetMapping("/item/delete/{id}")
	public String deleteItem(@PathVariable(value = "id") Long id) {
		itemService.deleteItem(id);
		return "redirect:/item-list";
	}

	@PostMapping("/item-setup")
	public String itemSetupPost(@ModelAttribute @Valid ItemDTO itemDTO, BindingResult result, Model model,
			RedirectAttributes attr) {
		try {

			validateRequest(itemDTO, result);
			if (result.hasErrors()) {
				model.addAttribute("errorMsg", "Please fill all required fields!");
				return "pages/item/item-setup";
			}
			itemService.saveItem(itemDTO);
			attr.addFlashAttribute("successMsg", "Item saved successfully!");
			return "redirect:/item-list";
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("productList", productService.getAllProductList());
			return "pages/item/item-setup";
		}
	}

	private void validateRequest(@Valid ItemDTO itemDTO, BindingResult result) {

		if(itemDTO.getName() != null) {
			if(this.itemService.isNameAlreadyExit(itemDTO.getName(), itemDTO.getId())) {
				result.rejectValue("name", "productDTO.name", "Name already used!");
			}
		}
	}

}
