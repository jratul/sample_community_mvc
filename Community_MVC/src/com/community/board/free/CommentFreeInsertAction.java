package com.community.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeCommentDao;
import com.community.dto.board.CommentDto;

public class CommentFreeInsertAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		int parentNum = Integer.parseInt(request.getParameter("parentNum"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		String content = request.getParameter("content");
		
		CommentDto dto = new CommentDto();
		dto.setWriter(writer);
		dto.setPostNum(postNum);
		dto.setParentNum(parentNum);
		dto.setDepth(depth);
		dto.setContent(content);
		
		boolean isSuccess = FreeCommentDao.getInstance().insert(dto);
		
		request.setAttribute("isSuccess", isSuccess);
		request.setAttribute("boardName", "free");
		request.setAttribute("postNum", postNum);
		
		return new ActionForward("/views/board/comment/insert.jsp");
	}
	

}
