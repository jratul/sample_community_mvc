package com.community.board.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeBoardDao;
import com.community.dao.board.FreeCommentDao;
import com.community.dto.board.CommentDto;

public class CommentFreeListAction extends Action{
	
	// �� �������� ��Ÿ�� �ο��� ����
	private static final int PAGE_ROW_COUNT = 10;
	// �ϴ� ���÷��� ������ ����
	private static final int PAGE_DISPLAY_COUNT = 10;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int pageNum = 1;
		
		String strPageNum = request.getParameter("pageNum");
		
		if(strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}
		
		// ������ ������ �������� ���� ResultSet row ��ȣ
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// ������ ������ �������� �� ResultSet row ��ȣ
		int endRowNum = pageNum * PAGE_ROW_COUNT; 
		
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		// ��ü row �� ������ �о�´�.
		int totalRow = FreeCommentDao.getInstance().getCount(postNum);
		// ��ü �������� ���� ���ϱ�
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// ���� ������ ��ȣ
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// �� ������ ��ȣ
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		// �� ������ ��ȣ�� �߸��� ���̶��
		if (totalPageCount < endPageNum) {
			endPageNum = totalPageCount; // �������ش�.
		}
		
		CommentDto dto = new CommentDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		dto.setPostNum(postNum);
		
		List<CommentDto> list = FreeCommentDao.getInstance().getList(dto);
		
		for(CommentDto tmp:list) {
			if(tmp.getIsDelete() == 1) {
				tmp.setContent("������ ����Դϴ�.");
			}
		}
		
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		
		return new ActionForward("/views/board/comment/list.jsp");
	}

}
