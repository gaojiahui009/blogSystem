package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Admin;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {

	@Autowired
	private HttpSession session;
	@Autowired
	private BlogService blogService;
	
	//blog一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		//セッションからログインしている人の情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし、admin==null ログイン画面ににリダイレクトする
		//そうでない場合
		//ログインしている人の名前の情報を画面に渡してblog一覧のhtmlを表示
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			//ブログの情報を取得
			List<Blog> blogList = blogService.selectAllBlogList(admin.getAdminId());
			model.addAttribute("adminName", admin.getAdminName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
}
