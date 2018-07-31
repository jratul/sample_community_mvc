package com.community.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.user.UserDao;
import com.community.dto.user.UserDto;

public class UserGetInfoAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		
		UserDto dto = UserDao.getInstance().getData(id);
		
		request.setAttribute("dto", dto);
		
		return new ActionForward("/views/user/getInfo.jsp");
	}

}
