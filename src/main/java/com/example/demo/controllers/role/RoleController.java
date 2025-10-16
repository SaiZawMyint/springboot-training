package com.example.demo.controllers.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.role.RoleDto;
import com.example.demo.services.role.RoleService;

import jakarta.validation.Valid;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	/* GET Mappings */
	@GetMapping("/role_setup")
	public String roleSetupPage(Model model) {
		model.addAttribute("roleDTO", new RoleDto());
		return "pages/role/role_setup";
	}

	//get
	@GetMapping("/role_list")
	public String roleListPage(Model model) {
		model.addAttribute("roleList", this.roleService.getAllRoleList());
		return "pages/role/role_list";
	}

	//update
	@GetMapping("/role/edit/{id}")
	public String updateRole(@PathVariable("id") long id, Model model) {
		RoleDto roleDTO = this.roleService.getById(id);
	    model.addAttribute("roleDTO", roleDTO);
	    return "pages/role/role_setup";
	}

	//delete
   @GetMapping("/role/delete/{id}")
    public String deleteRole(@PathVariable(value = "id") Long id) {
	   roleService.deleteRole(id);
        return "redirect:/role_list";
   }

   /* Save */
	@PostMapping("/role_setup")
	public String saveRoleInfo(@ModelAttribute ("roleDTO")  @Valid RoleDto roleDTO, BindingResult result, Model model, RedirectAttributes attr) {
		try {
			/*
			 * if(result.hasErrors()) { model.addAttribute("errorMsg",
			 * "Please fill all required fields!"); return "pages/role/role_setup"; }
			 */

			RoleDto saved = this.roleService.saveRole(roleDTO); // (no need to add model)
			 attr.addFlashAttribute("successMsg", "Product saved successfully!");
			return "redirect:/role_list";
		}catch(Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			 return "pages/role/role_setup";
		}


	}

}
