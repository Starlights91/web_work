package com.example.spring04.repository; //#m 클래스가 속한 패키지 경로. repository 패키지 → 보통 DB 접근 계층(DAO) 을 담는 위치

import java.util.List; //#m 회원 목록을 여러 명 담아 반환할 때 필요 (List 컬렉션 타입을 사용하기 위해 import.)

import com.example.spring04.dto.MemberDto; //#m DB의 row 데이터를 자바 객체로 옮겨 담는 역할


public interface MemberDao { //#m DAO interface 선언 → DB 관련 메소드 시그니처만 정의
	public List<MemberDto> selectAll(); //#m 모든 회원 정보를 조회해서 List<MemberDto> 형태로 리턴.
	public void insert(MemberDto dto); //#m 새로운 회원을 등록. dto 안에 들어 있는 num, name, addr 등을 DB에 삽입
	//update, delete 는 수정, 삭제된 row 의 갯수를 (int로)리턴하는 모양으로 Dao 메소드를 정의한다. #m 1 → 성공 (한 행 수정/삭제됨), 0 → 실패 (해당 행 없음)
	public int update(MemberDto dto); //#m 회원 정보 수정. 리턴값: 수정된 행(row)의 개수.
	public int deleteByNum(int num); //#m 회원 번호(num) 기준으로 회원 삭제. 리턴값: 삭제된 행의 개수.
	public MemberDto getByNum(int num); //#m 회원 번호(num)로 특정 회원 조회. 리턴값: MemberDto (해당 회원의 정보).
}	
