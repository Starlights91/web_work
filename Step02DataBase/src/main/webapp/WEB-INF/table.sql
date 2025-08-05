
CREATE TABLE gallery(
	num NUMBER PRIMARY KEY,   -- 사진 1개 이상 여러개 올릴 수 있다
	title VARCHAR2(100) NOT NULL, -- 제목
	writer VARCHAR2(20) NOT NULL, -- 업로더
	content CLOB,				  -- 내용 설명
	createdAt DATE DEFAULT SYSDATE
);
-- gallery 테이블은 board_seq 를 공유하기

CREATE TABLE gallery_image(
	num NUMBER PRIMARY KEY,
	gallery_num NUMBER REFERENCES gallery(num),  -- 여기 gallery(num)1 이 되어야 함
	saveFileName VARCHAR2(100) NOT NULL,		 -- 업로드된 사진의 저장된 파일명. 만일 사진이 여러개라면 동일한 gallery_num 으로 여러개의 row 가 saveFileName 에 저장된다. 
	createdAt DATE DEFAULT SYSDATE
);

CREATE SEQUENCE gallery_image_seq;


CREATE TABLE comments(
	num NUMBER PRIMARY KEY, -- 댓글의 글번호 
	writer VARCHAR2(20) NOT NULL, -- 작성자 
	content VARCHAR2(100) NOT NULL, -- 내용
	targetWriter VARCHAR2(20) NOT NULL, -- 누구에게 작성한 댓글인지 
	groupNum NUMBER NOT NULL, -- 댓글의 그룹번호 
	parentNum NUMBER NOT NULL, -- 부모가 되는 원글의 글번호 
	deleted CHAR(3) DEFAULT 'no', -- 댓글을 삭제 했는지 여부 
	createdAt DATE DEFAULT SYSDATE -- 댓글 작성일 
);

CREATE SEQUENCE comments_seq;

CREATE TABLE board(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(20) NOT NULL,
	title VARCHAR2(50) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE
);

CREATE SEQUENCE board_seq; 


CREATE TABLE users(
	num NUMBER PRIMARY KEY, -- 회원의 고유번호
	userName VARCHAR2(20) UNIQUE, -- 아이디
	password VARCHAR2(100) NOT NULL, -- 비밀번호
	email VARCHAR2(50) UNIQUE, -- 이메일
	profileImage VARCHAR2(100), -- 프로필 이미지 정보 (처음 가입시 null)
	role VARCHAR2(10) DEFAULT 'ROLE_USER', -- 역할 USER(일반 사용자) | STAFF(직원) | ADMIN(최고권한관리자)
	updatedAt DATE, -- 수정 날짜
	createdAt DATE -- 가입 날짜
);

CREATE SEQUENCE users_seq;

CREATE TABLE member(
	num NUMBER PRIMARY KEY,
	name VARCHAR2(20),
	addr VARCHAR2(20)
);

CREATE SEQUENCE member_seq;

CREATE TABLE book(
	num NUMBER PRIMARY KEY,
	title VARCHAR2(20),
	author VARCHAR2(20),
	publisher VARCHAR2(20)
);

CREATE SEQUENCE book_seq;