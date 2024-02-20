package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Model.CategoryInfo;

public interface CategoryRepository extends JpaRepository<CategoryInfo,Integer > {

}
