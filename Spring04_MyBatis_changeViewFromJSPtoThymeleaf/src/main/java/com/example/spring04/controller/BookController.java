package com.example.spring04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring04.dto.BookDto;
import com.example.spring04.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	//여기서 필요한건 DAO. 필요한 의존 객체를 주입 받는다.
	private final BookService service;
	
	//5. 수정할 도서 정보 title, author, publisher 가 추출되서 BookDto 객체에 담겨서 전달
	@PostMapping("/book/update")
	public String update(BookDto dto) {
		service.updateBook(dto);
		return "book/update";
	}
	
	//4. 도서 수정
	@GetMapping("/book/modify")
	public String modify(int num, Model model) {
		//수정할 도서 정보를 얻어와서
		BookDto dto=service.getBook(num);
		//Model 객체에 담고
		model.addAttribute("dto", dto);
		//view page 로 forward 이동해서 응답
		return "book/modify";
	}
	
	//3. 도서 삭제
	@GetMapping("/book/delete")
	public String delete(int num) {
		service.deleteBook(num);
		return "book/delete";
	}
	
	//2. 도서 추가
	@PostMapping("/book/add")
	public String add(BookDto dto) {
		service.insertBook(dto);
		return "redirect:/book/list";
	}
	
	//1. 도서 추가(양식)
	@GetMapping("/book/new-form")
	public String insertForm() {
		//현재 여기서 수행할 로직은 없고 view page 의 위치만 리턴해준다.
		return "book/new-form";
	}
	
	
	//0. 전체 도서 목록 페이지 (list 출력하기 위한 코드)
	@GetMapping("/book/list")
	public String list(Model model) {
		List<BookDto> list = service.getAll();
		model.addAttribute("bookList", list);
		
		return "book/list";
	}
	
	
}
