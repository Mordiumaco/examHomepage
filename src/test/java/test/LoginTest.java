package test;

import static org.junit.Assert.*;
import login.service.ILoginService;
import login.service.LoginService;
import login.vo.UserVO;

import org.junit.Before;
import org.junit.Test;

import util.encrypt.KISA_SHA256;

public class LoginTest {

	private ILoginService loginService;
	
	@Before
	public void setup(){
		loginService = LoginService.getInstance();
	}
	
	/**
	* Method : selectUserByIdAndPasswordTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 로그인 테스트(비밀 번호와 아이디 제공) 
	*/
	@Test
	public void selectUserByIdAndPasswordTest() {
		
		//service.selectUserByIdAndPassword(, password)
		
		String encrypt_number =  KISA_SHA256.encrypt("choi");
		System.out.println(encrypt_number);
		UserVO userVo = loginService.selectUserByIdAndPassword("choi", encrypt_number);
		
		assertEquals(encrypt_number, userVo.getPassword());
	}
	
	
}
