package com.alert.news.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.alert.news.model.OrganisationCampaign;

/**
 * This is repository interface which inherits {@link CassandraRepository} and
 * gives access DB manipulation methods.
 *
 * @author Shivaji Pote
 */
@Repository
public interface OrganisationCampaignRepository extends CassandraRepository<OrganisationCampaign, Long> {

    /**
     * This method retrieves all campaigns whose status is <em>new</em>. Please note
     * that use of allow filtering in PROD may not be a good idea.
     *
     * @param status status of the campaign
     * @return List of {@link OrganisationCampaign}
     */
    @Query("select * from organisation_campaign where status=?0 allow filtering")
    List<OrganisationCampaign> findByStatus(final String status);

    /**
     * This method updates status with specified one, of specified campaign id.
     *
     * @param id     id of organisation campaign
     * @param status status to update
     */
    @Query("update organisation_campaign set status=?1 where id=?0 ")
    void updateStatus(final Long id, final String status);
}
