package com.example.spring08.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 응답에 필요한 데이터를 하나의 객체에 담을수 있도록 클래스를 설계한다. 아래 4개는 한꺼번에 같이 사용한다.
@Builder //빌더 형식으로 필드에 값을 대입한 객체를 얻어낼수 있도록 해준다 (객체생성과 필드에 값 넣기를 1줄 코딩으로 할 수 있다)
@NoArgsConstructor // 빈 생성자 #m 디폴트 생성자를 자동으로 만들어 준다.
@AllArgsConstructor // 모든 필드값을 전달 받는 생성자 (Builder 를 사용하려면 필요함)  #m 필드의 모든값을 전달받아서 저장하는 생성자를 만들어 준다. (모든 인자 생성자)
@Data //setter, getter 메소드 + toString() 메소드를 자동으로 만들어준다.
public class BoardListResponse { //또는 BoardListDto 라고 해도 된다.
	//글목록
	private List<BoardDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
	private int totalRow;
	private String keyword;
}
