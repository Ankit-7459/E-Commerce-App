package com.nt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Payment_details")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	    private String cardnumber;
	    private String cardholder;
	    private String cvv;
	    
	    
	   
}
