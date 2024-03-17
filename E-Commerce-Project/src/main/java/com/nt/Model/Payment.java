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
	    
	    
	    private String firstName;
	    private String lastname;
	    private String AddressLine1;
	    private String AddressLine2;
	    private Double Postcode_ZIP;
	    private String Town_City;
	    private Long Phone;
	    private String Email;
        private String Additionalinformation;
}
