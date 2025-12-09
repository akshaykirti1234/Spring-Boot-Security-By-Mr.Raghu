package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tech.csm.model.User;
import tech.csm.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getMethodName() {
		return "Register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, Model model) {
		User saveUser = userService.saveUser(user);
		model.addAttribute("message", "User save with id : " + saveUser.getUserId());
		return "Register";
	}

}
