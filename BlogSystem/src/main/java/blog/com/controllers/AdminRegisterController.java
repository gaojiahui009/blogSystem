package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AdminService;

@Controller
public class AdminRegisterController {

	@Autowired
	private AdminService adminService;
	
	//登録画面の表示
	@GetMapping("/admin/register")
	public String getAdminRegister() {
		return "register.html";
	}
	
	//登録処理
	@PostMapping("/admin/register/process")
	public String adminRegisterProcess(@RequestParam String adminEmail, 
										@RequestParam String adminName,
										@RequestParam String password) {
		//もし、createAdminがtrue login.htmll
		if(adminService.createAdmin(adminEmail, adminName, password)) {
			return "register_success.html";
		//そうでない場合、register.htmllことどまります。
		} else {
			return "register.html";
		}
	}
}
