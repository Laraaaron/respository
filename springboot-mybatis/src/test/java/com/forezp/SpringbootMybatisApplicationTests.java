package com.forezp;

import com.forezp.service.AccountService;
import com.forezp.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
	@Autowired
	ArticleService articleService;
	@Autowired
	AccountService accountService;
	@Test
	public void contextLoads() {
		String result=articleService.findarticle().toString();
//		String result = accountService.matchinformation("412332441@qq.com").toString();
		System.out.println(result);
    }

}
