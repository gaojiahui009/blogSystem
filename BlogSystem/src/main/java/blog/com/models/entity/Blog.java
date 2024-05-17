package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
	//blog_text
	private String blogText;
	
}
