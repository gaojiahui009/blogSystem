package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Admin;
import blog.com.services.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

	@Autowired
	private AdminService adminService;
	
	//Session が使えるように宣言
	@Autowired
	private HttpSession session;
	
	//ログイン画面の表示
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "login.html";
	}
	
	//ログイン処理
	@PostMapping("/admin/login/process")
	public String adminLoginProcess(@RequestParam String adminEmail,
									@RequestParam String password) {
		//logincheckメソッドを呼び出してその結果をadminとlう変数に格納
		Admin admin = adminService.loginCheck(adminEmail, password);
		//もし、admin==nu11ログイン画面ににとどまります。
		if(admin == null) {
			return "login.html";
		//そうでない場合は、sessionにログイン情報に保存
		} else {
			session.setAttribute("loginAdminInfo", admin);
		//blog一覧画面|にリダイレクトする
			return "redirect:/blog/list";
		}
	}
}
