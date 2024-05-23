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
	public List<Blog>selectAllBlogList(Long adminId){
		//もしadminId==null 戻り値としてnu11
		if (adminId == null) {
			return null;
		//そうではない場合　findA11内容をコントロ-ラ-クラスに渡す
		} else {
			return blogDao.findAll();
		}
	}
	
	//blog登録のcheck
	public boolean createBlog(String blogTitle, 
							String blogDate, 
							String blogImg, 
							String blogContent, 
							Long adminId) {
		//もし、findByProductNameが==nullだったら、
		//保存処理
		//true
		if(blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle,blogDate,blogImg,blogContent, adminId));
			return true;
		//そうではない場合
		//false
		} else {
			return false;
		}
	}
	
	//編集画面を表示するときのチェック
	public Blog blogEditCheck(Long blogId) {
		//もし、blogId == null null
		if(blogId == null) {
			return null;
		//そうでない場合、
		//findByBlogIdの情報をコントロ-ラ-クラスに渡す
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	//更新処理のcheck
	public boolean blogUpdate(Long blogId, 
								String blogTitle,
								String blogDate,
								String blogImg,
								String blogContent, 
								Long adminId) {
		//もし、blogId==nullだったら、更新処理はしない
		//false
		if(blogId == null) {
			return false;
		//そうでない場合、
		} else {
			//更新処理をする
			//コントロ-ラ-クラスからもらった、productIdを使って、編集する前の、デ一タを取得
			Blog blog = blogDao.findByBlogId(blogId);
			//変更するべきとこるだけ、セッタ一を使用してデ-タの更新をする。
			//trueを返す
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
	public boolean deleteBlog(Long blogId) {
		//もし、コントロ-ラ-からもらったblogIdがnullであれば
		//削除できないこと false
		if(blogId == null) {
			return false;
		//そうでない場合
		//deleteByBlogIdを使用してblogの削除
		//true
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
}
