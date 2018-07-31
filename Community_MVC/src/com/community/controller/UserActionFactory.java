package com.community.controller;

import com.community.home.HomeAction;

public class UserActionFactory {
	private static UserActionFactory factory;

	private UserActionFactory() {
	}

	// �ڽ��� �������� �������ִ� �޼ҵ�
	public static UserActionFactory getInstance() {
		if (factory == null) {
			factory = new UserActionFactory();
		}
		return factory;
	}

	// ���ڷ� ���޵Ǵ� command �� ������ Action type ��ü�� �������ִ�
	// �޼ҵ�
	public Action action(String command) {
		// Action �߻�Ŭ���� type �� ���� �������� �����
		Action action = null;
		
		if(command.equals("/home")) {
			//Ȩ ��û ó��
			action = new HomeAction();
		} 
		
		return action;
	}
}
