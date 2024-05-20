package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
	 //blog_id の設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	 //blog_title
	 private String blogTitle;
	 //blog_date
	 private String blogDate;
	 //blog_img
	 private String blogImg;
	 //blog_content
	 private String blogContent;
	 //addmin_id
	 private Long adminId;
	 
	public Blog() {
	}

	public Blog(String blogTitle, String blogDate, String blogImg, String blogContent, Long adminId) {
		this.blogTitle = blogTitle;
		this.blogDate = blogDate;
		this.blogImg = blogImg;
		this.blogContent = blogContent;
		this.adminId = adminId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(String blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlogImg() {
		return blogImg;
	}

	public void setBlogImg(String blogImg) {
		this.blogImg = blogImg;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}





	 


}
