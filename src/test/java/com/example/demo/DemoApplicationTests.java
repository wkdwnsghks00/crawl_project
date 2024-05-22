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
@ActiveProfiles("test")
class DemoApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
		// 스프링 컨텍스트가 제대로 로드되는지 확인
	}

	@Test
	void testGetProductsByCategoryId() {
		// Given
		int categoryId = 1;  // 테스트용 카테고리 ID를 설정합니다.

		// When
		List<Product> products = productService.getProductsByCategoryId(categoryId);

		// Then
		assertThat(products).isNotNull();
		assertThat(products.size()).isGreaterThan(0);  // 적어도 하나 이상의 제품이 있어야 합니다.

		// 첫 번째 제품의 속성을 검증합니다.
		Product product = products.get(0);
		assertThat(product.getCategoryId()).isEqualTo(categoryId);
		assertThat(product.getTitle()).isNotEmpty();
	}
}
