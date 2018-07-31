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
		//�ۼ���
		String writer = (String) request.getSession().getAttribute("id");
		//����
		String title = request.getParameter("title");
		//����
		String content = request.getParameter("content");
		
		//BoardDto�� ���
		BoardDto dto = new BoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		//DB�� ����
		boolean isSuccess = FreeBoardDao.getInstance().insert(dto);
		
		//request�� ���� ���� ���
		request.setAttribute("isSuccess", isSuccess);
		
		request.setAttribute("boardName", "free");
		
		//view �������� forward �̵��ؼ� ����
		return new ActionForward("/views/board/private/insert.jsp");
	}
	
}
