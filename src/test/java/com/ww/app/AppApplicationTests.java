package com.ww.app;

import com.ww.app.service.IAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
    IAuthService menuService;

	@Test
	void contextLoads() {
		System.out.println(dataSource.getClass());
	}

}
