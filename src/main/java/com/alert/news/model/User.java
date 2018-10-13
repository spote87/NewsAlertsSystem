package com.alert.news.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Shivaji Pote
 */
@Table
@Getter
@Setter
@ToString
public class User {

    @NotNull(message = "Mobile number cannot be null")
    @PrimaryKey("mobile_number")
    private Long mobileNumber;

    @NotNull(message = "User name number cannot be null")
    @Column("user_name")
    private String userName;

    @NotNull(message = "Subscription Categories cannot be null")
    @Column("subscription_categories")
    private List<String> subscriptionCategories;


    /**
     * Default constructor
     */
    public User() {
        super();
    }

    /**
     * User parameterized constructor.
     *
     * @param mobileNumber           mobile number of user
     * @param userName               name of the user
     * @param subscriptionCategories subscription categories
     */
    public User(final Long mobileNumber, final String userName, final List<String> subscriptionCategories) {
        super();
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.subscriptionCategories = subscriptionCategories;
    }

}
