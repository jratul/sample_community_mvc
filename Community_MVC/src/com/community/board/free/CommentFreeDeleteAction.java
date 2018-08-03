package com.community.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeCommentDao;

public class CommentFreeDeleteAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		boolean isSuccess = FreeCommentDao.getInstance().commentDelete(num);
		
		request.setAttribute("isSuccess", isSuccess);
		
		return new ActionForward("/views/board/comment/delete.jsp");
	}

}
