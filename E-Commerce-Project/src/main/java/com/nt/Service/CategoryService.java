package com.nt.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Model.CategoryInfo;
import com.nt.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private  CategoryRepository catrepo;
	
	
	public List< CategoryInfo> ShowAllCategory() {
		
		return catrepo.findAll();
	}
	
	public  CategoryInfo addCategory( CategoryInfo category) {
		
		return catrepo.save(category);
	}

	public void removeCategoryById(Integer id) {
		
		catrepo.deleteById(id);
	}
	
	public Optional<CategoryInfo> UpdateCategory(Integer id) {
		
		return catrepo.findById(id);
	
	}
}
