package file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.other.SQLFactoryBuilder;
import file.vo.FileVO;


public class FileDao implements IFileDao{
	private static IFileDao dao = null;
	
	private FileDao() {
	}
	
	public static IFileDao getInstance() {
		if(dao == null) {
			dao = new FileDao();
		}
		return dao;
	}
	
	
	/**
	* Method : nowFileCodeNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일 최신 코드를 가져온다.
	*/
	@Override
  	public Integer nowFileCodeNumber(){
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try {
			Integer nowFileCode = session.selectOne("fileSQL.nowFileCodeNumber");
			
			return nowFileCode;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
  	}
  	
  	/**
	* Method : insertFile
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일을 생성한다. 
	*/
	@Override
  	public int insertFile(FileVO fileVo){
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try {
			int result = session.insert("fileSQL.insertFile", fileVo);
			
			session.commit();
			session.close();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
  	
  	
  	/**
	* Method : selectFilebyPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :포스트 코드에 해당하는 파일들을 가져온다. 
	*/
	@Override
  	public List<FileVO> selectFilebyPostCode(String postCode){
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try {
			
			List<FileVO> fileList = session.selectList("fileSQL.nowFileCodeNumber", postCode);
			
			return fileList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
