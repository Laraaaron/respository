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
//		String result=articleService.hotarticle().toString();
//		String result = accountService.matchinformation("412332441@qq.com").toString();
//		String result =articleService.findbyarticle_title("快速").toString();
		String result = articleService.findbyarticle_id(1).toString();
		System.out.println(result);
		String a="abc";
		System.out.println(Integer.valueOf(null));
    }

}
