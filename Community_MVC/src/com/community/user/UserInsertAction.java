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
		
		
		//������ ������ ������ ���� ��� ������
		//application�� ServletContext type ��ü�ε�
		//jsp �⺻ ��ü�̴�.
		//WebContent/upload ������ ������ �Ѵ�.
		ServletContext application=request.getServletContext();
		String realPath = application.getRealPath("/upload/userPic");
		//�ִ� ���ε� ������ �� ����
		int sizeLimit = 1024*1024*50;	//50 Mbyte
		/*
			WEB-INF/lib/cos.jar ������ �־�� �Ʒ��� MultipartRequest,
			DefaultFileRenamePolicy Ŭ������ import�ؼ� ����� �� �ִ�.
		*/
		
		//MultipartRequest ��ü�� �����Ѵ�.
		//��ü�� ���������� �����Ǹ� Ŭ���̾�Ʈ�� ���ε��� ������
		//WebContent/upload ������ ����ȴ�.
		MultipartRequest mr = null;
		boolean isSuccess = false;
		UserDto dto = new UserDto();
		try {
			mr = new MultipartRequest(request,
					realPath,
					sizeLimit,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			//���� ���ϸ�
			String orgFileName = mr.getOriginalFileName("image");
			
			if(orgFileName != null) {
				
				Pattern p = Pattern.compile("\\.(jpg|jpeg|png|gif)$", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(orgFileName);
				
				if(m.find()) {
					//���� �ý��ۿ� ����� ���ϸ�
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
			
			
			//DB�� �����Ѵ�.
			isSuccess = UserDao.getInstance().insert(dto);
		}
	
		request.setAttribute("isSuccess", isSuccess);
	
		return new ActionForward("/views/user/insert.jsp");
	}
}
