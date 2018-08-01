package com.community.dao.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.community.dto.board.BoardDto;
import com.community.mybatis.SqlMapConfig;

public class FreeBoardDao {
	private static FreeBoardDao dao;
	private static SqlSessionFactory factory;
	
	private FreeBoardDao() {}
	
	public static FreeBoardDao getInstance() {
		if(dao == null) {
			dao = new FreeBoardDao();
			factory = SqlMapConfig.getSqlSession();
		}
		
		return dao;
	}
	
	public boolean insert(BoardDto dto) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.insert("freeBoard.insert", dto);
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
	
	public List<BoardDto> getList(BoardDto dto) {
		SqlSession session = null;
		List<BoardDto> list = new ArrayList<>();
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			list = session.selectList("freeBoard.getList", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	//글의 개수를 리턴하는 메소드
	public int getCount(BoardDto dto) {
		SqlSession session = null;
		int count = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			count = session.selectOne("freeBoard.getCount", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return count;
	}
	
	public BoardDto getData(BoardDto dto) {
		SqlSession session = null;
		BoardDto resultDto = null;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			resultDto = session.selectOne("freeBoard.getData", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return resultDto;
	}
	
	public boolean addViewCount(int num) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = factory.openSession(true);
			//sql문 예시
			//flag = session.insert("member.insert",dto);
			flag = session.update("freeBoard.addViewCount", num);
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
			flag = session.update("freeBoard.addPoint", id);
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
