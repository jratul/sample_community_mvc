package com.community.board.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeBoardDao;
import com.community.dto.board.BoardDto;

public class BoardFreeListAction extends Action{
	
	// �� �������� ��Ÿ�� �ο��� ����
	private static final int PAGE_ROW_COUNT = 3;
	// �ϴ� ���÷��� ������ ����
	private static final int PAGE_DISPLAY_COUNT = 3;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * request�� �˻� keyword�� ���޵� ���� �ְ� �ȵ� ���� �ִ�.
		 * 1. ���� �ȵǴ� ��� : home���� ��Ϻ��⸦ ���� ���
		 * 2. ���� �Ǵ� ��� : list.do���� �˻�� �Է� �� form ������ ���
		 * 3. ���޵Ǵ� ��� : �̹� �˻��� �� ���¿��� ������ ��ũ�� ���� ���
		 */
		//�˻��� ���õ� �Ķ���͸� �о�� ����.
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		//BoardDto ��ü�� �����ؼ�
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
		
		// ������ �������� ��ȣ
		int pageNum = 1;
		// ������ �������� ��ȣ�� �Ķ���ͷ� ���޵Ǵ��� �о�´�.
		String strPageNum = request.getParameter("pageNum");
		if (strPageNum != null) {// ������ ��ȣ�� �Ķ���ͷ� �Ѿ�´ٸ�
			// ������ ��ȣ�� �����Ѵ�.
			pageNum = Integer.parseInt(strPageNum);
		}
		// ������ ������ �������� ���� ResultSet row ��ȣ
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// ������ ������ �������� �� ResultSet row ��ȣ
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// ��ü row �� ������ �о�´�.
		int totalRow = FreeBoardDao.getInstance().getCount(dto);
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
		
		//CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		List<BoardDto> list = FreeBoardDao.getInstance().getList(dto);

		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		//��ü row�� ������ �����ϱ�
		request.setAttribute("totalRow", totalRow);
		
		request.setAttribute("boardName",  "free");
		
		return new ActionForward("/views/board/list.jsp");
	}
	
}
