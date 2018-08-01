package com.community.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeBoardDao;
import com.community.dto.board.BoardDto;

public class BoardFreeDetailAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. �Ķ���ͷ� ���޵Ǵ� �� ��ȣ �о����
		int num = Integer.parseInt(request.getParameter("num"));
		
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		//CafeDto ��ü�� �����ؼ�
		BoardDto dto = new BoardDto();
		if(keyword!=null) { 	//�˻�� ���޵� ���
			if(condition.equals("titlecontent")) {		//����+���� �˻�
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if(condition.equals("title")) {		//���� �˻�
				dto.setTitle(keyword);
			} else if(condition.equals("writer")) {		//�ۼ��� �˻�
				dto.setWriter(keyword);
			}
			//list.jsp���� �ʿ��� ���� ���
			request.setAttribute("condition", condition);
			request.setAttribute("keyword", keyword);
		}
		
		dto.setNum(num);

		//2. CafeDao�� �̿��ؼ� �� ������ �о�ͼ�
		BoardDto resultDto = FreeBoardDao.getInstance().getData(dto);
		
		FreeBoardDao.getInstance().addViewCount(num);
		//3. request�� ���
		request.setAttribute("dto", resultDto);
		
		//�α��� ���� Ȯ���ؼ� request�� ���
		String id = (String) request.getSession().getAttribute("id");
		boolean isLogin = false;
		if(id!=null) {
			isLogin = true;
		}
		request.setAttribute("isLogin",  isLogin);
		
		//get comments
		//List<CafeCommentDto> list = CafeCommentDao.getInstance().getList(num);
		//request.setAttribute("commentList", list);
		
		request.setAttribute("boardName", "free");
		request.setAttribute("postNum", num);
		
		//4. view �������� forward �̵��ؼ� ����
		return new ActionForward("/views/board/detail.jsp");
	}

}
