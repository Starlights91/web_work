package com.example.spring04.repository;

import java.util.List;

import com.example.spring04.dto.BookDto;

public interface BookDao {
	//모든 책 정보 조회
	public List<BookDto> selectAll();
	//특정 번호로 책 한권의 정보 조회
	public BookDto getByNum(int num);
	//책 정보 추가
	public void insert(BookDto dto);
	// update & delete = 리턴값: 수정/삭제된 행의 갯수 = int 사용.
	//책 정보 수정
	public int update(BookDto dto);
	//특정 번호로 책 정보 삭제
	public int deleteByNum(int num);
}
