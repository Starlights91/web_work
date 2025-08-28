package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring08.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //의존객체 생성자로 주입받기 위한 어노테이션(lombok 의 기능)
@Repository
public class BoardDaoImpl implements BoardDao {
	//의존 객체
	private final SqlSession session;

	@Override
	public List<BoardDto> selectPage(BoardDto dto) {
		/*
		 * 	1. mapper's namespace : board
		 * 	2. sql's id : selectPage
		 * 	3. parameterType : BoardDto
		 * 	4. resultType : BoardDto (select 된 row 하나를 어떤 type 으로 받을지를 결정해 준다)
		 */
		// dto 에 keyword 가 있을수도 있고(=해당키워드) 없을 수도 있다(=null). keyword 가 있다면, search 는 "title_content" 제목+내용검색 or "title" 제목검색 or "writer" 작성자검색 
		return session.selectList("board.selectPage", dto);
	}


	@Override
	public int getCount(BoardDto dto) {
		// TODO Auto-generated method stub
		return session.selectOne("board.getCount", dto);
	}


	@Override
	public void insert(BoardDto dto) {
		//이 메소드를 호출하는 시점에 dto.num 은 0 이지만
		session.insert("board.insert",dto);
		//이 메소드가 리턴된 이후에는 dto.num 의 저장된 글번호가 들어 있다 (selectKey 값이 들어 있다)
	}

	@Override
	public BoardDto getByNum(int num) {
		return session.selectOne("board.getByNum", num);
	}

	
	@Override
	public BoardDto getByDto(BoardDto dto) {
		return session.selectOne("board.getByDto", dto);
	}
	
	@Override
	public int delete(int num) {
		return session.delete("board.delete", num);
	}


	@Override
	public int update(BoardDto dto) {
		return session.update("board.update", dto);
	}


	@Override
	public void addViewCount(int num) {
		session.update("board.addViewCount", num);
		
	}


}
