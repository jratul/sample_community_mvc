package com.community.board.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.board.FreeBoardDao;
import com.community.dto.board.BoardDto;

public class BoardFreeListAction extends Action{
	
	// 한 페이지에 나타낼 로우의 개수
	private static final int PAGE_ROW_COUNT = 3;
	// 하단 디스플레이 페이지 개수
	private static final int PAGE_DISPLAY_COUNT = 3;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * request에 검색 keyword가 전달될 수도 있고 안될 수도 있다.
		 * 1. 전달 안되는 경우 : home에서 목록보기를 누른 경우
		 * 2. 전달 되는 경우 : list.do에서 검색어를 입력 후 form 전송한 경우
		 * 3. 전달되는 경우 : 이미 검색을 한 상태에서 페이지 링크를 누른 경우
		 */
		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		//BoardDto 객체를 생성해서
		BoardDto dto = new BoardDto();
		if(keyword!=null) { 	//검색어가 전달된 경우
			if(condition.equals("titlecontent")) {		//제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if(condition.equals("title")) {		//제목 검색
				dto.setTitle(keyword);
			} else if(condition.equals("writer")) {		//작성자 검색
				dto.setWriter(keyword);
			}
			//list.jsp에서 필요한 내용 담기
			request.setAttribute("condition", condition);
			request.setAttribute("keyword", keyword);
		}
		
		// 보여줄 페이지의 번호
		int pageNum = 1;
		// 보여줄 페이지의 번호가 파라미터로 전달되는지 읽어온다.
		String strPageNum = request.getParameter("pageNum");
		if (strPageNum != null) {// 페이지 번호가 파라미터로 넘어온다면
			// 페이지 번호를 설정한다.
			pageNum = Integer.parseInt(strPageNum);
		}
		// 보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// 전체 row 의 갯수를 읽어온다.
		int totalRow = FreeBoardDao.getInstance().getCount(dto);
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
		
		//CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		List<BoardDto> list = FreeBoardDao.getInstance().getList(dto);

		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		//전체 row의 개수도 전달하기
		request.setAttribute("totalRow", totalRow);
		
		request.setAttribute("boardName",  "free");
		
		return new ActionForward("/views/board/list.jsp");
	}
	
}
