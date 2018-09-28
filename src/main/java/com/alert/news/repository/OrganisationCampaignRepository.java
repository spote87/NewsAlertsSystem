/**
 * Copyright: No copyright
 */
package com.alert.news.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.alert.news.model.OrganisationCampaign;

/**
 * This is repository interface which inherits {@link CassandraRepository} and
 * gives access DB manipulation methods.
 * 
 * @author Shivaji Pote
 *
 */
@Repository
public interface OrganisationCampaignRepository extends CassandraRepository<OrganisationCampaign, Long> {

}
