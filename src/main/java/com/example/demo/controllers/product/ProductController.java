package com.example.demo.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.services.product.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	/* GET Mappings */
	@GetMapping("product-setup")
	public String productSetupPage(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		return "products/product-setup";
	}

	@GetMapping("product-list")
	public String productListPage(Model model) {
		model.addAttribute("productList", this.productService.getAllProductList());
		return "products/product-list";
	}

	@PostMapping("product-setup")
	public String productSetupPost(@ModelAttribute ProductDTO productDTO, Model model, RedirectAttributes attr) {

		ProductDTO saved = this.productService.saveProduct(productDTO);
		return "redirect:product-list";
	}

	//update
	@GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        ProductDTO productDTO = productService.getById(id);
        model.addAttribute("productDTO", productDTO); // ✅ fixed
        return "products/product-setup";
    }

	//delete
   @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {
	  productService.deleteProduct(id);
        return "redirect:product-list";

    }
}
