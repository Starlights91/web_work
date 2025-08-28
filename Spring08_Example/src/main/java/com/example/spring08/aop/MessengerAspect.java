package com.example.spring08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.spring08.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그출력을 도와주는 어노테이션. 이 어노테이션이 붙어있는 클래스의 메소드에서 log 객체를 사용할 수 있다.
@Aspect //Aspect 역할 을 할 수 있도록 특별한 기능 추가 
@Component // @Component 어노테이션을 붙여서 spring 이 관리하는 bean 으로 등록을 한다. 빈이 안되면, aop 도 동작 안함.
public class MessengerAspect {
	
	/*
	 * 	1. 메소드의 return type 은 String 이고
	 * 	2. com.example.spring08.util 패키지에 속해있는 모든클래스(*) 중에서
	 * 	3. get 으로 시작하는 메소드
	 * 	4. 메소드의 매개변수는 비어있는 메소드
	 * 
	 * 	위의 4가지 조건이 모두 만족하면 아래의 aspect 가 적용된다.
	 */
	
	@Around("execution(String com.example.spring08.aop.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect 가 적용된 메소드를 실행하고 해당 메소드가 리턴하는 값을 변수에 담기
		Object obj=joinPoint.proceed();
		//원래 type 으로 casting
		String returnValue = (String)obj;
		log.debug("원래 리턴한 값:"+returnValue);
		//리턴값이 있는 메소드에 aspect 를 적용하면 반드시 해당 데이터를 리턴해야 한다.
		return "뭔 공부야? 놀자~"; //다른 값을 리턴해줄수도 있다.
		
		//return obj;
	}
	
		
	
	// .. 은 매개변수의 모양을 상관하지 않겠다. (갯수와 type을 제한하지 않음)
	// spring 이 관리하는 bean 의 메소드중에서 리턴 type 이 void 이고 send 로 시작하는 모든 메소드에 적용된다.
	@Around("execution(void send*(..))") // 메서드 실행전후 (가장 일반적) @Around 는 {} 안에서 @Before & @After 에서 할 작업을 쓸 수 있다.
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자들 목록을 얻어내기
		Object[] args=joinPoint.getArgs();
		//반복문 돌면서 매개변수에 담긴 값들을 하나하나 참조하면서
		for(Object tmp:args) {
			//찾고 싶은 type 을 확인한다
			if(tmp instanceof String) { //만일 String type 이라면
				//찾았다면 원래 type 으로 casting 한다.
				String msg = (String)tmp;
				//System.out.println("매개 변수에 전달된값:"+msg);
				//log 객체를 이용해서 메세지를 출력해 보자
				//log.info("매개변수에 전달된 값: "+msg); //초록색 INFO
				//log.warn("매개변수에 전달된 값: "+msg); //노란색 WARN
				//log.error("매개변수에 전달된 값: "+msg); //빨간색 ERROR
				log.debug("매개변수에 전달된 값: "+msg); //디버그 메세지 출력하는 것 (디버깅용인데, 배포할 때 ..
				if(msg.contains("바부")) {
					log.error("바부는 금지된 단어입니다. 메소드 호출을 차단합니다");
					return; // 여기서 리턴하면 아래의 joinPoint.proceed(); 가 호출이 안된다.
				}
				
			}
			if(tmp instanceof MemberDto) { //만일 MemberDto type 이라면
				log.debug("매개 변수에 전달된 값:"+tmp);
				MemberDto dto =(MemberDto)tmp;
				dto.setName("하늘이");
				
			}
			
			
		}
		//이 메소드를 호출하는 시점에 실제로 aspect 가 적용된 메소드가 수행된다.(호출하지 않으면 수행안됌)
		joinPoint.proceed();
		
	}
	
}
