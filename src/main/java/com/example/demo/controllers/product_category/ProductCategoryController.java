package com.example.demo.controllers.product_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.product_category.ProductCategoryDTO;
import com.example.demo.services.product_category.ProductCategoryService;

@Controller
@RequestMapping("/product-categories")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping
	public String listProductCategories(Model model) {
		List<ProductCategoryDTO> categories = productCategoryService.getAllProductCategories();
		model.addAttribute("categories", categories);
		return "pages/product_category/list";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("productCategory", new ProductCategoryDTO());
		return "pages/product_category/create";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		ProductCategoryDTO dto = productCategoryService.getAllProductCategories().stream()
				.filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new RuntimeException("Category not found: " + id));
		model.addAttribute("productCategory", dto);
		return "pages/product_category/edit";
	}

	@GetMapping("/delete/{id}")
	public String deleteProductCategory(@PathVariable("id") Long id) {
		productCategoryService.deleteProductCategory(id);
		return "redirect:/product-categories";
	}

	@PostMapping("/create")
	public String createProductCategory(@ModelAttribute("productCategory") ProductCategoryDTO dto) {
		productCategoryService.saveProductCategory(dto);
		return "redirect:/product-categories";
	}

	@PostMapping("/edit/{id}")
	public String updateProductCategory(@PathVariable("id") Long id,
			@ModelAttribute("productCategory") ProductCategoryDTO dto) {
		dto.setId(id);
		productCategoryService.updateProductCategory(dto);
		return "redirect:/product-categories";
	}

}
