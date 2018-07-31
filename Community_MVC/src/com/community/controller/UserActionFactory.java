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

	// 자신의 참조값을 리턴해주는 메소드
	public static UserActionFactory getInstance() {
		if (factory == null) {
			factory = new UserActionFactory();
		}
		return factory;
	}

	// 인자로 전달되는 command 를 수행할 Action type 객체를 리턴해주는
	// 메소드
	public Action action(String command) {
		// Action 추상클래스 type 을 담을 지역변수 만들기
		Action action = null;
		
		if(command.equals("/home")) {
			//홈 요청 처리
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
