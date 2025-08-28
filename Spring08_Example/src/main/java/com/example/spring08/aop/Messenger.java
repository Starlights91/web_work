package com.example.spring08.aop;

import org.springframework.stereotype.Component;

import com.example.spring08.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j //logger 객체 사용 (디버깅)
@Component  // @Component 어노테이션을 붙여서 spring 이 관리하는 bean 으로 등록을 한다. 빈이 안되면, aop 도 동작 안함.
public class Messenger {

	public void sendGreeting(String msg) {
		//System.out.println("오늘의 인사:"+msg);
		log.debug("오늘의 인사:"+msg);
	}
	
	public void sendMember(MemberDto dto) {
		if(dto!=null) {
		//System.out.println(dto);
			log.debug(dto.toString());
		}
	}

	public String getMessage() {
		//System.out.println("getMessage() 메소드가 수행됩니다.");
		log.debug("getMessage() 메소드가 수행됩니다");
		return "열심히 공부하자";
	}
	
	
}
