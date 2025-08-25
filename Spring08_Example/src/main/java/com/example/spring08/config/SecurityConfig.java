package com.example.spring08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정에 관련된 bean 이라고 알려준다. (#m 이 클래스가 스프링 설정 클래스(빈 정의)임을 알림)
@EnableWebSecurity //Spring Security 를 설정하기 위한 어노테이션 (#m 웹 보안(필터 체인) 구성 활성화)
public class SecurityConfig { //보안설정 담는 구성 클래스 시작
	/*
	 * 메소드에 @Bean 어노테이션을 붙여 놓으면
	 * 1. Spring 이 자동으로 해당 메소드를 호출하고
	 * 2. 매개변수에 필요한 type 객체가 있으면 알아서 spring bean container 에서 해당 type 객체를 찾아서
	 * 전달해준다.
	 * 3. 또한 메소드가 리턴해주는 객체를 spring bean container 에서 관리해준다.
	 */
	@Bean //#m 아래 메서드가 반환하는 객체를 스프링 빈으로 등록해 관리
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		// security 를 통과하는 white list
		String[] whiteList = {"/", "/user/loginform", "/user/login-fail", 
				"/user/expired", "/user/signup-form", "/user/signup", 
				"/test/**", "/user/check-id"};
		
		//#m 는 함수표현식으로 한줄 코딩인것. (#m 체이닝으로 보안 설정 시작)
		httpSecurity
		.csrf(csrf-> csrf.disable()) //csrf 검증 사용하지 않기 (#m 개발 편의용; 운영에서는 보통 활성화 후 토큰 사용 권장)
		.authorizeHttpRequests(config->
			config
			.requestMatchers(whiteList).permitAll() //whiteList 는 무조건 받아 들이고 (#m whiteList에 정의한 경로는 누구나 접근 가능)
			//역할에 따라서 요청 구분한다.
			.requestMatchers("/admin/**").hasRole("ADMIN") //admin 하위 요청은 반드시 admin 이여야 한다.
			.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF") //staff 하위 요청은 반드시 admin 또는 staff 이여야 한다.
			.anyRequest().authenticated() //그 외 다른 모든 요청은 인증을 (필요)진행한다
		)
		.formLogin(config ->  //#m 폼 로그인 동작 방식 설정
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 (#m 로그인 폼 보여주는 GET 페이지)
				.loginPage("/user/required-loginform")
				//로그인 폼의 action (로그인 처리는 spring security 가 대신 해준다) (#m 로그인 인증을 처리할 POST 엔드포인트)
				.loginProcessingUrl("/user/login") // #m CustomUserDetailsService 클래스의 UserDetails 메소드에 있는 정보와 비교를 한다.
				//로그인 폼의 사용자명 입력란의 name 속성의 값 <input type="text" name="userName" >
				.usernameParameter("userName")
				//로그인 폼의 비밀번호 입력란의 name 속성의 값 <input type="password" name="password" >
				.passwordParameter("password")
				//로그인 성공시 forward 이동될 url (login welcome 응답) (#m 컨트롤러는 보통 @PostMapping 으로 받음)
				//.successForwardUrl("/user/login-success")
				
				//로그인 성공시 동작하는 핸드러 객체 등록하기 (AuthSuccessHandler 클래스 참조)
				.successHandler(new AuthSuccessHandler())
				//계정, 비밀번호 문제로 로그인 실패시 forward 이동될 url (실패 되었다고 응답) (#m 역시 보통 @PostMapping)
				.failureForwardUrl("/user/login-fail")
				.permitAll() //위의 요청들은 whiteList 에 없어도 요청 가능하도록 설정(#m 로그인 페이지/처리 관련 경로에 대한 접근 허용)
		) //#m 여기 config 안에 있는 것들을 controller & view page 는 우리가 만들어야 함.
		.logout(config -> //#m 로그아웃 설정
			config
				//로그아웃시 요청할 url (spring security 가 대신 처리해준다)
				.logoutUrl("/user/logout") //#m 로그아웃 처리 URL(POST 요청 권장. security 가 session 무효화 등을 처리함)
				//로그아웃 후에 리다일렉트 이동할 url 
				.logoutSuccessUrl("/")
				.permitAll() //#m 로그아웃 관련 경로 접근 허용
		)
		.exceptionHandling(config -> // #m 예외 처리(접근 거부 등)를 설정
			//권한 부족시 forward 이동될 url
			config.accessDeniedPage("/user/denied") //#m 권한 부족(403) 시 보여줄 페이지 경로
		)
		.sessionManagement(config -> //#m 세션 관리 설정
			config
				//최대 세션 허용 갯수 설정 (다중 기기 로그인 허용 여부) #동일 계정 동시 접속허용 세션 갯수(1개로 제한).
				.maximumSessions(1)
				//로그인이 해제 되었을때 응답할 url
				.expiredUrl("/user/expired")
		);

		return httpSecurity.build(); //#m 위 설정을 반영한 SecurityFilterChain 생성/반환
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() { 
		//여기서 리턴해주는 객체도 bean 으로 된다.
		return new BCryptPasswordEncoder();
	}
	//인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder encoder, UserDetailsService service) throws Exception{
		//적절한 설정을한 인증 메니저 객체를 리턴해주면 bean 이 되어서 Spring Security 가 사용한다 
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(service)
				.passwordEncoder(encoder)
				.and()
				.build();
	}
	

}
