package com.vicheak.phoneshop.project.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vicheak.phoneshop.project.entity.Brand;
import com.vicheak.phoneshop.project.exception.ResourceNotFoundException;
import com.vicheak.phoneshop.project.repository.BrandRepository;
import com.vicheak.phoneshop.project.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;

	private BrandService brandService;

	@BeforeEach
	public void setUp() {
		brandService = new BrandServiceImpl(brandRepository);
	}

	// one test case
	/*
	 * @Test public void testCreate() { //given Brand brand = new Brand();
	 * brand.setId(1); brand.setName("Apple");
	 * 
	 * Brand brand2 = new Brand(); brand2.setName("Apple");
	 * 
	 * //when
	 * when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(brand);
	 * //when(brandRepository.save(brand2)).thenReturn(brand); Brand brandReturn =
	 * brandService.create(brand2);
	 * 
	 * //then assertEquals(1, brandReturn.getId()); assertEquals("Apple",
	 * brandReturn.getName()); }
	 */

	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");

		// when
		brandService.create(brand);

		// then
		verify(brandRepository, times(1)).save(brand);
		// verify(brandRepository, times(1)).delete(brand);
	}

	@Test
	public void testGetByIdSuccess() {
		// given
		Brand brand = new Brand();
		brand.setId(1);
		brand.setName("Apple");

		// when
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1);

		// then
		assertEquals(1, brandReturn.getId());
		// assertEquals("Appledara", brandReturn.getName());
		assertEquals("Apple", brandReturn.getName());
	}

	@Test
	public void testGetByIdThrow() {
		//given 
		
		//when 
		when(brandRepository.findById(2)).thenReturn(Optional.empty());
		//brandService.getById(2);
		//testing exception
		assertThatThrownBy(() -> brandService.getById(2))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Brand with id = 2 not found");
			//.hasMessage(String.format("%s with id = %d not found", "Brand", 2));
			//.hasMessageEndingWith("not found");
		
		//then
	}

}
