package com.community.controller;

import com.community.home.HomeAction;
import com.community.user.UserCanUseEmailAction;
import com.community.user.UserCanUseIdAction;
import com.community.user.UserCanUseNicknameAction;
import com.community.user.UserInsertAction;
import com.community.user.UserInsertFormAction;

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
		} else if(command.equals("/user/insert_form")) {
			action = new UserInsertFormAction();
		} else if(command.equals("/user/canUseId")) {
			action = new UserCanUseIdAction();
		} else if(command.equals("/user/canUseNickname")) {
			action = new UserCanUseNicknameAction();
		} else if(command.equals("/user/canUseEmail")) {
			action = new UserCanUseEmailAction();
		} else if(command.equals("/user/insert")) {
			action = new UserInsertAction();
		}
		
		return action;
	}
}
