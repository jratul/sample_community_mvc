package com.community.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class UserLogoutAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		
		request.setAttribute("msg", "로그아웃되었습니다.");
		request.setAttribute("url", request.getContextPath());
	
		return new ActionForward("/views/alert.jsp");
	}

}
