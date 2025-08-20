package com.example.spring04.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring04.dto.BookDto;

@Repository //DAO 클래스임을 표시(스프링이 Bean으로 관리 + 예외 변환 기능 제공).
public class BookDaoImpl implements BookDao {
	@Autowired
	private SqlSession session;

	@Override
	public List<BookDto> selectAll() {
		//selectList = 동적이고, 여러개의 rows(정보)를 select 할 때 사용.
		List<BookDto> list = session.selectList("book.selectAll");
		return list;
	}

	@Override
	public BookDto getByNum(int num) {
		//selectOne = 동적이고, 한개의 row(정보)를 select 할 때 사용.
		BookDto dto = session.selectOne("book.getByNum", num);
		return dto;
	}

	@Override
	public void insert(BookDto dto) {
		session.insert("book.insert", dto);
	}

	@Override
	public int update(BookDto dto) {
		return session.update("book.update", dto);
	}

	@Override
	public int deleteByNum(int num) {
		return session.delete("book.delete", num); //BookMapper.xml의 ID="delete" 와 BookDaoImpl.java의 session.delete("book.delete",num) 이 일치해야 작동된다. 
	}
	
}
