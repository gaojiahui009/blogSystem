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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Admin;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	//ブログ画面の表示
	@GetMapping("/blog/register")
	public String getBlogRegister(Model model) {
		//セッションからログインしている人の情報をadminとlう変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし admin==nul1 ログイン画面にリダイレクトする
		//そうでない場合は、ログインしている人の名前を画面に渡す
		//登録のhtm1を表示させる
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("adminName", admin.getAdminName());
			return "blog_register.html";
		}
	}
	
	//ブログ画面の登録処理
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String blogTitle,
										@RequestParam String blogDate,
										@RequestParam MultipartFile blogImg,
										@RequestParam String blogContent) {
		//セッションからログインしている人の情報をadminとlう変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし、admin==nul1だったら、ログイン画面にリダイレクトする
		//そうでない場合は、画像のフアイル名を取得
		//画像のアップロ-ド
		//もし、同じフアイルの名前がなかったら保存
		//-覧画面ににリダイレクトする
		//そうでない場合、登録画面にとどまります。
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			//ファイルの名前を取得
			/*
			 * 現在の日時情報を元に、フアイル名を作成しています。simpleDateFormatクラスを使用して、日時のフオ-マットを指定している。
			 *具体的にこは、"yyyy-MM-dd-HH-mm-ss-"の形式でフオ-マットされた文字列を取得している
			 *その後、blogImageオプジエクトから元のフアイル名を取得し、フオ-マットされた日時文字列と連結して、fileName変数に代入
			 */
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImg.getOriginalFilename();
			//ファイル保存
			try {
				Files.copy(blogImg.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(blogService.createBlog(blogTitle, blogDate, fileName, blogContent, admin.getAdminId())) {
				return "blog_reg_succ.html";
			} else {
				return "blog_register.html";
			}
		}
		
	}
	
}
