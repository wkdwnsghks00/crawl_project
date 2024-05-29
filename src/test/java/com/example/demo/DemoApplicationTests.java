package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

class DemoApplicationTests {

	@Test
	void contextLoads() {
		// 스프링 컨텍스트가 제대로 로드되는지 확인
	}

}
