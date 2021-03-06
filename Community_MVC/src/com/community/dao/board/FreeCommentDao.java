package com.community.dao.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.community.dto.board.CommentDto;
import com.community.mybatis.SqlMapConfig;

public class FreeCommentDao {
	private static FreeCommentDao dao;
	private static SqlSessionFactory factory;
	
	private FreeCommentDao() {}
	
	public static FreeCommentDao getInstance() {
		if(dao == null) {
			dao = new FreeCommentDao();
			factory = SqlMapConfig.getSqlSession();
		}
		
		return dao;
	}
	
	public int getCount(int postNum) {
		SqlSession session = null;
		int result = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			result = session.selectOne("freeComment.getCount", postNum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public List<CommentDto> getList(CommentDto dto) {
		SqlSession session = null;
		List<CommentDto> list = new ArrayList<>();
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			list = session.selectList("freeComment.getList", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public boolean insert(CommentDto dto) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.insert("freeComment.insert", dto);
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
	
	public boolean addPoint(String id) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			session.update("freeComment.addPoint", id);
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
	
	public boolean checkAlreadyLike(CommentDto dto) {
		SqlSession session = null;
		int result = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			result = session.selectOne("freeComment.checkAlreadyLike", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doLike(CommentDto dto) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.insert("freeComment.doLike", dto);
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
	
	public boolean doDislike(CommentDto dto) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.insert("freeComment.doDislike", dto);
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
	
	public boolean upLike(int num) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.update("freeComment.upLike", num);
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
	
	public boolean upDislike(int num) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.update("freeComment.upDislike", num);
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
	
	public boolean commentDelete(int num) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.update("freeComment.commentDelete", num);
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
	
	public CommentDto getData(int num) {
		SqlSession session = null;
		CommentDto dto = null;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			dto = session.selectOne("freeCommnet.getData", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}
	
	public boolean update(CommentDto dto) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.update("freeComment.update", dto);
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
}
