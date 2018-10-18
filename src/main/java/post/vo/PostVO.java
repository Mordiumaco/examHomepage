package post.vo;

public class PostVO {
	
	private Integer postCode;
	private String userId;
	private String postName;
	private String postCon;
	private String postDate;
	private String postInquiry;
	private String boardCode;
	private String postRefer;
	private Integer postAvailable;
	
	public Integer getPostCode() {
		return postCode;
	}
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostCon() {
		return postCon;
	}
	public void setPostCon(String postCon) {
		this.postCon = postCon;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getPostInquiry() {
		return postInquiry;
	}
	public void setPostInquiry(String postInquiry) {
		this.postInquiry = postInquiry;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getPostRefer() {
		return postRefer;
	}
	public void setPostRefer(String postRefer) {
		this.postRefer = postRefer;
	}
	public Integer getPostAvailable() {
		return postAvailable;
	}
	public void setPostAvailable(Integer postAvailable) {
		this.postAvailable = postAvailable;
	}
	
}
