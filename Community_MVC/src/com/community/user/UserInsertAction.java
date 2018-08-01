package com.community.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.controller.Action;
import com.community.controller.ActionForward;
import com.community.dao.user.UserDao;
import com.community.dto.user.UserDto;
import com.community.util.ShaEncoder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserInsertAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		//파일을 저장할 폴더의 절대 경로 얻어오기
		//application은 ServletContext type 객체인데
		//jsp 기본 객체이다.
		//WebContent/upload 폴더를 만들어야 한다.
		ServletContext application=request.getServletContext();
		String realPath = application.getRealPath("/upload/userPic");
		//최대 업로드 사이즈 값 지정
		int sizeLimit = 1024*1024*50;	//50 Mbyte
		/*
			WEB-INF/lib/cos.jar 파일이 있어야 아래의 MultipartRequest,
			DefaultFileRenamePolicy 클래스를 import해서 사용할 수 있다.
		*/
		
		//MultipartRequest 객체를 생성한다.
		//객체가 성공적으로 생성되면 클라이언트가 업로드한 파일이
		//WebContent/upload 폴더에 저장된다.
		MultipartRequest mr = null;
		boolean isSuccess = false;
		UserDto dto = new UserDto();
		try {
			mr = new MultipartRequest(request,
					realPath,
					sizeLimit,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			//원본 파일명
			String orgFileName = mr.getOriginalFileName("image");
			
			if(orgFileName != null) {
				
				Pattern p = Pattern.compile("\\.(jpg|jpeg|png|gif)$", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(orgFileName);
				
				if(m.find()) {
					//파일 시스템에 저장된 파일명
					String saveFileName= mr.getFilesystemName("image");
					
					dto.setPic("/upload/userPic/" +saveFileName);				
				} else {
					isSuccess = false;
				}
			} else {
				dto.setPic("/resources/img/no-profile.jpg");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dto.setPic("/resources/img/no-profile.jpg");
			e.printStackTrace();
		} finally {
			String id = mr.getParameter("id");
			String pwd = mr.getParameter("pwd");
			String nickname = mr.getParameter("nickname");
			String email = mr.getParameter("email");

			pwd = ShaEncoder.getInstance().getEncodedStr(pwd);
			
			
			dto.setId(id);
			dto.setPwd(pwd);
			dto.setNickname(nickname);
			dto.setEmail(email);
			
			
			//DB에 저장한다.
			isSuccess = UserDao.getInstance().insert(dto);
		}
	
		request.setAttribute("isSuccess", isSuccess);
	
		return new ActionForward("/views/user/insert.jsp");
	}
}
