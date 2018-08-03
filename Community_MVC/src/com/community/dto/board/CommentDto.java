package com.community.dto.board;

public class CommentDto {
	private int num;
	private String writer;
	private String content;
	private int postNum;
	private int parentNum;
	private int depth;
	private String regdate;
	private int likeCnt;
	private int dislikeCnt;
	private int startRowNum;
	private int endRowNum;
	private String pic;
	private int isDelete;
	
	public CommentDto() {}

	public CommentDto(int num, String writer, String content, int postNum, int parentNum, int depth, String regdate,
			int likeCnt, int dislikeCnt, int startRowNum, int endRowNum, String pic, int isDelete) {
		super();
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.postNum = postNum;
		this.parentNum = parentNum;
		this.depth = depth;
		this.regdate = regdate;
		this.likeCnt = likeCnt;
		this.dislikeCnt = dislikeCnt;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.pic = pic;
		this.isDelete = isDelete;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getParentNum() {
		return parentNum;
	}

	public void setParentNum(int parentNum) {
		this.parentNum = parentNum;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getDislikeCnt() {
		return dislikeCnt;
	}

	public void setDislikeCnt(int dislikeCnt) {
		this.dislikeCnt = dislikeCnt;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	
}
