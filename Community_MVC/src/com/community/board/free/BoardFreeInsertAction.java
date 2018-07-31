package com.community.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeBoardDao;
import com.community.dto.board.BoardDto;

public class BoardFreeInsertAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//작성자
		String writer = (String) request.getSession().getAttribute("id");
		//제목
		String title = request.getParameter("title");
		//내용
		String content = request.getParameter("content");
		
		//BoardDto에 담기
		BoardDto dto = new BoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		//DB에 저장
		boolean isSuccess = FreeBoardDao.getInstance().insert(dto);
		
		//request에 성공 여부 담기
		request.setAttribute("isSuccess", isSuccess);
		
		request.setAttribute("boardName", "free");
		
		//view 페이지로 forward 이동해서 응답
		return new ActionForward("/views/board/private/insert.jsp");
	}
	
}
