package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// WebServlet 으로 /friends 로 읽을 수 있도록 추가
@WebServlet("/friends")
public class FriendsServlet extends HttpServlet {
	//service 입력 + fn+ctrl+space 로 @Override & req, resp 변경.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB에서 읽어온 친구 목록이라고 가정
		List<String> names=new ArrayList<>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		
		
		// html(만들어논 html 기본 응답)+ fn+_ctrl+space 불러오기
		// 응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		// 응답 컨텐츠 설정
		response.setContentType("text/html; charset=utf-8");

		// 클라이언트에게 출력할 수 있는 객체의 참조값 얻기
		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title> </title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>친구 목록입니다</h1>");
		pw.println("<ul>");

		for(String tmp:names) {
			pw.println("<li>"+tmp+"</li>");
		}
		
		pw.println("</ul>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
	}
}
