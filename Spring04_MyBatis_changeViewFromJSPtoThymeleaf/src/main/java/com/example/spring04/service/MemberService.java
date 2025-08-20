package com.example.spring04.service;

import java.util.List;

import com.example.spring04.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> getAll();
	public MemberDto getMember(int num);
	public void addMember(MemberDto dto);
	public void updateMember(MemberDto dto);
	public void deleteMember(int num);
	
	//아래는 개인 연습용으로 추가한 것 
//	public MemberDto getMemInfo(int num);
//	public int deleteMem(int num);
//	public void insertMem(MemberDto dto);

	
	

}
