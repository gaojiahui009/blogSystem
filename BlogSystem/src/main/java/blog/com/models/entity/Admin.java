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
public class Admin {

	//admin_id の設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long adminId;
	//admine_email
	private String adminEmail;
	//admin_name
	private String adminName;
	//password
	private String password;
	
	
}
