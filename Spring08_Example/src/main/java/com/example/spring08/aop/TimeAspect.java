package com.example.spring08.aop;

import java.util.Date; //java.util.Date

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * 	횡단 관심사를 따로 클래스를 만들어서 작성한다.
 */

@Aspect
@Component // @Component 어노테이션을 붙여서 spring 이 관리하는 bean 으로 등록을 한다. 빈이 안되면, aop 도 동작 안함.
public class TimeAspect {
	
	@Before("execution(void write*())") //메서드 실행전
	public void start() {
		Date start = new Date();
		long startTime = start.getTime();
		System.out.println("시작 시간:"+ startTime);
	}
	@After("execution(void write*())") // 메서드 성공/실패와 관계없이 실행. return 타입이 void 이면서 메소드명에 *은 wildcard 
	public void end() {
		Date start = new Date();
		long startTime = start.getTime();
		System.out.println("종료 시간:"+ startTime);
	}
	
	
}
