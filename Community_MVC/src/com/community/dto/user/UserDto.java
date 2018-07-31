package com.community.dto.user;

public class UserDto {
	private String id;
	private String pwd;
	private String nickname;
	private String email;
	private String pic;
	private int point;
	private String regdate;
	
	public UserDto () {}

	public UserDto(String id, String pwd, String nickname, String email, String pic, int point, String regdate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.nickname = nickname;
		this.email = email;
		this.pic = pic;
		this.point = point;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
