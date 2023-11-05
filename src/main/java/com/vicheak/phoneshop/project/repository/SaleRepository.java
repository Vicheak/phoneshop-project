package com.vicheak.phoneshop.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vicheak.phoneshop.project.entity.Sale;
import com.vicheak.phoneshop.project.projection.ProductSold;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(value = "SELECT\r\n"
			+ "    p.id AS \"productId\",\r\n"
			+ "    p.name AS \"productName\",\r\n"
			+ "    sum(sd.unit) AS \"unit\",\r\n"
			+ "    sum(sd.unit * sd.sold_amount) AS \"totalAmount\"\r\n"
			+ "FROM sale_details sd INNER JOIN sales s\r\n"
			+ "ON sd.sale_id = s.sale_id\r\n"
			+ "INNER JOIN products p\r\n"
			+ "ON sd.product_id = p.id\r\n"
			+ "WHERE date(s.sold_date)\r\n"
			+ "    BETWEEN :startDate AND :endDate\r\n"
			+ "GROUP BY p.id, p.name",
			nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
	
}
