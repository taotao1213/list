package com.wangtao.week2.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangtao.week2.domain.Goods;

@Controller
public class GoodsController {

	@Autowired
	RedisTemplate redisTemplate;
	
	
	@RequestMapping("findAll")
	public String getGoodsList(Model m,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "10")Integer pageSize) {
		
		List<Goods> list = redisTemplate.opsForList().range("good_list", (page-1)*pageSize, (page-1)*pageSize+pageSize-1);
		
		System.out.println(list);
		if(page!=1) {
			m.addAttribute("prePage", page-1);
		}else {
			m.addAttribute("prePage", 1);
		}
		
		m.addAttribute("nextPage", page+1);
		m.addAttribute("list", list);
		return "list";
		
	}
	
	
	@RequestMapping("findAllZset")
	public String getGoodsZset(Model m,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "10")Integer pageSize) {
		
		Long list = redisTemplate.opsForZSet().removeRange("good_zset", (page-1)*pageSize, (page-1)*pageSize+pageSize-1);
		m.addAttribute("list", list);
		if(page!=1) {
			m.addAttribute("prePage", page-1);
		}else {
			m.addAttribute("prePage", 1);
		}
		
		m.addAttribute("nextPage", page+1);
		return "zset";
		
	}
}
