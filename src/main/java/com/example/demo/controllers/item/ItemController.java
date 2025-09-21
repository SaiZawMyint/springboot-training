package com.example.demo.controllers.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.item.ItemDTO;
import com.example.demo.services.item.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/* GET Mappings */
	@GetMapping("/item-setup")
	public String itemSetupPage(Model model) {
		model.addAttribute("itemDTO", new ItemDTO());
		return "pages/item/item-setup";
	}

	@GetMapping("/item-list")
	public String itemListPage(Model model) {
		model.addAttribute("itemList", this.itemService.getAllItemList());
		return "pages/item/item-list";
	}

	//update
	@GetMapping("/item/edit/{id}")
    public String updateItemForm(@PathVariable("id") long id, Model model) {
		ItemDTO itemDTO = itemService.getById(id);
        model.addAttribute("itemDTO", itemDTO); // ✅ fixed
        return "pages/item/item-setup";
    }

	//delete
   @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable(value = "id") Long id) {
	   itemService.deleteItem(id);
        return "redirect:/item-list";
   }

	@PostMapping("/item-setup")
	public String itemSetupPost(@ModelAttribute ItemDTO itemDTO, Model model, RedirectAttributes attr) {
		ItemDTO saved = this.itemService.saveItem(itemDTO);
		 return "redirect:/item-list";
	}
}
