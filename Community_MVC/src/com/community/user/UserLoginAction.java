package com.community.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.user.UserDao;
import com.community.dto.user.UserDto;
import com.community.util.ShaEncoder;

public class UserLoginAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		pwd = ShaEncoder.getInstance().getEncodedStr(pwd);
		
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPwd(pwd);
		
		boolean isSuccess = UserDao.getInstance().isLoginValid(dto);
		
		if(isSuccess) {
			request.getSession().setAttribute("id", id);
		}
		
		request.setAttribute("isSuccess",  isSuccess);
		
		return new ActionForward("/views/user/login.jsp");
	}

}
