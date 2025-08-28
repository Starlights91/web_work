package com.example.spring08.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;
import com.example.spring08.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService service;
	
	
	//#m 여기 3개는 board 게시글 관련 
	@PostMapping("/board/update")
	public String boardUpdate(BoardDto dto, RedirectAttributes ra) {
		//글 수정 반영하고
		service.updateContent(dto);
		//리다일렉트 이동해서 출력할 메세지도 담는다.
		ra.addFlashAttribute("message", "게시글을 성공적으로 수정했습니다");
		// 글 자세히 보기로 리다일렉트 이동
		return "redirect:/board/view?num="+dto.getNum();
	}
	
	
	@GetMapping("/board/edit")
	public String boardEdit(int num, Model model) {
		model.addAttribute("dto", service.getData(num));
		return "/board/edit";
	}
	
	
	@GetMapping("/board/delete")
	public String boardDelete(int num) {
		service.deleteContent(num);
		return "board/delete";
	}
	
	
	
	// 여기 아래부터는 comment 관련: 
	
	@PostMapping("/board/comment-update")
	public String boardUpdate(CommentDto dto) {
		service.updateComment(dto);
		return "redirect:/board/view?num="+dto.getParentNum();
	}
	
	@GetMapping("/board/comment-delete")
	public String boardDelete(CommentDto dto) {
		//dto 에는 삭제할 댓글의 글번호와 원글의 긍번호가 들어 있다.
		service.deleteComment(dto.getNum());
		
		return "redirect:/board/view?num="+dto.getParentNum();
	}
	
	
	@PostMapping("/board/save-comment")
	public String boardSave(CommentDto dto) {
		//서비스를 이용해서 새로운 댓글을 저장한다
		service.createComment(dto);
		//댓글을 작성한 원글 자세히 보기로 다시 리다일렉트 이동시키기 (02 플젝: save-comment.jsp 참고 비교)
		return "redirect:/board/view?num="+dto.getParentNum();
	}
	
	// #m 조회수 카운트적용을 위한 기존 ("/board/view") 맵핑 수정 (comment + board)
	@GetMapping("/board/view")
	public String boardView(BoardDto requestDto, Model model) {
		/*
		 * requestDto 에는 자세히 보여줄 글의 num 와 
		 * search (검색조건), keyword (검색어) 가 들어 있을수도 있다.
		 * 검색어가 없는경우는 search 와 keyword 에는 null 이 들어 있다.
		 */
		
		//서비스를 이용해서 응답에 필요한 데이터를 얻어내서 
		BoardDto dto=service.getDetail(requestDto);
		
		String query="";
		if(requestDto.getKeyword() != null) {
			query="&search="+requestDto.getSearch()+"&keyword="+requestDto.getKeyword();
		}
		//검색 query 정보도 view page 에 전달한다 
		model.addAttribute("query", query);
		
		
		//댓글 목록은 원글의 글번호requestDto.getNum()를 전달해서 얻어낸다.
		List<CommentDto> comments = service.getComments(requestDto.getNum());
		//모델 객체에 담고 
		model.addAttribute("dto", dto); // dto 
		model.addAttribute("commentList", comments); // 댓글목록 
		System.out.println(comments.size());
		
		//로그인된 userName 얻어내기 (null 일수도 있다)
		//로그인을 안했으면 "annonymousUser"가 리턴된다.
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(userName); 
		boolean isLogin = userName.equals("anonymouseUser") ? false : true;
		//위의 추가 정보도 모델 객체에 담는다.
		model.addAttribute("userName", userName); //유저네임
		model.addAttribute("isLogin", isLogin); //로그인정보
		
		//타임리프 페이지에서 응답한다.
		return "board/view";
		
	}
	
	
	/*
	 * 	@ModelAttribute 는 view page 에서 필요한 값을 대신 Model 객체에 담아준다.
	 */
	@PostMapping("/board/save")
	public String boardSave(@ModelAttribute("dto") BoardDto dto) { //#m BoardDto dto) 값이 자동으로 모델 dto에 담기고, save.html에서 num=${dto.num} 으로 저장된 글번호를 출력을 해줄 수 있다. 폼 전송되는 내용 <input name : title & content ... 이 BoardDto에 담겨져서
		//글작성자
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		// 서비스를 이용해서 글을 저장하기
		service.createContent(dto);
		return "/board/save";
	}
	
	
	@GetMapping("/board/new-form")
	public String newForm() {
		return "board/new-form";
	}
	
	
	/*
	 * 	@RequestParam 어노테이션을 이용하면 요청 파라미터를 추출하면서 해당 값이 없으면 defaultValue 를 설정할수 있다.
	 */
	@GetMapping("/board/list")
	public String list(Model model, 
			@RequestParam(defaultValue = "1") int pageNum, 
			BoardDto dto) {
		//BoardDto 객체에는 keyword 와 search 가 있을수도 있다. (없을 수도 있다 = null)
		
		//응답에 필요한 데이터를 얻어내서
		BoardListResponse listResponse = service.getBoardList(pageNum, dto);
		//모델 객체에 담고
		model.addAttribute("dto", listResponse); //타임리프에서 글목록: ${listRes.list} 또는 ${dto.list} , 페이지넘: ${listRes.pageNum} 

		//타임리프 페이지에서 응답
		return "board/list"; //th:each="tmp : ${dto.list}"
	}
}
