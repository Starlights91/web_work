package com.example.spring04.service;

import java.util.List;

import com.example.spring04.dto.BookDto;

public interface BookService {
	public List<BookDto> getAll();
	// 특정 번호의 책을 update & delete = 리턴값: 수정/삭제된 행의 갯수 = int 사용.
	public BookDto getBook(int num);
	public void insertBook(BookDto dto);
	public void updateBook(BookDto dto);
	public void deleteBook(int num);
	

}
