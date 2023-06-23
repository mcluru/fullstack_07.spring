package com.lec;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lec.domain.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageInfoTest {
	
	PageInfo pageInfo = new PageInfo(250, 18, 10, "title", "");
	
	@Test
	public void Test() {
		pageInfo.pageTest();
		System.out.println(pageInfo.toString());
	}
	
}
