package com.alert.news.repository;

import com.alert.news.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shivaji Pote
 *
 */
@Repository
public interface UserRepository extends CassandraRepository<User, Long>{

}
