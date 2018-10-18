package comment.vo;

public class CommentVO {
	
	private Integer commentCode;
	private String userId;
	private String commentCon;
	private String commentDate;
	private Integer postCode;
	private Integer commentRefer;
	private Integer commentAvailable;
	
	public Integer getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(Integer commentCode) {
		this.commentCode = commentCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentCon() {
		return commentCon;
	}
	public void setCommentCon(String commentCon) {
		this.commentCon = commentCon;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public Integer getPostCode() {
		return postCode;
	}
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	public Integer getCommentRefer() {
		return commentRefer;
	}
	public void setCommentRefer(Integer commentRefer) {
		this.commentRefer = commentRefer;
	}
	public Integer getCommentAvailable() {
		return commentAvailable;
	}
	public void setCommentAvailable(Integer commentAvailable) {
		this.commentAvailable = commentAvailable;
	}
	
}
