package login.service;

import util.encrypt.KISA_SHA256;
import login.dao.ILoginDao;
import login.dao.LoginDao;
import login.vo.UserVO;

public class LoginService implements ILoginService{
	private ILoginDao dao = null;
	private static ILoginService service = null;
	
	private LoginService() {
		dao = LoginDao.getInstance();
	}
	
	public static ILoginService getInstance() {
		if(service == null) {
			service = new LoginService();
		}
		
		return service;
	}
	
	@Override
	public UserVO selectUserByIdAndPassword(String userId, String password) {
		UserVO userVo = new UserVO();
		userVo.setUserId(userId);
		
		//암호 적용
		password =  KISA_SHA256.encrypt(password);
		userVo.setPassword(password);
		
		return dao.selectUserByIdAndPassword(userVo);
	}
	
	
	
}
