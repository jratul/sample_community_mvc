package com.community.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeCommentDao;
import com.community.dto.board.CommentDto;

public class CommentFreeLikeAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = (String) request.getSession().getAttribute("id");
		
		int cmtNum = Integer.parseInt(request.getParameter("num"));
		
		String likeOrDislike = request.getParameter("likeOrDislike");
		
		CommentDto dto = new CommentDto();
		dto.setWriter(id);
		dto.setNum(cmtNum);
		boolean isAlreadyLike = FreeCommentDao.getInstance().checkAlreadyLike(dto);
		
		boolean canLike = false;
		if(!isAlreadyLike) {
			FreeCommentDao dao = FreeCommentDao.getInstance();
			if(likeOrDislike.equals("like")) {
				dao.doLike(dto);
				dao.upLike(cmtNum);
				canLike = true;
			} else if(likeOrDislike.equals("dislike")) {
				dao.doDislike(dto);
				dao.upDislike(cmtNum);
				canLike = true;
			}
		}
		
		request.setAttribute("canLike", canLike);
	
		
		return new ActionForward("/views/board/comment/like.jsp");
	}

}
