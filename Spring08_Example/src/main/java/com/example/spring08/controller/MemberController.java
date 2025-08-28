package com.example.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring08.dto.MemberDto;
import com.example.spring08.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;
	
	@GetMapping("/member/list")
	public String list(MemberDto dto, Model model) {
		model.addAttribute("list", service.getAll());
		return "/member/list";
	}
	//new-form
	@GetMapping("/member/insertform")
	public String insertForm() {

		return "/member/insertform";
	}
	
	@PostMapping("/member/save")
	public String insert(MemberDto dto, RedirectAttributes ra) {
		service.addMember(dto);
		ra.addFlashAttribute("message", dto.getName()+" 님의 회원정보를 성공적으로 추가 했습니다.");
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/updateform")
	public String updateForm(int num, Model model) {
		model.addAttribute("dto", service.getMember(num));
		return "/member/updateform";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto, RedirectAttributes ra) {
		service.updateMember(dto);
		ra.addFlashAttribute("message", dto.getName()+" 님의 회원정보를 성공적으로 수정 했습니다.");
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/delete")
	public String delete(int num, RedirectAttributes ra) {
		// 회원정보가 delete 되기 전에 먼저 이름을 name 변수에 담는다 (메세지 출력을 위해)
		String name = service.getMember(num).getName();
		service.deleteMember(num);
		ra.addFlashAttribute("message", name+" 님의 회원정보를 성공적으로 삭제 했습니다.");
		return "redirect:/member/list";
	}
}	
