package file.service;

import java.util.List;

import file.dao.FileDao;
import file.dao.IFileDao;
import file.vo.FileVO;


public class FileService implements IFileService{
	private IFileDao dao = null;
	private static IFileService service = null;
	
	private FileService() {
		dao = FileDao.getInstance();
	}
	
	public static IFileService getInstance() {
		if(service == null) {
			service = new FileService();
		}
		
		return service;
	}
	
	/**
	* Method : nowFileCodeNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일 최신 코드를 가져온다.
	*/
  	public Integer nowFileCodeNumber(){
  		return dao.nowFileCodeNumber();
  	}
  	
  	/**
	* Method : insertFile
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일을 생성한다. 
	*/
  	public int insertFile(FileVO fileVo){
  		return dao.insertFile(fileVo);
  	}
  	
  	
  	/**
	* Method : selectFilebyPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :포스트 코드에 해당하는 파일들을 가져온다. 
	*/
  	public List<FileVO> selectFilebyPostCode(String postCode){
  		return dao.selectFilebyPostCode(postCode);
  	}
	
}	
