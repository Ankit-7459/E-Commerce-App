package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Model.ProductInfo;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {

}
