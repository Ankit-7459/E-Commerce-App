package com.nt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	    private String cardnumber;
	    private String cardholder;
	    private String cvv;
}
