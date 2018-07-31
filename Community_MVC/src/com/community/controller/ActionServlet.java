package com.community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// .do�� ������ ��� ��û�� ó���� ����
@WebServlet("*.do")
public class ActionServlet extends HttpServlet {
	// . �� ������ ��û Ȯ������ ����
	public static final int INCLUDE_EXTENSION_LENGTH = 3;			//.do�� 3

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��û URI �о����
		String uri = request.getRequestURI();
		// context name �о����
		String contextName = request.getContextPath();
		// ���� ��û ��θ� ���� �Ѵ�.
		// command => /fortune or /info or /signin or /signout
		String command = uri.substring(contextName.length(), uri.length() - INCLUDE_EXTENSION_LENGTH);

		// �ش� command �� ������ Action ��ü�� ���丮�� ���� ���´�.
		Action action = UserActionFactory.getInstance().action(command);

		if (action != null) {// ó�� ������ command ���
			ActionForward af = null;
			try {
				af = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			if (af.isRedirect()) {// redirect �̵��ؾ� �Ѵٸ�
				// redirect �̵� ��Ų��.
				response.sendRedirect(contextName + af.getPath());
			} else {// forward �̵��ؾ� �Ѵٸ�
				RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
				rd.forward(request, response);
			}
		}
	}
}
