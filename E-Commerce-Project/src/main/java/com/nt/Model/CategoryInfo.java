package com.nt.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class CategoryInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="category_id")
	private Integer id;
	
	private String name;
	
}
