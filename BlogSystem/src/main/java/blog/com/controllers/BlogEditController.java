package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Admin;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	//编辑画面表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		//セッションからログインしている人の情報をadminとlう変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし admin==nul1 ログイン画面にリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			//編集画面に表示させる情報を変数に格納 blog
			Blog blog = blogService.blogEditCheck(blogId);
			//もしblog==nullだったら、blog一覧ペ-ジこリリダイレクトする
			//そうでない場合、編集画面に編集する内容を渡す
			//編集画面を表示
			if(blog == null) {
				return "redirect:/blog/list";
			}else {
				model.addAttribute("adminName", admin.getAdminName());
				model.addAttribute("blog", blog);
				return "blog_editor.html";
			}
		}
	}
	
	//更新処理
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, 
							@RequestParam String blogDate,
							@RequestParam MultipartFile blogImg,
							@RequestParam String blogContent,
							@RequestParam Long blogId) {
		//セッションからログインしている人の情報をadminとlう変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし、admin == nullだったら、ログイン画面ににリダイレクトする
		//そうでない場合、
		/*
		 * 現在の日時情報を元に、フアイル名を作成しています。simpleDateFormatクラスを使用して、日時のフオ-マットを指定している
		 *具体的(は、"yyyy-MM-dd-HH-mm-ss-"の形式でフオ-マットされた文字列を取得している
		 *その後、blogImageオプジエクトから元のフアイル名を取得し、フオ-マットされた日時文字列と連結して、fileName变数に代入
		 */
		
		//フアイルの保存
		//もし、blogUpdateの結果がtrueの場合は、blog一覧ににリダイレクト
		//そうでない場合、blog編集画面ににリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		}else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImg.getOriginalFilename();
			try {
				Files.copy(blogImg.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(blogService.blogUpdate(blogId, blogTitle, blogDate, fileName, blogContent, admin.getAdminId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit"+blogId;
			}
		}
	}
	
}
