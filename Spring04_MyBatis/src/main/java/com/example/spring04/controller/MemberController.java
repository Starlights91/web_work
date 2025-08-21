package com.example.spring04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;
import com.example.spring04.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	// #m 여기서 필요한건 DAO
	// 필요한 의존 객체를 주입 받는다
	//@Autowired private MemberDao dao;
	//@Autowired private MemberService service;
	private final MemberService service;

	
	//5. 수정할 회원의 정보 num, name , addr 이 추출되서 MemberDto 객체에 담겨서 전달
	@PostMapping("/member/update1")
	public String update1(MemberDto dto) {
		//수정할 회원의 정보가 MemberDto 객체에 담겨서 전달된다.
		//dao.update(dto);
		service.updateMember(dto);
		return "member/update1"; //여기엔 member 앞에 / 없는게 맞음.
	}
	
	
	//4. 회원 수정
	@GetMapping("/member/edit")
	public String edit(int num, Model model) {
		//수정할 회원의 정보를 얻어와서
		//MemberDto dto=dao.getByNum(num);
		MemberDto dto=service.getMember(num);
		//Model 객체에 담고
		model.addAttribute("dto", dto); //#m "dto" = MemberDto type -> edit.jsp의 value="${dto.num} 에서 EL로 getNum 방식으로 정보 추출해서 수정반영하고 view페이
		//view page 로 forward 이동해서 응답
		return "member/edit";
	}

	
	//3. 회원 삭제
	@GetMapping("/member/delete1")
	public String delete(int num) {
		/*
		 * 	매개변수에 int num 을 선언하면 요청 파라미터 중에서 num 이라는 파라미터 명으로 전달되는
		 * 	문자열을 자동추출해서 Integer.parseInt() 를 수행해서 실제 int 값으로 바꾼다음
		 * 	해당 값을 매개 변수에 전달해 준다.
		 * 	int 값으로 바꿀 수 없는 문자열이 넘어오면 에러가 발생한다
		 */
		//dao.deleteByNum(num);
		service.deleteMember(num);
		
		// return "redirect:/member/list";
		return "member/delete1";
	}
	
	//2. 회원 추가
	@PostMapping("/member/save")
	public String save(MemberDto dto) {
		/*
		 * 	매개변수에 MemberDto 를 선언하면 폼 전송되는 파라미터가 자동 추출되어서
		 * 	MemberDto 객체에 담긴 채로 전달된다.
		 * 	단 MemberDto 클래스의 필드명과 폼전송되는 파라미터명이 같아야 한다.
		 * 	private String name <=> <input type="text" name="name">
		 * 	private String addr <=> <input type="text" name="addr">
		 */
		//dao.insert(dto);
		service.addMember(dto);
		
		// 회원 목록보기 "/member/list" 요청을 다시 하라는 redirect 응답 하기
		// "redirect: 리다일렉트 경로" 처럼 redirect: 콜론 으로 시작하는 문자열을 리턴하면된다.
		return "redirect:/member/list";
	}
	
	//1. 회원 추가(양식) 
	@GetMapping("/member/new-form")
	public String newForm() {
		//현재 여기서 수행할 로직은 없고 view page 의 위치만 리턴해준다.
		return "member/new-form";
	}
	
	//0. 회원 목록 입니다 페이지 
	//list 를 출력하기 위한 코드 : (Model 객체는 Spring MVC 에서 Controller -> View 로 데이터 전달하기 위한 바구니 같은 객체)
	@GetMapping("/member/list")
	public String list(Model model) {
		// 회원 목록 출력 (List 에 각 회원의 정보를 담은 Dto들을 SelectAll() 메소드를 호출해서 담는다)
		//List<MemberDto> list=dao.selectAll();
		List<MemberDto> list=service.getAll();
		// 응답에 필요한 객체를 Model 객체에 Key, Value 형태로 담는다. (memberList라는 키값에 list를 담는다)
		model.addAttribute("memberList",list);
		
		// "/WEB-INF/views/member/list.jsp" 에서 응답하기
		return "member/list"; // view page 의 위치를 리턴한다. jsp 가 현재는 view page이다. jsp -> thymeleaf : view page. resources/static/xxx.html (html,css,images 같은 정적인 ) & templates/yyy.html (html 파일은 해석된 결과가 출력이 되는 것. thymeleaf 도 여기 안에 입력)
	}
	
	
