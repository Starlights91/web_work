package com.example.spring08.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryImageDto;
import com.example.spring08.repository.GalleryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {
	
	//GalleryMapper type 을 주입받아서 사용한다.
	private final GalleryMapper mapper;
	
	@Override
	public List<GalleryDto> getAll() {
		List<GalleryDto> list = mapper.getListWithImages();
		
		for(GalleryDto tmp : list) {
			List<GalleryImageDto> imageList = mapper.getImageList(tmp.getNum());
            tmp.setImageList(imageList);
		}
		return list;
	}
	

}
