package com.example.spring05;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//#m 아래 4개는 한꺼번에 같이 사용한다. JSP에서는 set/get을 수동으로 다 만들어야 했지만, Lombok이 자동 처리해줌.
@Builder //빌더 형식으로 필드에 값을 대입한 객체를 얻어낼수 있도록 해준다 (객체생성과 필드에 값 넣기를 1줄 코딩으로 할 수 있다)
@NoArgsConstructor //디폴트 생성자를 자동으로 만들어 준다.
@AllArgsConstructor //필드의 모든값을 전달받아서 저장하는 생성자를 만들어 준다. (모든 인자 생성자)
@Data //setter, getter 메소드 + toString() 메소드를 자동으로 만들어준다. #m getter/setter, toString, equals, hashCode 자동 생성
public class MemberDto {
	private int num;
	private String name;
	private String addr;

}