//	// 아래는 개인 연습 
//	// list.jsp 에서 회원상세보기 버튼 눌렀을때 응답할 코드: 
//	@GetMapping("/member/view")
//	// view() 에서 '()'안에 들어간 매개변수는 각각 url에 num=?으로 들어온 파라미터를 읽어들이기 위한 @RequestParam(생략됨) int num 과
//	// 위의 num 을 사용해 받아온 회원정보를 dto에 담아서 Model 객체에 담아서 응답을 위임하기 위한 Model model
//	public String view(int num, Model model) {
//		//받아온 num을 통해 회원정보 dto 에 담는다
//		//MemberDto dto = dao.getByNum(num);
//		MemberDto dto = service.getMemInfo(num);
//		//응답할 view.jsp 페이지에 전달하기 위해 Model 객체에 담아서 (Key 이름은 원하는대로 지정가능)
//		model.addAttribute("memberView", dto);
//		
//		//응답을 위임한다.
//		return "member/view";
//	}
//	
//	//list.jsp 에서 수정하기 버튼을 눌렀을 때 응답할 코드 :
//	@GetMapping("/member/update-form")
//	//updateForm() 에서 '()'안에 들어간 매개변수는 각각
//	//url에 num=? 으로 들어온 파라미터를 읽어들이기 위한 @RequestParam(생략됨) int num 과
//	//위의 num 을 사용해 받아온 회원정보를 dto에 담아서 Model 객체에 담아서 응답을 위임하기 위한 Mpdel model
//	public String updateForm(int num, Model model) {
//		//받아온 num을 통해 회원정보 dto 에 담아서
//		//MemberDto dto = dao.getByNum(num);
//		MemberDto dto = service.getMemInfo(num);
//		//응답에 필요한 객체를 Model 객체에 담아서
//		model.addAttribute("getByNum", dto);
//		
//		//응답을 위임한다.
//		return "member/update-form";
//	}
//	
//	// update-form.jsp 에서 수정 버튼을 눌렀을 때 응답할 코드
//	// 입력한 정보를 숨기기 위해 PostMapping으로 함 (Get으로 하면 url에 노출됨)
//	@PostMapping("/member/update")
//	// Spring MVC가 form의 name 속성과 Dto 의 필드 이름이 일치할 경우, 자동으로 Dto에 데이터를 담아준다.
//	public String update(MemberDto dto) {
//		// dto로 받아온 데이터를 update() 메소드를 통해 반영한다. (dao는 이미 의존객체를 주입받았기 때문에 따로 생성하지 않고 사용가능)
//		//dao.update(dto);
//		service.updateMember(dto);
//		
//		// view.jsp 페이지로 이동시킨다. 단, 기존에 저장한 prefix, suffix 를 무시해야하기 때문에 redirect: 를 사용한다.
//		return "redirect:/member/view?num=" + dto.getNum();
//	}
//	
//	
//	// list.jsp 에서 회원추가 버튼을 눌렀을때 (추가 양식)응답할 코드 : (list.jsp에서 /member/insert 로 링크하므로 GET 이 폼을 연다)
//	@GetMapping("/member/insert-form")
//	public String insertForm() {
//		return "member/insert-form"; // /WEB-INF/views/member/insert-form.jsp 를 연다.
//	}
//
//	// insert-form.jsp 에서 회원추가 버튼 눌렀을때 응답할 코드(POST)
//	@PostMapping("/member/insert")
//	public String insert(MemberDto dto, Model model) {
//		// dto.name, dto.addr 이 넘어온다. selectKey 로 num 이 세팅된다.
//		//dao.insert(dto); // insert 를 수행한다.
//		service.insertMem(dto); // insert 를 수행한다.
//		model.addAttribute("isSuccess", true); // 예외 없이 끝났다고 가정한다.
//		model.addAttribute("num", dto.getNum()); // 발급된 번호를 보여준다.
//		return "member/insert"; // 결과 페이지로 포워드한다.
//	}
//	
//	
//	// list.jsp 의 링크(/member/delete?num=...)에 맞춰서 삭제 처리 (GET)으로 
//	@GetMapping("/member/delete")
//	public String delete(int num, Model model) {
//	    //int rows = dao.deleteByNum(num); // (한줄) 삭제를 수행한다.
//	    int rows = service.deleteMem(num); // (한줄) 삭제를 수행한다.
//	    // 결과 페이지를 보여주고 다시 회원 목록페이지로 가게 하려면 아래처럼 바꾼다.
//	    // model.addAttribute("isSuccess", rows > 0);
//	    // return "member/delete";
//	    
//	    // 회원 목록으로 리다이렉트한다.(회원목록 페이지)
//	    return "redirect:/member/list";
//
//	}
	

}
