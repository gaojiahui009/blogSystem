package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	//blog一覧のcheck
	//もUadminId==nul1 戻り値としてnu11
	//findA11内容をコントロ-ラ-クラスに渡す
	public List<Blog>selectAllBlogList(Long adminId){
		if (adminId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}
	
	//blog登録のcheck
	//も乚、findByProductNameが==nullだったら、
	//保存処理
	//true
	//そうでな場合
	//false
	public boolean createBlog(String blogTitle, 
							String blogDate, 
							String blogImg, 
							String blogContent, 
							Long adminId) {
		if(blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle,blogDate,blogImg,blogContent, adminId));
			return true;
		} else {
			return false;
		}
	}
	
}
