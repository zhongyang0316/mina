package com.zy.mina.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinaMessageApplicationTests {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void contextLoads() {
		this.logger.info("只是测试。。。。");
	}
	
}
