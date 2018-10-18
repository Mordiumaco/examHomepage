package login.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.other.SQLFactoryBuilder;
import login.vo.UserVO;

public class LoginDao implements ILoginDao{
	
	private static ILoginDao dao = null;
	
	private LoginDao() {
	}
	
	public static ILoginDao getInstance() {
		if(dao == null) {
			dao = new LoginDao();
		}
		return dao;
	}
	
	
	
	/**
	* Method : selectUserByIdAndPassword
	* 작성자 : pc20
	* 변경이력 :
	* @param userId 유저 아이디
	* @param password 유저 비밀번호
	* @return 유저객체 반환
	* Method 설명 : 아이디 체크
	*/
	@Override
	public UserVO selectUserByIdAndPassword(UserVO userVo) {
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		UserVO user = session.selectOne("userSQL.selectUserByIdAndPassword", userVo);
		
		if(user != null){
			return user;
		}
		
		return null;
	}
	
}
