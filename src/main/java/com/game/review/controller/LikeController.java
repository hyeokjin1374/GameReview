package com.game.review.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.Service.ArticlesService;
import com.game.review.dto.ArticlesDTO;


@Controller
public class LikeController {
	
	@Autowired
	private ArticlesService artservice ;

    @ResponseBody
	@RequestMapping( value= "/updateLike", method = RequestMethod.POST )
	  public String updateLike(Long aNum, Long gNum , Long mNum) {
    	  System.out.println("리뷰번호는:" + aNum + " 게임넘버는: " + gNum+ " 맴버번호는: "+ mNum);
		
    	  int likeCheck = artservice.likeCheck(aNum, mNum);
		  System.out.println("좋아요카운터:" +likeCheck);
		  
		  if(likeCheck == 0) {
			  System.out.println(likeCheck);
			  //좋아요 처음 누름
			  artservice.insertLike(aNum, mNum); //테이블 삽입
 			  System.out.println("추천 ajax 인서트확인");
			  
			  artservice.updateLikeCheck(gNum, mNum, aNum); //like테이블 구분자 1
			  System.out.println("추천 ajax 좋아요 +1 확인 끝");
			 System.out.println(" 맴버번호는: "+mNum);
		  } else if(likeCheck == 1) {
			  artservice.updateLikeCheckCancel(gNum, mNum,aNum); 
			  System.out.println("추천 취소ajax 좋아요 -1 확인 끝");
              	
			  artservice.deleteLike(aNum, mNum);
			  System.out.println("추천 취소ajax 좋아요테이블삭제 확인 끝") ;
			  System.out.println(" 맴버번호는: "+mNum);
		  }
		  return Integer.toString(likeCheck); 
			  
	  }
	  
	
	
}
