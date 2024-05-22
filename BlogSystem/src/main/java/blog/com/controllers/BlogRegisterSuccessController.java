package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogRegisterSuccessController {

	@PostMapping("/blog/register/success/process")
	public String blogRegisterSuccessProcess() {
		return "redirect:/blog/list";
	}
}
