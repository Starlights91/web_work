package com.example.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring08.service.GalleryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GalleryController {
	
	private final GalleryService service;
	
	@GetMapping("/gallery/list")
	public String list(Model model) {
		model.addAttribute("list", service.getAll());
		return "/gallery/list";
	}
	

}
