package com.community.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.community.dto.user.UserDto;
import com.community.mybatis.SqlMapConfig;

public class UserDao {
	private static UserDao dao;
	private static SqlSessionFactory factory;
	
	private UserDao() {}
	
	public static UserDao getInstance() {
		if(dao==null) {
			dao = new UserDao();
			factory = SqlMapConfig.getSqlSession();
		}
		
		return dao;
	}
	
	public boolean insert(UserDto dto) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			flag = session.insert("user.insert",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canUseId(String id) {
		SqlSession session = null;
		String result = null;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			result = session.selectOne("user.canUseId", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (result == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canUseNickname(String nickname) {
		SqlSession session = null;
		String result = null;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			result = session.selectOne("user.canUseNickname", nickname);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (result == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canUseEmail(String email) {
		SqlSession session = null;
		String result = null;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			result = session.selectOne("user.canUseEmail", email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (result == null) {
			return true;
		} else {
			return false;
		}
	}
}
