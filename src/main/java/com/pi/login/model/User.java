package com.pi.login.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Getter
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,length=100,unique=true,name="USER_NAME")
	private String username;
	
	@Column(nullable = false,length=100,name="USER_ID")
	private String userId;
	
	@Column(nullable = false,length=100,name="USER_PS")
	private String password;
	
	@Column(nullable = false,length=50,name="USER_EML")
	private String email;
	
	@Column(nullable = false,length=50,name="USER_TELNO")
	private String phone;

	@Enumerated(EnumType.STRING) @Setter
	private RoleType role;
	
	private String oauth;
	
	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public User(Long id, String username, String userId, String password, String email, String phone, RoleType role, String oauth, Timestamp createDate) {
		this.id = id;
		this.username = username;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.oauth = oauth;
		this.createDate = createDate;
	}
}
