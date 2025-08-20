package com.example.spring04;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Spring04MyBatisApplication { //애플리케이션의 메인 클래스이다. (이름은 관례적으로 프로젝트명+Application)

	public static void main(String[] args) {
		SpringApplication.run(Spring04MyBatisApplication.class, args);
	} //스프링 부트 앱을 시작. 내장 톰캣이 뜨고, 빈 생성/초기화가 끝나면 이벤트들이 이어서 발생
	
	//크롬 브라우저를 자동으로 열어 주는 메소드
	@EventListener(ApplicationReadyEvent.class)
	public void openChrome() {
		String url = "http://localhost:9000/"; // 추가로 spring04/ 붙여주면 바로 들어가짐
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
