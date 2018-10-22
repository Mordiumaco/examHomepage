package board.vo;


public class BoardVO {
	private Integer boardCode;
	private String userId;
	private String boardName;
	private String boardDate;
	private Integer boardAvailable;
	
	public Integer getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(Integer boardCode) {
		this.boardCode = boardCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public Integer getBoardAvailable() {
		return boardAvailable;
	}
	public void setBoardAvailable(Integer boardAvailable) {
		this.boardAvailable = boardAvailable;
	}
	
	
}
