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
	
	//編集画面を表示するときのチェック
	//もし、blogId == null null
	//そうでない場合、
	//findByBlogIdの情報をコントロ-ラ-クラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	//更新処理のcheck
	//もし、blogId==nullだったら、更新処理はしない
	//false
	//そうでない場合、
	//更新処理をする
	//コントロ-ラ-クラスからもらった、productIdを使って、編集する前の、デ一タを取得
	//変更するべきとこるだけ、セッタ一を使用してデ-タの更新をする。
	//trueを返す
	public boolean blogUpdate(Long blogId, 
								String blogTitle,
								String blogDate,
								String blogImg,
								String blogContent, 
								Long adminId) {
		if(blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDate(blogDate);
			blog.setBlogImg(blogImg);
			blog.setBlogContent(blogContent);
			blog.setAdminId(adminId);
			blogDao.save(blog);
			return true;
		}
	}
	
	//削除の処理check
	//もし、コントロ-ラ-からもらったblogIdがnullであればば
	//削除できないこと false
	//そうでない場合
	//deleteByBlogIdを使用してblogの削除
	//true
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
}
