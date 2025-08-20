package com.example.spring03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
/*
 * 	@Controller 어노테이션(=특별한 기능을 수행하는 객체)이 붙어 있기때문에 spring 은 new HomeController() 해서 객체를 생성한 다음
 * 	
 * 	spring bean container 에서 직접 관리를 한다.
 * 	
 * 	또한 @Controller 어노테이션이 붙은 객체는 클라이언트의 요청을 처리하는 틀별한 객체 이기 때문에 해당 동작을 하기위한
 * 	
 * 	준비 작업도 이루어진다.
 */
@Controller
public class HomeController {
	/*
	 * 	http://localhost:9000
	 * 	위에 처럼 이 서버의 최상위(root) 경로 요청이 왔을때 요청을 처리할 컨트롤러 메소드 
	 */
	@GetMapping("/")
	public String home(HttpServletRequest request) { //#m 여기 필요한 객체만 선언하면 request 전달 (request=응답하기 전까지 유지되고, 응답하고나서 사라짐=1회성 데이터)
		//DB 에서 읽어온 공지사항이라고 가정하자
		List<String> notice=List.of("Spring 프레임워크가 시작되었습니다.", "어쩌구..", "저쩌구.."); //#m DB 에서 읽어온 공지사항 3가지라 가정.
		
		//응답에 필요한 data 를 HttpServletRequest 객체에 담아준다
		request.setAttribute("notice",notice); //"notice" => in jsp 페이지 ${notice} 어떤 키값으로 어떤 타입을 담아서 가져온건지 알아야 함.
		/*
		 * 	@ResponseBody 어노테이션 없이 리턴해주는 문자열은 view page(jsp 페이지=client 한테 보여주는 페이지) 의 위치를 의미한다.
		 * 	
		 * 	"home" 를 리턴하면 application.properties 파일에 설정된것 대로
		 * 	접두어(prefix) 에 "/WEB-INF/views 가 붙고
		 * 	접미어(suffix) 에 ".jsp" 가 자동으로 붙어서
		 * 	결국 view page 는 "WEB-INF/views/home.jsp" 를 가리키는 것이다. (따라서 여기서 출력)
		 * 	해당 jsp 페이지로 응답이 위임된다 (forward 이동) #따라서 controller 에서 응답을 request 영역에 필요한 데이터 DB에서 가져와서 httpservlet setAttribute.. 담아서 응답
		 * 	#m 원래 java코드로 request.getAttribute...로 하던거 EL ${ } 로  
		 */
		return "home"; //application.properties까지 jsp 의 prefix&suffix 설정을 했기때문에 return "WEB-INF/views/home.jsp"; 안써도 동작 하는 것. 
	}
}
