package com.example.demo.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.services.product.ProductCategoryService;
import com.example.demo.services.product.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService categoryService;

	/* GET Mappings */
	@GetMapping("/product-setup")
	public String productSetupPage(Model model) {

		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categoryList", this.categoryService.getAllProductCategoryList());
		return "pages/products/product-setup";
	}

	@GetMapping("/product-list")
	public String productListPage(Model model) {
		model.addAttribute("productList", this.productService.getAllProductList());
		return "pages/products/product-list";
	}

	// update
	@GetMapping("/edit/{id}")
	public String updatePtoductForm(@PathVariable("id") long id, Model model) {
		ProductDTO productDTO = productService.getById(id);
		model.addAttribute("categoryList", this.categoryService.getAllProductCategoryList());
		model.addAttribute("productDTO", productDTO); // ✅ fixed
		return "pages/products/product-setup";
	}

	// delete
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/product-list";
	}

	@PostMapping("/product-setup")
	public String productSetupPost(
	        @Valid @ModelAttribute("productDTO") ProductDTO productDTO,
	        BindingResult result,
	        Model model,
	        RedirectAttributes attr) {

	    // 1. Custom validation
	    validateRequest(productDTO, result);

	    // 2. Check validation errors
	    if (result.hasErrors()) {
	        model.addAttribute("categoryList", categoryService.getAllProductCategoryList());
	        model.addAttribute("errorMsg", "Please fill all required fields!");
	        return "pages/products/product-setup";
	    }

	    try {
	        // 3. Ensure categoryId is provided
	        if (productDTO.getProductCategoryId() == null) {
	            model.addAttribute("categoryList", categoryService.getAllProductCategoryList());
	            model.addAttribute("errorMsg", "Please select a product category!"); //to ask
	            return "pages/products/product-setup";
	        }

	        // 4. Save product
	        productService.saveProduct(productDTO);
	        attr.addFlashAttribute("successMsg", "Product saved successfully!");
	        return "redirect:/product-list";

	    } catch (Exception e) {
	        model.addAttribute("categoryList", categoryService.getAllProductCategoryList());
	        model.addAttribute("errorMsg", "Error saving product: " + e.getMessage());
	        return "pages/products/product-setup";
	    }
	}


	 private void validateRequest(@Valid ProductDTO productDTO, BindingResult result) {
			if(productDTO.getName() != null) {
				if(this.productService.isNameAlreadyExit(productDTO.getName(), productDTO.getId())) {
					result.rejectValue("name", "productDTO.name", "Name already used!");
				}
			}
		}
}
