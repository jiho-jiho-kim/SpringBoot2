package com.myweb.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.basic.command.NoticeBoardVo;
import com.myweb.basic.noticeBoard.NoticeBoardService;

@Controller
@RequestMapping("/noticeBoard")
public class NoticeBoardController {
	
	@Autowired
	@Qualifier("noticeBoardService")
	NoticeBoardService noticeBoardService;
	
	@GetMapping("/noticeBoardReg")
	public String boardReg () {
		return "noticeBoard/noticeBoardReg";
	}
	
	@GetMapping("/noticeBoardListAll")
	public String boardListAll (Model model) {
		List <NoticeBoardVo> list = noticeBoardService.getListMe();
		model.addAttribute("list", list);
		
		return "noticeBoard/noticeBoardListAll";
	}
	
	@GetMapping("/noticeBoardListMe")
	public String boardListMe (Model model) {
		List <NoticeBoardVo> list = noticeBoardService.getListMe();
		model.addAttribute("list", list);
		
		return "noticeBoard/noticeBoardListMe";
	}
	
	@GetMapping("/noticeBoardDetail")
	public String boardDetail () {
		return "noticeBoard/noticeBoardDetail";
	}
	
	@GetMapping("/noticeBoardModify")
	public String boardModify() {
		return "noticeBoard/noticeBoardModify";
	}
	
	//등록
	@PostMapping("/noticeBoardForm")
	public String noticeBoardForm(@Valid NoticeBoardVo vo, Errors errors, Model model) {
		if(errors.hasErrors()) {
			List <FieldError> list = errors.getFieldErrors();
			for(FieldError err : list ) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "빈칸을 채워주세요");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			model.addAttribute("vo", vo);
			return "noticeBoard/noticeBoardReg";
		}
		
		boolean result = noticeBoardService.noticeRegist(vo);
		return "redirect:/noticeBoard/noticeBoardListMe";
		
	}
	
	
}
