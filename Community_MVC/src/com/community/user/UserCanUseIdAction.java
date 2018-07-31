package com.community.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.user.UserDao;

public class UserCanUseIdAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("inputId");
		
		boolean canUse = UserDao.getInstance().canUseId(id);
		
		request.getSession().setAttribute("canUse", canUse);
		
		return new ActionForward("/views/user/canUse.jsp", true);
	}
	

}
