package com.campus.board.market.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.community.upload.model.service.ImgUploadService;
import com.campus.community.upload.model.service.ImgUploadServiceImpl;
import com.campus.community.upload.model.vo.Image;
import com.campus.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MarketUploadFileServlet
 */
@WebServlet("/board/market/upload.do")
public class MarketBoardFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage=request.getParameter("currentPage");
		String userId=request.getParameter("userId");
		String uploadPath = request.getServletContext().getRealPath("/community/uploadFile/market");
		int sizeLimit = 1024*1024*50;
		
		String encType = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		File size = new File(uploadPath+"\\"+multi.getFilesystemName("file"));
		if(size.length()>(1024*1024*1024)){
			size.delete();
			RequestDispatcher view = request.getRequestDispatcher("/community/market/fileSizeLimit.jsp");
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		}
		request.setCharacterEncoding("UTF-8");
		String imgName = multi.getFilesystemName("file");
		String imgPath = "/community/uploadFile/market/"+imgName;
		int imgSize=(int) size.length();
		String imgBoard="market";
		
		Image img=new Image();
		img.setImgName(imgName);
		img.setImgPath(imgPath);
		img.setImgSize(imgSize);
		img.setImgBoard(imgBoard);
		
		ImgUploadService imgService=new ImgUploadServiceImpl();
		int result=imgService.insert(img, userId);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if (result > 0) {
			out.println("<script>alert('이미지 업로드 성공!');</script>");
			RequestDispatcher view = request.getRequestDispatcher("/community/market/writeForm.jsp");
			request.setAttribute("img", img);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		} else {
			out.println("<script>alert('이미지 업로드 실패. 재시도 해주세요.');</script>");
			out.println("<script>location.replace('/community/market/writeForm.jsp');</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
