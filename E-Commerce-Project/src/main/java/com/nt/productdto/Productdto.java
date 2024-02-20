package com.nt.productdto;

import lombok.Data;


@Data
public class Productdto {
   private Long  id;
   private String name;
   private Integer categoryId;
   private Double   price;
   private Double   weight;
   private String   description;
   private String   imageName;
}
