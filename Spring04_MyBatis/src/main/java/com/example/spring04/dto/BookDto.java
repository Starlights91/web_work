package com.example.spring04.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("bookDto") //BookMapper 의 resultType="bookDto"로 사용 가능.
@Setter
@Getter

public class BookDto {
	private int num;
	private String title;
	private String author;
	private String publisher;

}
