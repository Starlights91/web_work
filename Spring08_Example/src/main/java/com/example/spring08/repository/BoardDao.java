package com.example.spring08.repository;

import java.util.List;

import com.example.spring08.dto.BoardDto;

public interface BoardDao {
	
	public List<BoardDto> selectPage(BoardDto dto);
	public int getCount(BoardDto dto);
	
	public void insert(BoardDto dto);
	public BoardDto getByNum(int num);
	
	public BoardDto getByDto(BoardDto dto);
	
	// 추가 (update, delete, addViewCount)
	public int delete(int num); //게시판 삭제 메소드 (번호를 이용해서)	
	public int update(BoardDto dto); //게시판 수정 
	//
	public void addViewCount(int num); //게시글 조회수
	

}
