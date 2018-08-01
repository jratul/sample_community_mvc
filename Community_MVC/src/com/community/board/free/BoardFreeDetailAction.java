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
		//1. 파라미터로 전달되는 글 번호 읽어오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		//CafeDto 객체를 생성해서
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
		
		dto.setNum(num);

		//2. CafeDao를 이용해서 글 정보를 읽어와서
		BoardDto resultDto = FreeBoardDao.getInstance().getData(dto);
		
		FreeBoardDao.getInstance().addViewCount(num);
		//3. request에 담고
		request.setAttribute("dto", resultDto);
		
		//로그인 여부 확인해서 request에 담기
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
		
		//4. view 페이지로 forward 이동해서 응답
		return new ActionForward("/views/board/detail.jsp");
	}

}
