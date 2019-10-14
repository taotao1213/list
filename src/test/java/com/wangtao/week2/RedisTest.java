package com.wangtao.week2;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.common.utils.StreamUtil;
import com.wangtao.week2.domain.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class RedisTest {

	@Autowired
	RedisTemplate redisTemplate;
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void RedisList() {
		String string = StreamUtil.readTextFile(new File("E:\\aaa.txt"));
		String[] split = string.split("==");
		
		ArrayList<Goods> list = new ArrayList<Goods>();
		
		for (int i = 1; i <= 106; i++) {
			Goods good = new Goods();
			good.setId(i);
			good.setName("苹果");
			good.setPrice(i+1.0);
			good.setBaifen("80");
			list.add(good);
		}
		
		Long goods = redisTemplate.opsForList().leftPush("good_list", list);
		
		
//		for (String string2 : split) {
//			
//			redisTemplate.opsForList().leftPush("good_list", string2);
//		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void RedisZset() {
		String string = StreamUtil.readTextFile(new File("E:\\aaa.txt"));
		String[] split = string.split("==");
		
		for (int i = 1; i <= 106; i++) {
			Goods good = new Goods();
			good.setId(i);
			good.setName("苹果");
			good.setPrice(i+1.0);
			good.setBaifen("80");
			redisTemplate.opsForZSet().add("good_zset", good,i);
		}
		
//		for (String string2 : split) {
//			
//			redisTemplate.opsForZSet().add("good_zset", string2);
//		}
	}
}
