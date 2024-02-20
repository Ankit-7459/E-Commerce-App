package com.nt.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class ProductInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
   private Long  id;
   
   private String name;
   
   @ManyToOne(fetch= FetchType.LAZY)
   
   @JoinColumn(name="category_id",referencedColumnName="category_id")
   private CategoryInfo category;
   private Double   price;
   private Double   weight;
   private String   description;
   private String   imageName;
}
