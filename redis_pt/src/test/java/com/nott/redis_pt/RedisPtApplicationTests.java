package com.nott.redis_pt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class RedisPtApplicationTests {

	@Test
	public void contextLoads() {
		Properties initPro = new Properties(System.getProperties());
		System.out.println(initPro.getProperty("file.encoding"));
		String str = new String("asdf 啊手动阀手动阀 a75&%");
		try {
			System.out.println(new String(str.getBytes("GBK")));
			System.out.println(new String(str.getBytes("UTF-8")));
			System.out.println(new String(str.getBytes("ISO8859-1")));
			System.out.println(new String(str.getBytes("gb2312")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
