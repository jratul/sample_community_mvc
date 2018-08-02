package com.community.controller;

import com.community.board.free.BoardFreeDetailAction;
import com.community.board.free.BoardFreeInsertAction;
import com.community.board.free.BoardFreeInsertFormAction;
import com.community.board.free.BoardFreeListAction;
import com.community.board.free.CommentFreeInsertAction;
import com.community.board.free.CommentFreeLikeAction;
import com.community.board.free.CommentFreeListAction;
import com.community.home.HomeAction;
import com.community.user.UserCanUseEmailAction;
import com.community.user.UserCanUseIdAction;
import com.community.user.UserCanUseNicknameAction;
import com.community.user.UserGetInfoAction;
import com.community.user.UserInsertAction;
import com.community.user.UserInsertFormAction;
import com.community.user.UserLoginAction;
import com.community.user.UserLogoutAction;

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
		} else if(command.equals("/user/login")) {
			action = new UserLoginAction();
		} else if(command.equals("/user/getInfo")) {
			action = new UserGetInfoAction();
		} else if(command.equals("/user/logout")) {
			action = new UserLogoutAction();
		} else if(command.equals("/board/free/list")) {
			action = new BoardFreeListAction();
		} else if(command.equals("/board/private/free/insert_form")) {
			action = new BoardFreeInsertFormAction();
		} else if(command.equals("/board/private/free/insert")) {
			action = new BoardFreeInsertAction();
		} else if(command.equals("/board/free/detail")) {
			action = new BoardFreeDetailAction();
		} else if(command.equals("/board/free/comment/list")) {
			action = new CommentFreeListAction();
		} else if(command.equals("/board/free/comment/insert")) {
			action = new CommentFreeInsertAction();
		} else if(command.equals("/board/free/comment/like")) {
			action = new CommentFreeLikeAction();
		}
		
		return action;
	}
}
