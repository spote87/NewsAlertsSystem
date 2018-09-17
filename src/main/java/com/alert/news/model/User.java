/**
 * 
 */
package com.alert.news.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Shivaji Pote
 *
 */
@Table
@Getter
@Setter
@ToString
public class User {
	
	@PrimaryKey("mobile_number")
	private Long mobileNumber;
	
	@Column("user_name")
	private String userName;
	
	@Column("subscription_categories")
	private String subscriptionCategories;
	
	
	/**
	 *  Default constructor
	 */
	public User() {
		super();
	}

	/**
	 * @param mobileNumber
	 * @param userName
	 * @param subscriptionCategories
	 */
	public User(Long mobileNumber, String userName, String subscriptionCategories) {
		super();
		this.mobileNumber = mobileNumber;
		this.userName = userName;
		this.subscriptionCategories = subscriptionCategories;
	}
	
}
