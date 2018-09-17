/**
 * 
 */
package com.alert.news.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.alert.news.model.User;

/**
 * @author Shivaji Pote
 *
 */
@Repository
public interface UserRepository extends CassandraRepository<User, Long>{

}
