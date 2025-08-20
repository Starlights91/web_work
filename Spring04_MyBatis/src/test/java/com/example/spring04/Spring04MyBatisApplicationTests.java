package com.example.spring04;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
//import org.assertj.core.api.Assertions; 이거 아니니까 주의하기
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;
/*
 * 	여기에 test 코드를 넣고 run As JUnit Test 를 하면 단위 테스트를 할수가 있다.
 */
@SpringBootTest
class Spring04MyBatisApplicationTests {
	
	// session 객체를 주입받는다고 가정
	@Autowired
	private SqlSession session;
	
	@Autowired
	private MemberDao dao;
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(session, "sqlsession은 null 이면 안됨"); //Assertions. ctrl+space 하면 static method 있다.
		Assertions.assertNotNull(dao,"MemberDao 는 null 이면 안됨");
		
	}
	
	@Test
	void sqlsession_dao_injected() { // 메소드명 맘대로 지을 수 있음 : sqlsession_dao_injected
		Assertions.assertNotNull(session, "sqlsession은 null 이면 안됨"); //Assertions. ctrl+space 하면 static method 있다.
		Assertions.assertNotNull(dao,"MemberDao 는 null 이면 안됨");		
	}
	
	@Test
	@DisplayName("MemberDao 와 SqlSession 객체 DI 성공 여부 테스트") // 어떤 테스트를 하는 메소드인지 적는 곳 
	void test01() { //메소드명은 간단하데 적고
		Assertions.assertNotNull(session, "sqlsession은 null 이면 안됨"); //assertNotNull == 반드시 null이 아니어야 한다로 명시)  Assertions. ctrl+space 하면 static method 있다.
		Assertions.assertNotNull(dao,"MemberDao 는 null 이면 안됨");			
	}
	
	@Test
	@DisplayName("회원 목록 select 테스트")
	void selectTest() {
		//회원목록을 select 한다
		List<MemberDto> list=dao.selectAll();
		Assertions.assertNotNull(list, "selectAll() 은 null 이 아니여야 합니다." ); // Assertions.assertNotNull(list); 가 위반 되었을 때 메세지를 표시 하고 싶으면 , 메세지 입력
		
	}
	
	

}
