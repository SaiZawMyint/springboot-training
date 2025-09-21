package com.example.demo.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.user.UserDto;
import com.example.demo.services.user.UserService;
@Controller
public class UserController {

	@Autowired
	  private  UserService userService;

	/* GET Mappings */
	@GetMapping("/user_setup")
	public String userSetupPage(Model model) {
		model.addAttribute("userDTO", new UserDto());
		return "pages/user/user_setup";
	}

	@GetMapping("/user_list")
	public String userListPage(Model model) {
		model.addAttribute("userList", this.userService.getAllUserList());
		return "pages/user/user_list";
	}
	
	//update
	@GetMapping("/user/edit/{id}")
	public String updateUser(@PathVariable("id") long id, Model model) {
	    UserDto userDTO = this.userService.getById(id);
	    model.addAttribute("userDTO", userDTO);
	    return "redirect:/user_setup";
	}
	
	//delete
   @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
	   userService.deleteUser(id);
        return "redirect:/user_list";
   }
	
	 /* Save */
    @PostMapping("/user_setup")
    public String saveUserInfo(@ModelAttribute("userDTO") UserDto userDTO, Model model, RedirectAttributes attr) {
    	UserDto saved= this.userService.saveUser(userDTO); //(no need to add model)
        return "pages/user/user_list";
    }
    
    
}
