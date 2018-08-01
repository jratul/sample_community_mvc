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
}
