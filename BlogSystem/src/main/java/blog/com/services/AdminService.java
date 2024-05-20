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
	//もし、findByAdminEmail==nullだったら登録処理をします
	//saveメソッドを使用して登録処理をする
	//保存ができた5true
	//そうでない場合、保存処理失敗 false
	
	public boolean createAdmin(String adminEmail, String adminName, String password) {
		if(adminDao.findByAdminEmail(adminEmail) == null) {
			adminDao.save(new Admin(adminEmail, adminName, password));
			return true;
		} else {
			return false;
		}
	}

	//ログイン処理
	//もU、emailとpasswordがfindByAdminEmailAndpasswordを使用して存在しなか)左場合==nu11の場合.
	//その場合は、存在しないnul1であることをコントロ-ラ-クラスに知らせる
	//そうでない場合ログネンしている人の情報をコントロ-ラ-クラスに渡す
	
	public Admin loginCheck(String adminEmail, String password) {
		Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail, password);
		if(admin == null) {
			return null;
		} else {
			return admin;
		}
	}
}
