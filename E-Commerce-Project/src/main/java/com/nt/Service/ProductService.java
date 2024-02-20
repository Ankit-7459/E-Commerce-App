package com.nt.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Model.ProductInfo;
import com.nt.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	 ProductRepository ProductRepo;
	
	public List<ProductInfo> FetchAllproduct(){
		return ProductRepo.findAll();
	}
	
	public void ProductAdd(ProductInfo product) {
		ProductRepo.save(product);
		
	}
	
	public void removeProductById(Long id) {
		
		ProductRepo.deleteById(id);
	}
	
	public Optional<ProductInfo> UpdateProductById(Long id) {
		
		return ProductRepo.findById(id);
	}
	public List<ProductInfo> getAllProductByCategoryId(Integer id){
		
		return ProductRepo.findAllByCategory_Id(id);
	}
}
