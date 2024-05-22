package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminRegisterSuccessController {

	//登録処理
	@PostMapping("/admin/register/success/process")
	public String adminRegisterSuccessProcess() {
		return "redirect:/admin/login";
	}
}
