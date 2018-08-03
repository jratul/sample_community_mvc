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
	
	// 한 페이지에 나타낼 로우의 개수
	private static final int PAGE_ROW_COUNT = 10;
	// 하단 디스플레이 페이지 개수
	private static final int PAGE_DISPLAY_COUNT = 10;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int pageNum = 1;
		
		String strPageNum = request.getParameter("pageNum");
		
		if(strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}
		
		// 보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum = pageNum * PAGE_ROW_COUNT; 
		
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		// 전체 row 의 갯수를 읽어온다.
		int totalRow = FreeCommentDao.getInstance().getCount(postNum);
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		// 끝 페이지 번호가 잘못된 값이라면
		if (totalPageCount < endPageNum) {
			endPageNum = totalPageCount; // 보정해준다.
		}
		
		CommentDto dto = new CommentDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		dto.setPostNum(postNum);
		
		List<CommentDto> list = FreeCommentDao.getInstance().getList(dto);
		
		for(CommentDto tmp:list) {
			if(tmp.getIsDelete() == 1) {
				tmp.setContent("삭제된 댓글입니다.");
			}
		}
		
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		
		return new ActionForward("/views/board/comment/list.jsp");
	}

}
