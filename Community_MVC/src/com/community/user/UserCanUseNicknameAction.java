package com.community.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.user.UserDao;

public class UserCanUseNicknameAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String nickname = request.getParameter("inputNickname");
		
		boolean canUse = UserDao.getInstance().canUseNickname(nickname);
		
		request.getSession().setAttribute("canUse", canUse);
		
		return new ActionForward("/views/user/canUse.jsp", true);
	}
	
}
