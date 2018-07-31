package com.community.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.user.UserDao;

public class UserCanUseEmailAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("inputEmail");
		
		boolean canUse = UserDao.getInstance().canUseEmail(email);
		
		request.getSession().setAttribute("canUse", canUse);
		
		return new ActionForward("/views/user/canUse.jsp", true);
	}

}
