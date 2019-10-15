package com.wangtao.week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.common.utils.StreamUtil;
import com.wangtao.common.utils.StringUtil;
import com.wangtao.week2.domain.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class RedisTest {

	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	public void RedisList() {
		File file = new File("src/test/resources/init.txt");
		
		try {
			InputStream is = new FileInputStream(file);
			List<String> readTextForLine = StreamUtil.readTextForLine(is);
			
			for (String string : readTextForLine) {
				Goods g = new Goods();
				String[] split = string.split("==");
				if(StringUtil.isNumber(split[0])) {
					g.setId(Integer.valueOf(split[0]));
				}
				if(StringUtil.hasText(split[1])) {
					g.setName(split[1]);
				}
				if(StringUtil.hasText(split[2])) {
					BigDecimal substring = BigDecimal.valueOf(Double.parseDouble(split[2].substring(1)));
					if(StringUtil.isNumber(split[2].substring(1))) {
						g.setPrice(substring);
					}
				}
				if(StringUtil.hasText(split[3])) {
					if(StringUtil.isNumber(split[3].substring(0,split[3].length()-1))) {
						g.setBaifen(Integer.valueOf(split[3].substring(0, split[3].length()-1)));
					}
				}
				
				redisTemplate.opsForList().leftPush("good_list", g);
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void RedisZset() {
		File file = new File("src/test/resources/aaa.txt");
		try {
			InputStream is = new FileInputStream(file);
			List<String> readTextForLine = StreamUtil.readTextForLine(is);
			
			for (String string : readTextForLine) {
				System.out.println(string);
				Goods g = new Goods();
				String[] split = string.split("==");
				if(StringUtil.isNumber(split[0])) {							
					g.setId(Integer.valueOf(split[0]));
				}
				if(StringUtil.hasText(split[1])) {
					g.setName(split[1]);
				}
				if(StringUtil.hasText(split[2])) {
					BigDecimal substring = BigDecimal.valueOf(Double.parseDouble(split[2].substring(1)));
					if(StringUtil.isNumber(split[2].substring(1))){
						g.setPrice(substring);
					}	
				}
				if(StringUtil.hasText(split[3])) {
					if(StringUtil.isNumber(split[3].substring(0,split[3].length()-1))){
						g.setBaifen(Integer.valueOf(split[3].substring(0,split[3].length()-1)));
					}
				}
				
				redisTemplate.opsForZSet().add("goods_zset", g, Integer.valueOf(split[3].substring(0,split[3].length()-1)));			
			}		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
