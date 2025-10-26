package com.example.demo.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.user.UserDto;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.user.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/* GET Mappings */
	@GetMapping("/user_setup")
	public String userSetupPage(Model model) {
		model.addAttribute("userDTO", new UserDto());
		//model.addAttribute("roleList", this.roleService.getAllRoleList());
		return "pages/user/user_setup";
	}

	// SHOW USER LIST
	@GetMapping("/user_list")
	public String userListPage(Model model) {
		model.addAttribute("userList", this.userService.getAllUserList());

		return "pages/user/user_list";
	}

	// update
	@GetMapping("/user/edit/{id}")
	public String updateUser(@PathVariable("id") long id, Model model) {
		UserDto userDTO = this.userService.getById(id);
		model.addAttribute("userDTO", userDTO);
		return "pages/user/user_setup";
	}

	// delete
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id) {
		userService.deleteUser(id);
		return "redirect:/user_list";
	}

	@PostMapping("user_setup")
	public String saveUserInfo(@ModelAttribute("userDTO") @Valid UserDto userDTO, BindingResult result, Model model,
			RedirectAttributes attr) {

		// ✅ 1. Custom validation before checking errors
		validateRequest(userDTO, result);

		if (result.hasErrors()) {
			model.addAttribute("errorMsg", "Please fill all required fields!");
			return "pages/user/user_setup";
		}

		try {
			// ✅ 2. Save the user
			UserDto userDto = userService.saveUser(userDTO);

			// ✅ 3. Add success message and redirect
			attr.addFlashAttribute("successMsg", "User saved successfully!");
			return "redirect:/user_list";

		} catch (Exception e) {
			// ✅ 4. Catch and display actual error
			model.addAttribute("errorMsg", "Error saving user: " + e.getMessage());
			return "pages/user/user_setup";
		}
	}

	private void validateRequest(@Valid UserDto userDTO, BindingResult result) {
		if (userDTO.getUsername() != null) {
			if (this.userService.isNameAlreadyExit(userDTO.getUsername(), userDTO.getId())) {
				result.rejectValue("username", "userDTO.username", "Name already used!");
			}
		}
	}
}