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
		return "home";
	}

	@GetMapping("/home")
	public String showHome() {
		return "home";
	}

	@GetMapping("/hello")
	public String showHello() {
		return "hello";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegister() {
		return "Register";
	}

	@GetMapping("/admin")
	public String showAdmin() {
		return "admin";
	}

	@GetMapping("/customer")
	public String showCustomer() {
		return "customer";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, Model model) {
		System.out.println("==========================" + user + "=================");
		User saveUser = userService.saveUser(user);
		model.addAttribute("message", "User save with id : " + saveUser.getUserId());
		return "Register";
	}

}
