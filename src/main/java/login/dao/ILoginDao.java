package login.dao;

import login.vo.UserVO;


public interface ILoginDao {
	
	/**
	* Method : selectUserByIdAndPassword
	* 작성자 : pc20
	* 변경이력 :
	* @param userId 유저 아이디
	* @param password 유저 비밀번호
	* @return 유저객체 반환
	* Method 설명 : 아이디 체크
	*/
	public UserVO selectUserByIdAndPassword(UserVO userVo);
	
	
}
