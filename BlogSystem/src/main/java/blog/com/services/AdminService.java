package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AdminDao;
import blog.com.models.entity.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	//保存処理
	
	public boolean createAdmin(String adminEmail, String adminName, String password) {
		//もし、findByAdminEmail==nullだったら登録処理をします
		if(adminDao.findByAdminEmail(adminEmail) == null) {
			adminDao.save(new Admin(adminEmail, adminName, password));
		//saveメソッドを使用して登録処理をする
		//保存ができたらtrue
			return true;
		//そうでない場合、保存処理失敗 false
		} else {
			return false;
		}
	}

	//ログイン処理
	public Admin loginCheck(String adminEmail, String password) {
		Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail, password);
		//もし、emailとpasswordがfindByAdminEmailAndPasswordを使用して存在しなかた場合==nu11の場合.
		//その場合は、存在しないnullであることをコントロ-ラ-クラスに知らせる
		if(admin == null) {
			return null;
		//そうでない場合ログネンしている人の情報をコントロ-ラ-クラスに渡す
		} else {
			return admin;
		}
	}
}
