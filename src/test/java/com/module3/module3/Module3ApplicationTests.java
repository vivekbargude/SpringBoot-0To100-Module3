package com.module3.module3;

import com.module3.module3.entity.ProductEntity;
import com.module3.module3.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class Module3ApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();

		ProductEntity saved = productRepository.save(productEntity);
		System.out.println(saved);

	}

	@Test
	void getRepository() {
//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0,0,0));

//		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanAndPriceGreaterThan(12, BigDecimal.valueOf(123.45));
		List<ProductEntity> entities = productRepository.findByTitleContaining("C");
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> productEntity = productRepository.findByTitleAndPrice("Wireless Mouse", BigDecimal.valueOf(1200.00));
		System.out.println(productEntity);
	}

}
