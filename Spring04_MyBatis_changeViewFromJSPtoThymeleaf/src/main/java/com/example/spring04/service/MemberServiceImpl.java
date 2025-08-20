package com.example.spring04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.exception.MemberException;
import com.example.spring04.repository.MemberDao;

import lombok.RequiredArgsConstructor;

//서비스 클래스에 붙여줄 어노테이션
@Service
@RequiredArgsConstructor //lombok 이 생성자를 자동으로 만들어 주도록 한다.
public class MemberServiceImpl implements MemberService {
	//의존 객체에 final 예약어를 붙이고 클래스에 @RequiredArgsConstructor 를 
	//붙이면 의존 객체를 전달받는 생성자가 자동으로 만들어진다. & bean 으로 관리한다.
	//의존 객체 (를 final 로 선언).
	@Autowired
	private final MemberDao dao;
	
	
	@Override
	public List<MemberDto> getAll() {
		return dao.selectAll();
	}

	@Override
	public MemberDto getMember(int num) {
		MemberDto dto=dao.getByNum(num);
		//만일 select 되는 회원정보가 없다면?
		if(dto==null) {
			//예외 발생시키기
			throw MemberException.notFound(num);
			
		}
		return dto;
	}

	@Override
	public void addMember(MemberDto dto) {
		/*
		 * 	insert 과정에서 SQL Exception 이 발생하면 자동으로 DataAccessException 이 발생한다.
		 * 	dao 에 붙여 놓은 @Repository 어노테이션 때문에 
		 */
		//수정 되지 않았다면?		
		dao.insert(dto);
	}

	@Override
	public void updateMember(MemberDto dto) {
		int rowCount = dao.update(dto);
		//만일 수정되지 않았다면?
		if(rowCount==0) {
			//예외 발생시키기
			throw MemberException.updateFailed(dto.getNum());
		}
	}

	@Override
	public void deleteMember(int num) {
		int rowCount = dao.deleteByNum(num);
		//만일 삭제되지 않았다면?
		if(rowCount == 0) {
			//예외 발생시키기
			throw MemberException.deleteFailed(num);
			
		}
	}

	
	// 개인 연습으로 추가
//	@Override
//	public MemberDto getMemInfo(int num) {
//	    return dao.getByNum(num);
//	}
//
//	@Override
//	public int deleteMem(int num) {
//	    return dao.deleteByNum(num);
//	}
//
//	@Override
//	public void insertMem(MemberDto dto) {
//	    dao.insert(dto);
//	}



	
	
	
}
