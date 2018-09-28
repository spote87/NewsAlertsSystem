/**
 * Copyrights: No copyrights
 */
package com.alert.news.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is model class for organization campaign.
 * 
 * @author Shivaji Pote
 *
 */
@Getter
@Setter
@ToString
@Table("Organisation_Campaign")
public class OrganisationCampaign {

	/**
	 * Auto increment counter field for id
	 */
	private static AtomicInteger counter = new AtomicInteger();

	/**
	 * campaign id
	 */
	@PrimaryKey("id")
	private Long campaignId;

	/**
	 * name of the organisation
	 */
	@Column("organisation_name")
	private String orgnisationName;

	/**
	 * title of campaign
	 */
	@Column("campaign_title")
	private String campaignTitle;

	/**
	 * description of the campaign
	 */
	@Column("campaign_description")
	private String campaignDescription;

	/**
	 * Campaign categories
	 */
	@Column("tagged_categories")
	private String taggedCategories;

	/**
	 * Default constructor. This will initialize campaign if to 1.
	 */
	public OrganisationCampaign() {
		this.campaignId = (long) counter.incrementAndGet();
	}

	/**
	 * Parameterized constructor. This will initialize campaign id to 1 and other
	 * fields to passed parameters.
	 * 
	 * @param orgnisationName    name of the organisation
	 * @param campainTitle       title of the campaign
	 * @param campainDescription description if the campaign
	 * @param taggedCategories   tagged categories
	 */
	public OrganisationCampaign(final String orgnisationName, final String campainTitle,
			final String campainDescription, final String taggedCategories) {
		this.campaignId = (long) counter.incrementAndGet();
		this.orgnisationName = orgnisationName;
		this.campaignTitle = campainTitle;
		this.campaignDescription = campainDescription;
		this.taggedCategories = taggedCategories;
	}

}
