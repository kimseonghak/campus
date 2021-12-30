package com.campus.diary.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.IMP_LIMIT;

import com.campus.diary.model.service.DiaryService;
import com.campus.diary.model.service.DiaryServiceImpl;
import com.campus.diary.model.vo.Frame;
import com.campus.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FrameUpload
 */
@WebServlet("/diary/frameUpload.do")
public class FrameUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrameUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// cos 라이브러리 추가
		
		// ----서버에 사진 업로드----
		
		// 업로드 경로 세팅
		String uploadImagePath = request.getServletContext().getRealPath("/diary/uploadFrameImage");
		
		System.out.println(uploadImagePath);
		// 이미지 최대 사이즈
		int uploadImageSizeLimit = 1024*1024*50; // 50MB
		
		// 이미지 이름 인코딩 처리 값
		String encType = "UTF-8";
		
		// 정책 설정 - DefaultFileRenamePolicy 사용
		
		// 위 정보를 가지고, MultipartRequest 객체 생성 -> 객체를 생성하는 순간 경로에 이미지 업로드 됨
		MultipartRequest multi = new MultipartRequest(request,
														uploadImagePath,
														uploadImageSizeLimit,
														encType,
														new DefaultFileRenamePolicy());
		
		// 파일 사이즈 검증
		File tmp = new File(uploadImagePath+"\\"+multi.getFilesystemName("file"));
		//System.out.println("검증 파일 사이즈 : " +tmp.length());
		
		if(tmp.length()>(1024*1024*1024))
		{
			tmp.delete(); // 해당 파일 삭제
			response.sendRedirect("/diary/d_fileSizeLimit.jsp");
			return;
		}
		
		
		// ---- db에 저장하기 위한 처리 ----
		
		//인코딩
		request.setCharacterEncoding("UTF-8");
		// 업로드 유저 id
		String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
		// 사진 이름
		String imageName = multi.getFilesystemName("file");
		// 사진 경로
		String imagePath = "/diary/uploadFrameImage/"+imageName;
		// 프레임 타이틀
		String frameTitle = multi.getParameter("title");
		
		// 프레임 날짜
		Date frameDate = Date.valueOf(multi.getParameter("date"));
		/*
		System.out.println(userId);
		System.out.println(imageName);
		System.out.println(imagePath);
		System.out.println(frameTitle);
		System.out.println(frameDate);
		System.out.println(imagePath.length());
		System.out.println(frameTitle.length());
		*/
		//System.out.println(imagePath);

		Frame fr = new Frame();
		fr.setUserId(userId);
		fr.setImageName(imageName);
		fr.setImagePath(imagePath);
		fr.setFrameTitle(frameTitle);
		fr.setFrameDate(frameDate);
		
		DiaryService fService = new DiaryServiceImpl();
		int result = fService.insertFrameUpload(fr);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/diary/d_frameUploadResult.jsp");
		if(result>0)
		{
			request.setAttribute("result", true);
		}
		else
		{
			request.setAttribute("result", false);
		}
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
