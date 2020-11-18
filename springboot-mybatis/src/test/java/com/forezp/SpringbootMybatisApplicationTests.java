package com.forezp;

import com.forezp.service.AccountService;
import com.forezp.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

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
		String result = articleService.findbyarticle_id(5).toString();
		System.out.println(result);
//		String a="article_id_1";
//		System.out.println(Integer.valueOf(a.split("_")[2]));
    }
    @Test
	public void Thread_test() throws InterruptedException {
		/**
		 * 启动线程的两种方式
		 */
		Thread test3 = new Thread(new function3());
		test3.setDaemon(true);//设定为守护进程
		test3.start();
		Thread test1 = new function1();
		test1.start();//启动线程
//		test1.sleep(1);
//		((function1) test1).running=false;
		test1.interrupt();//结束test1进程
	}

}

class function1 extends Thread{
	/**
	 * 继承Thread类，重写run方法
	 */
	public volatile boolean running =true;
	@Override
	public void run() {
		System.out.println("job启动"+Thread.currentThread() + LocalTime.now());
		Thread test2 = new Thread(new function2());
		test2.start();
		try {
			test2.join();//等待test2进程结束，再结束test1进程
		}
		catch (InterruptedException e){
			System.out.println("test1进程结束成功");
			test2.interrupt();//结束test2进程
		}
	}
}

class function2 implements Runnable{
	/**
	 * 实现Runnable接口
	 */
	@Override
	public void run() {
		for (int i=0;i<3000;i++){
		System.out.println("job2"+"当前的下标为:"+i+Thread.currentThread()+LocalTime.now());
		}
	}
}
class function3 implements Runnable{
	@Override
	public void run() {
		while (true){
			System.out.println("守护进程"+Thread.currentThread()+LocalTime.now());
		}
	}
}
