package com.example.spring08.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;
import com.example.spring08.repository.BoardDao;
import com.example.spring08.repository.CommentDao;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	
	//하나의 서비스에서 여러개의 dao 에 의존하는 경우도 많다 
	private final BoardDao boardDao;
	private final CommentDao commentDao;
	
	// pageNum 또는 keyword 에 해당하는 글목록과 추가 정보를 BoardListResponse 객체에 담아서 
	// 리턴하는 메소드 
	@Override
	public BoardListResponse getBoardList(int pageNum, BoardDto dto) {
		
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=3;
		
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=3;

		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT; //공차수열
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT; //등비수열 
		
		//하단 시작 페이지 번호 (정수를 정수로 나누면 소수점이 버려진 정수가 나온다)
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		//전체글의 갯수
		int totalRow = boardDao.getCount(dto);
		
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		// startRowNum 과 endRowNum 을 BoardDto 객체에 담아서
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//글 목록 얻어오기 (검색 키워드가 있다면 조건에 맞는 목록만 얻어낸다)
		List<BoardDto> list=boardDao.selectPage(dto);
		
		//query 문자열을 미리 구성해서 view page 에 전달하도록 한다
		/*
		 * 검색 키워드가 없으면 query="" 빈문자열
		 * 검색 키워드가 있으면 query="&search=검색조건&keyword=검색어" 형식의 문자열  ==> <a href="/board/list?pageNum=2____">
		 */
		String query="";
		if(dto.getKeyword() != null) {
			query="&search="+dto.getSearch()+"&keyword="+dto.getKeyword();
		}
		
		//한줄 coding 으로 BoardListResponse 객체를 만들어서 리턴하기
		return BoardListResponse.builder()
				.list(list)
				.pageNum(pageNum)
				.keyword(dto.getKeyword())
				.search(dto.getSearch())
				.query(query)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.totalRow(totalRow)
				.build();
	}

	@Override
	public void createContent(BoardDto dto) {
		boardDao.insert(dto);
		//글정보가 저장된 이후에는 dto.num 에 글번호가 들어 있다.
	}

	@Override
	public BoardDto getDetail(BoardDto dto) {
		
		return boardDao.getByDto(dto);
	}

	@Override
	public List<CommentDto> getComments(int parentNum) {
		
		return commentDao.selectList(parentNum);
	}

	@Override
	public void createComment(CommentDto dto) {
		//댓글의 그룹번호가 넘어오지 않으면 dto.getGroupNum() 은 0 을 리턴한다. 
		
		//저장할 댓글의 pk 를 미리 얻어낸다.
		int num=commentDao.getSequence();
		//댓글의 글번호로 사용하고 
		dto.setNum(num);
		//만일 원글의 댓글이면
		if(dto.getGroupNum() == 0) {
			dto.setGroupNum(num); //원글의 댓글은 자신의 글번호가 댓글의 그룹번호이고 
		}
		
		//댓글 작성자를 얻어내서 dto 에 담는다 
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		
		//대댓글이면 이미 dto 에 댓글의 그룹번호가 들어 있다.
		commentDao.insert(dto);
	}

	@Override
	public void updateComment(CommentDto dto) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생시킨다.
		String writer = commentDao.getByNum(dto.getNum()).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("남의 글을 수정할수 없습니다!");
		}
		commentDao.update(dto);
		
	}

	@Override
	public void deleteComment(int num) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생시킨다.
		String writer = commentDao.getByNum(num).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("남의 글을 지울수는 없습니다!");
		}
		//댓글 삭제하기 
		commentDao.delete(num);
	}


	@Override
	public void deleteContent(int num) {
		// 글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생.
		String writer=boardDao.getByNum(num).getWriter();
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("본인이 작성한 글 외에 지울 수 없습니다.");
			
		}
		//삭제하고 삭제된 row 의 갯수를 리턴받는다. 정상 삭제시 1, 삭제 실패시 0 가 리턴된다.
		int rowCount = boardDao.delete(num);
		if(rowCount == 0) {
			throw new RuntimeException("삭제 실패!");
		}
	}

	@Override
	public BoardDto getData(int num) {
		return boardDao.getByNum(num);
	}

	
	@Override
	public void updateContent(BoardDto dto) {
	// 글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생.
		String writer=boardDao.getByNum(dto.getNum()).getWriter();
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("본인이 작성한 글만 수정할 수 없습니다.");
		}
		int rowCount = boardDao.update(dto);
		if(rowCount == 0) {
			throw new RuntimeException("수정 실패!");
		}		
		
	}	
	
}	










