package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryImageDto;


@Mapper
public interface GalleryMapper {
	//이미지 목록도 포함하여 gallery 목록 반환
	@Select("""
		    SELECT num, title, writer, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
            FROM gallery
            ORDER BY num DESC
			
			""")
	public List<GalleryDto> getListWithImages();
	
	
	//이미지 목록
	@Select("""
            SELECT num, saveFileName, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
            FROM gallery_image
            WHERE galleryNum = #{galleryNum}
            ORDER BY num ASC			
			""")
	public List<GalleryImageDto> getImageList(int galleryNum);
	
	
	
	/////// below 5 need to edit.... 
	
	
	//저장할 글번호를 리턴해주는 메소드
	@Select("""
			SELECT board_seq.NEXTVAL AS num FROM dual
			""")
	public int getSequence();
	
	
	//게시글 저장
	@Insert("""
            INSERT INTO gallery (num, title, writer, content)
            VALUES (?, ?, ?, ?)			
			""")
	public boolean insert(GalleryDto dto);
	
	
	// 이미지 저장
	@Insert("""
            INSERT INTO gallery_image (num, galleryNum, saveFileName)
            VALUES (gallery_image_seq.NEXTVAL, ?, ?)			
			""")
	public boolean insertImage(GalleryImageDto dto);
	
	
    // 게시글 목록
	@Select("""
            SELECT num, title, writer, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
            FROM gallery
            ORDER BY num DESC			
			""")
    public List<GalleryDto> getList();
	
	
    // 상세보기
	@Select("""
            SELECT g.num, title, writer, content, 
        		TO_CHAR(g.createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt,
        		profileImage
            FROM gallery g
            JOIN users u ON writer = userName
            WHERE g.num = ?			
			""")
    public GalleryDto getData(int num);
	
}
