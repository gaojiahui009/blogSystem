package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {

	//保存処理と更新処理 insertとupdate
	Blog save(Blog blog);
	
	//SELECT * FROM blog
	//用途:blog listを表示させるときに使用
	List<Blog>findAll();
	//SELECT * FROM blog WHERE blog_title = ?
	//用途:blogの登録チエック使用(同じblog titleが登録されないようにこするチエックに使用)
	Blog findByBlogTitle(String blogTitle);
	
	//SELECT * FROM blog WHERE blog_id = ?
	//用途:編集画面を表示する際に使用。
	Blog findByBlogId(Long blogId);
	
	//DELETE FROM blog WHERE blog_id = ?
	//用途：消除使用
	void deleteByBlogId(Long blogId);
}
