package hospital.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.entity.User;
import hospital.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
//		user.setId(0);
//		user.getUserDetail().setUser(user);
//		userService.saveUser(user);
		return user;
	}
}
