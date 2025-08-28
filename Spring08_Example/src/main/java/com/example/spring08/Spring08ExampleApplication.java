package com.example.spring08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.spring08.aop.Messenger;
import com.example.spring08.dto.MemberDto;
import com.example.spring08.test.WritingUtil;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;


@Slf4j //logger 객체 사용 (디버깅)
@PropertySource(value="classpath:custom.properties") //custom.properties 파일 로딩 
@SpringBootApplication
public class Spring08ExampleApplication {

	//의존 객체 주입받기
	@Autowired
	private WritingUtil util;
	
	//의존 객체 주입받기 (Messenger.java)
	@Autowired
	private Messenger m;
	
	//spring 프레임 워크가 준비 되고 난 이후에 호출할 메소드ㄴ
	@PostConstruct
	public void testAop() {
		/*
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
		*/
		
		//오늘의 인사가 출력 안된다
		m.sendGreeting("안녕 바부야");
		//오늘의 인사가 출력된다.
		m.sendGreeting("안녕 노을아");
		
		MemberDto dto = MemberDto.builder().num(1).name("흰둥이").addr("강서구").build();
		m.sendMember(dto);
		
		String msg = m.getMessage();
		//System.out.println("리턴된 msg:"+msg);
		log.debug("리턴된 msg:"+msg);

	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Spring08ExampleApplication.class, args);
	}

}
