package com.nt.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Model.ProductInfo;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {

	List<ProductInfo> findAllByCategory_Id(Integer id);
}
