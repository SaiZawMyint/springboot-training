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

import com.example.demo.dtos.product.ProductCategoryDTO;
import com.example.demo.services.product.ProductCategoryService;

import jakarta.validation.Valid;

@Controller
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService pdtCategoryService;

	/* GET Mappings */
	@GetMapping("/productcategory-setup")
	public String pdtCategory(Model model) {
		model.addAttribute("pdtCategoryDTO", new ProductCategoryDTO());
		return "pages/products/productcategory-setup";
	}

	@GetMapping("/productcategory-list")
	public String pdtCategoryListPage(Model model) {
		model.addAttribute("pdtCategoryList", this.pdtCategoryService.getAllProductCategoryList());
		return "pages/products/productcategory-list";
	}

	//update
	@GetMapping("/pdtCategory/edit/{id}")
    public String updatepdtCategoryForm(@PathVariable("id") long id, Model model) {
        ProductCategoryDTO pdtCategoryDTO = pdtCategoryService.getAllProductCategoryList().stream()
				.filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new RuntimeException("Category not found: " + id));
        model.addAttribute("pdtCategoryDTO", pdtCategoryDTO); // ✅ fixed
        return "pages/products/productcategory-setup";
    }

	//delete
   @GetMapping("/pdtCategory/delete/{id}")
    public String deletePdtCategory(@PathVariable(value = "id") Long id) {
	   pdtCategoryService.deleteProductCategory(id);
        return "redirect:/productcategory-list";
   }

   @PostMapping("productcategory-setup")
	public String pdtCategorySetupPost(@ModelAttribute("pdtCategoryDTO") @Valid ProductCategoryDTO pdtCategoryDTO, BindingResult result, Model model, RedirectAttributes attr) {
	   try {

		   validateRequest(pdtCategoryDTO, result);

		   if (result.hasErrors()) {
				model.addAttribute("errorMsg", "Please fill all required fields!");
				return "pages/products/productcategory-setup";
			}

		   ProductCategoryDTO saved = this.pdtCategoryService.saveProductCategory(pdtCategoryDTO);
			attr.addFlashAttribute("successMsg", "Category saved successfully!");
			return "redirect:/productcategory-list";
	   }
	   catch(Exception e){
		   model.addAttribute("errorMsg", e.getMessage());
			return "pages/products/productcategory-setup";
	   }

	}

   private void validateRequest(@Valid ProductCategoryDTO pdtCategoryDTO, BindingResult result) {
		if(pdtCategoryDTO.getName() != null) {
			if(this.pdtCategoryService.isNameAlreadyExit(pdtCategoryDTO.getName(), pdtCategoryDTO.getId())) {
				result.rejectValue("name", "pdtCategoryDTO.name", "Name already used!");
			}
		}
	}
}
