package com.example.spring08.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor 	// 모든 필드값을 전달 받는 생성자 (Builder 를 사용하려면 필수)
@NoArgsConstructor 		// 빈 생성자
@Data 					// setter, getter
public class GalleryImageDto {
	private int num;
	//gallery 테이블의 PK 참조
	private int galleryNum;
	private String saveFileName;
	private String createdAt;
	
	
}
