package com.game.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.Service.RepliesService;
import com.game.review.command.RepliesCommand;
import com.game.review.dto.RepliesDTO;

@Controller
public class RepliesController {

	@Autowired
	private RepliesService rservice;
	
	

	@RequestMapping(value = "/rep/{aNum}", method = RequestMethod.GET)
	public String rep(@ModelAttribute("repData") RepliesCommand rcmd, Errors errros,
			@PathVariable("aNum") Long aNum, Model model) {
		RepliesDTO rdto = new RepliesDTO();
		System.out.println("리뷰 넘버는: " + aNum);
		rdto.setaNum(aNum);	
		
		List<RepliesDTO> rep = rservice.selectRe(rdto);
		model.addAttribute("rep1", rep);
	
		return "/rep";

	}

	@RequestMapping(value = "/rep/{aNum}", method = RequestMethod.POST)
	public String rep2(@ModelAttribute("repData") RepliesCommand rcmd, Errors errros, @PathVariable("aNum") int aNum,
			Model model) {
		System.out.println("댓글컨트롤러 포스트");

		rservice.insetRe(rcmd);
		return "redirect:/rep/{aNum}";
	}
	
	@RequestMapping(value = "/repupdate/{aNum}/{reNum}", method = RequestMethod.GET)
	public String update(@ModelAttribute("repupdateData") RepliesCommand rcmd, Errors errros,
			@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum ,
			Model model) {
		
		RepliesDTO rdto = new RepliesDTO();
		System.out.println("리뷰 넘버는: " + aNum);
		rdto.setaNum(aNum);

		List<RepliesDTO> rep = rservice.selectRe(rdto);
		model.addAttribute("rep", rep);
		System.out.println("댓글업데이트 확인" );


		return "/repupdate";

	}
	@RequestMapping(value = "/repupdate/{aNum}/{reNum}", method = RequestMethod.POST)
	public String update2(@ModelAttribute("repupdateData") RepliesCommand rcmd, Errors errros,
			@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum ,
			Model model) {
		RepliesDTO rdto = new RepliesDTO();
		rdto.setmNum((long) 1);
		rdto.setaNum(aNum);
		rdto.setReNum(reNum);
		rdto.setReContent(rcmd.getReContent());
		rservice.updateRe(rdto);


		return "redirect:/rep/{aNum}";

	}
	
	@RequestMapping(value = "/repdel/{aNum}/{reNum}", method = RequestMethod.GET)
	public String delete(@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum ,
			Model model) {
		
		RepliesDTO rdto = new RepliesDTO();
		rdto.setaNum(aNum);
		rdto.setmNum((long) 1);
		List<RepliesDTO> rep = rservice.selectdel(rdto);
		model.addAttribute("rep", rep);

		System.out.println("댓글삭제 확인" );
		return "/repdel";

	}
	
	@RequestMapping(value = "/repdel/{aNum}/{reNum}", method = RequestMethod.POST)
	public String delete2(@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum) {
		System.out.println("댓글삭제 POST 확인" );

		RepliesDTO rdto2 = new RepliesDTO() ;
		rdto2.setReNum(reNum); 
		rdto2.setaNum(aNum);  //리뷰넘버
		rdto2.setmNum((long) 1);
		
		rservice.deleteRe(rdto2);

		return  "redirect:/rep/{aNum}";

	}

	
	
	
}