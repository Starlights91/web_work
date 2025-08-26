package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring08.dto.CommentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CommentDaoImpl implements CommentDao {
	
	private final SqlSession session;

	//원글의 글번호를 이용해서 원글에 달린 댓글 목록을 리턴하는 메소드
	@Override
	public List<CommentDto> selectList(int parentNum) {
		
		return session.selectList("comment.selectList", parentNum); //#m mapper의 namespace = comment // .점 뒤에는 sql의 id=" " 와 일치시켜야 함. // , parentNum = Mapper에서 parameter type 
	}


	@Override
	public int delete(int num) {
		return session.update("comment.delete", num);
	}

	@Override
	public int update(CommentDto dto) {
		return session.update("comment.update", dto);
	}
	
	@Override
	public void insert(CommentDto dto) {
		//이 메소드를 호출하는 시점에 dto.num 은 0 이지만
		session.insert("comment.insert", dto);
		//이 메소드가 리턴된 이후에는 dto.num 의 저장된 글번호가 들어 있다 (selectKey 값이 들어 있다)
		
	}

	@Override
	public int getSequence() {
		return session.selectOne("comment.getSequence");
	}


	@Override
	public CommentDto getByNum(int num) {
		return session.selectOne("comment.getByNum", num);
	}

}
