package com.community.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeCommentDao;
import com.community.dto.board.CommentDto;

public class CommentFreeUpdateAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		String content = request.getParameter("content");
		
		CommentDto dto = new CommentDto();
		dto.setNum(num);
		dto.setContent(content);
		
		FreeCommentDao.getInstance().update(dto);
		
		return new ActionForward("/board/free/detail.do?num=" + postNum, true);
	}
}
