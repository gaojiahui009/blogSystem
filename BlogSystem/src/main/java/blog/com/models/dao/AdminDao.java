package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {

	//保存処理と更新処理 insertとupdate
	Admin save(Admin admin);
	
	//SELECT * FROM admin WHERE admin_email = ?
	//用途:管理者の登録処理をするときこ、同じメ-ルアドレスがあったらば登録させないようにする
	Admin findByAdminEmail(String adminEmail);
	
	//SELECT * FROM admin WHERE admin_email = ? AND password = ?
	//用途:ログイン処理に使用。入カしたメ-ルアドレスとパスワ-ドが-致してるときだけデ一タを取得
	Admin findByAdminEmailAndPassword(String adminEmail, String password);
	
}
