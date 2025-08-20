package com.example.spring04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring04.dto.BookDto;
import com.example.spring04.repository.BookDao;

//서비스 클래스에 붙여줄 어노테이션
@Service
public class BookServiceImpl implements BookService {
	//의존 객체
	@Autowired
	private BookDao dao;

	@Override
	public List<BookDto> getAll() {
		return dao.selectAll();
	}

	@Override
	public BookDto getBook(int num) {
		BookDto dto = dao.getByNum(num);
		
		return dto;
	}

	@Override
	public void insertBook(BookDto dto) {
		dao.insert(dto);
	}

	@Override
	public void updateBook(BookDto dto) {
		//int rowCount = dao.update(dto);
		//수정되지 않았다면?
		dao.update(dto);
	}

	@Override
	public void deleteBook(int num) {
		int rowCount = dao.deleteByNum(num);
		//삭제되지 않았다면?
		if(rowCount ==0){
			
		}
	}

}
