package com.community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	//�߻� �޼ҵ�
	public abstract ActionForward execute(HttpServletRequest request, HttpServletResponse response);
}