package com.example.spring05;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring05ThymeleafApplication { //애플리케이션의 메인 클래스이다. (이름은 관례적으로 프로젝트명+Application)
	
	//이 클래스로 객체가 생성된 직후 호출될 메소드에 붙이는 어노테이션
	@PostConstruct
	public void test() {
		//@Data 어노테이션이 붙은 MemberDto 클래스로 테스
		MemberDto dto=new MemberDto();
		dto.setNum(1);
		dto.setName("애옹이");
		dto.setAddr("강남구");
		// @Data 어노테이션이 toString() 메소드를 재정의 하기 때문에 객체의 필드안에 들어 있는 내용 확인 가능
		System.out.println(dto);
		
		// @Builder 의 기능을 이용해서 MemberDto 객체 얻어내기 (1줄코딩)
		MemberDto dto2 = MemberDto.builder().num(2).name("야옹이").addr("강남구").build();
		System.out.println(dto2);
		
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Spring05ThymeleafApplication.class, args);
	} //스프링 부트 앱을 시작. 내장 톰캣이 뜨고, 빈 생성/초기화가 끝나면 이벤트들이 이어서 발생
	
	//크롬 브라우저를 자동으로 열어 주는 메소드
	@EventListener(ApplicationReadyEvent.class)
	public void openChrome() {
		String url = "http://localhost:9000/spring05/";
		// 운영체제의 얻어와서 이름을 소문자로 
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder builder = null;
		try {
			if (os.contains("win")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "start chrome " + url);
			} else if (os.contains("mac")) {
				builder = new ProcessBuilder("/usr/bin/open", "-a", "Google Chrome", url);
			} else {
				System.out.println("지원 하지 않는 운영체제 입니다.");
				return;
			}
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
