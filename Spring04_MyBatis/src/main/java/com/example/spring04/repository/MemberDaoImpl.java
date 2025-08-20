package com.example.spring04.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring04.dto.MemberDto;

//Dao 에는 보통 @Repository 어노테이션을 붙여서 bean 을 만든다 (내부적으로 추가 기능을 제공해준다)
@Repository
public class MemberDaoImpl implements MemberDao { //MemberDaoImpl => spring boot app 이 시작되는 시점에 spring 이 직접 객체를 생성한다.
	// MyBatis 를 사용할때 필요한 핵심 객체
	@Autowired
	private final SqlSession session; //session 필드를 final (상수)로 변경 불가하게 입
	
	//"생성자를 이용해서 의존 객체를 주입" 받는것이 더 일반적이다 (lombok 의 기능을 이용하면 생략 가능하다)
	//@Autowired //생성자가 오직 1개인 경우에는 생략 가능하다
	public MemberDaoImpl(SqlSession session) { //SqlSession 타입의 객체를 생성해서 전달.  new MemberDaoImpl( ) <- 이()괄호 안에 SqlSession 타입 전달이 되면서 객체 생성.
		this.session=session;
	}
	
	
	@Override
	public List<MemberDto> selectAll() {
		//return session.selectList("member.selectAll");
		
		/*
		 * #m row (출력할 정보가) 여러개이면 .selectList() 
		 * .selectList() 를 호출하면 리턴 type 은 무조건 List<T> 이다
		 * 	List 의 generic type T 는 그때 그때 다르다
		 * 	resultType 이 바로 List 의 generic type 으로 설정된다.
		 */
		
		List<MemberDto> list = session.selectList("member.selectAll");
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto); // session. 객체에 insert 문으로 MemberDto dto 타입이 MemberMapper.xml에서 파라미터로 전달된다.
	}

	@Override
	public int update(MemberDto dto) {
		// update 를 실행하고 update 된 row 의 갯수를 바로 리턴하기
		return session.update("member.update", dto); //리턴: 정수값 = update 된 row 의 갯수 = 1; int로 상단에 설계가 되어 있어서,
	}

	@Override
	public int deleteByNum(int num) { //1. num
		// delete 를 실행하고 delete 된 row 의 갯수를 바로 리턴하기
		return session.delete("member.delete", num); //2.파라미터로 전달한  num 삭제할 번호만 넘겨주면 Member
	} // 0이면 작업 실패. 1이면 성공
	/*
	 * 	select 되는 row 가 1개(1개의 정보)이면 session.selectOne() 메소드를 사용하고
	 * 	select 되는 row 가 여러개(여러개의 정보)일 가능성이 있으면 session.selectList() 메소드를 사용해서 select 한다.
	 */
	@Override
	public MemberDto getByNum(int num) {
		//selectOne = 동적이고, 한개의 정보를 select 할 때 사용. 
		MemberDto dto = session.selectOne("member.getByNum",num);
		return dto;
	}

}
