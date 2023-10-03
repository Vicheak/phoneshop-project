package com.vicheak.phoneshop.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.vicheak.phoneshop.project.entity.Brand;

//class under test
@DataJpaTest
public class BrandRepositoryTest {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Test
	public void testFindByNameLike() {
		//given 
		Brand brand = new Brand();
		brand.setName("Apple");
		
		Brand brand2 = new Brand(); 
		brand2.setName("Samsung");
		
		brandRepository.save(brand);
		brandRepository.save(brand2);
		
		//when 
		List<Brand> brands = brandRepository.findByNameLike("%A%");
		
		//then (excepted)
		assertEquals(1, brands.size());
		assertEquals("Apple", brands.get(0).getName());
		assertEquals(3, brands.get(0).getId());
		
		/*System.out.println("test find by name like");
		brands.stream()
			.forEach(b -> System.out.println(b));*/
	}
	
	@Test
	public void testFindByNameContaining() {
		//given 
		Brand brand = new Brand();
		brand.setName("Oppo");
		
		Brand brand2 = new Brand(); 
		brand2.setName("Vivo");
		
		brandRepository.save(brand);
		brandRepository.save(brand2);
		
		//when 
		List<Brand> brands = brandRepository.findByNameContaining("Oppo"); 
		
		//then
		assertEquals(1, brands.size());
		assertEquals("Oppo", brands.get(0).getName());
		assertEquals(1, brands.get(0).getId());
		
		/*System.out.println("test find by name containing");
		brands.stream()
			.forEach(b -> System.out.println(b));*/
	}
	
}
