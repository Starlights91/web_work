package com.example.spring04.dto;

import org.apache.ibatis.type.Alias; // Mybatis 예전이름.

import lombok.Getter;
import lombok.Setter;

// MyBatis 에서 제공하는 @Alias 어노테이션을 이용해서 MemberDto type 의 별칭을 부여할수 있다.
// 부여된 별칭을 Mapper xml 문서에서 parameterType or resultType 으로 활용할수 있다.
// MemberDto 클래스명을 소문자로 별칭을 부여한 것 -> "memberDto"
@Alias("memberDto")
@Setter
@Getter
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
