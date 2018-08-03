package com.community.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeBoardDao;
import com.community.dto.board.BoardDto;

public class HomeAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		List<BoardDto> list = FreeBoardDao.getInstance().getPreviewList();
		
		request.setAttribute("list", list);
		
		return new ActionForward("/views/home.jsp");
	}
	
}
