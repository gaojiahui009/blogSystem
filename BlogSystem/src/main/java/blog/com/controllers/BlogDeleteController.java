package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Admin;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		//セッションからログインしている人の情報をadminとlう変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし admin==nul1 ログイン画面にリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			//もし、deleteBlogの結果がtrueだったら
			// blogの-覧ペ-ジこリダイレクト
			//そうでない場合
			//編集画面ににこリダイレクトする
			if(blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}
	}
}
