package com.nt.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotEmpty
	@Column(nullable=false)
	
	private String firstName;
	
	private String lastName;
	@NotEmpty
	@Column(nullable=false ,unique=true )
	@Email(message= "{errors.invalid_email}")
	private String email;
	@NotEmpty
	private String password;
	
	@ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns=(@JoinColumn(name="user_ID",reference)))
	private list<Role> roles;
	
}

