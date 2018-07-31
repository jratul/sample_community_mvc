package com.community.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/*
 * 	EncodingFilter는 post 방식 파라미터 전송했을 때
 * 한글이 깨지지 않도록 인코딩 설정을 자동으로 해주는 역할을 한다.
 * 
 * 필터의 동작 맵핑은 WEB-INF/web.xml 문서에 되어 있다.
 */
public class EncodingFilter implements Filter{
	//필드
	private String encoding;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//1. 요청을 가로채서 해야할 작업 작성
		if(req.getCharacterEncoding()==null) {
			//필드에 저장된 인코딩 형식 지정하기
			req.setCharacterEncoding(encoding);
		}
		
		//2. 다음 작업 수행하기
		chain.doFilter(req, res);
	}

	//필터가 최초 동작될 때 호출되는 메소드(객체 생성 직후)
	@Override
	public void init(FilterConfig config) throws ServletException {
		/*
		 * 아래와 같은 web.xml 문서의 내용 중에서 utf-8 문자열
		 * 읽어내기
		 * <init-param>
		 * 		<param-name>encoding</param-name>
		 * 		<param-value>utf-8</param-value>
		 * </init-param>
		 */
		//일겅온 문자열을 멤버 필드에 저장한다.
		encoding = config.getInitParameter("encoding");
		
	}

}
