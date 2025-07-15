package test.dto;

// 회원정보
public class MemberDto {
	private int num;
	private String name;
	private String addr;
	
	public MemberDto() {}  //입력하고, 1. setter& getter 만들기 => MainClass07에서 호출! 가능

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	};
	
	
}
