package com.example.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FortuneController {
	/*
	 * HttpServletRequest 는 HTTP 의 모든 기능을 다루는 객체
	 * Model 은 view page 로 넘길 데이터만 담는 개체 (더 편리하게 사용할수 있다)
	 * Model 객체도 컨트롤러 메소드의 매개변수에 선언만하면 자동으로 spring 이 전달해 준다.
	 */
	@GetMapping("/fortune2")
	public String fortune2(Model model){  //Model == 인터페이스 임!!!! 아래에서 model.addAttribute 사용.  Model 은 뷰(JSP) 로 데이터 전달할 때 사용하는 객체
		//운세 목록 (DB에서 읽어온 오늘의 운세라고 가정)
		String fortuneToday="동쪽으로 가면 귀인을 만나요";
		//Model 객체에 담으면 자동으로 HttpServletRequest 객체에 담긴다.
		model.addAttribute("fortuneToday", fortuneToday); //#m Model 객체에 "fortuneToday" 라는 이름으로 데이터 저장 & 나중에 JSP에서 ${fortuneToday} 로 접근 가능. 내부적으로는 request 영역에 저장됨 (request.setAttribute() 와 같음).
		return "fortune"; // View 이름 //#m 뷰 이름으로 "fortune" 리턴. 스프링 설정(prefix, suffix)에 따라 실제 경로는 /WEB-INF/views/fortune.jsp
	}
	
	@GetMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		//운세 목록
		//DB 에서 읽어온 오늘의 운세라고 가정하자
		String fortuneToday = "서쪽으로 가면 장대비를 만나요!";
		
		request.setAttribute("fortuneToday", fortuneToday); //#m request 객체에 "fortuneToday" 이름으로 값 저장. JSP에서 ${fortuneToday} 로 출력 가능.
		
		//WEB-INF/views/fortune.jsp 페이지로 응답을 위임한다는 의미 (접두어+/fortune+접미어)
		return "fortune"; // View 이름
	}
	
}
