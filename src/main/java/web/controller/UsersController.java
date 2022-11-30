package web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import web.models.User;
import web.service.UserService;


@Controller
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String getAllUsers(Model model) {
		model.addAttribute("all", userService.getAllUsers());
		return "users/users";
	}

	@GetMapping("/users/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", userService.show(id));
		return "users/show";
	}

	@GetMapping("users/new")
	public String newUser(@ModelAttribute("user") User user) {

		return "users/new";
	}

	@PostMapping("/new")
	public String create(@ModelAttribute("user") @Valid User user,
						 BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "users/new";

		userService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/edit")
	public String edit(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("user", userService.show(id));
		return "users/edit";
	}

	@PatchMapping("/users/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") Integer id) {
		if (bindingResult.hasErrors())
			return "users/edit";

		userService.update(id, user);
		return "redirect:/users";
	}

	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return "redirect:/users";
	}
}