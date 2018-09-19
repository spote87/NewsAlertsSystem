/**
 * 
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
 * @author qx13ip
 *
 */
@Getter
@Setter
@ToString
@Table("Organisation_Campaign")
public class OrganisationCampaign {
	
	private static AtomicInteger counter;
	
	@PrimaryKey("id")
	private Long campaignId;
	
	@Column("organisation_name")
	private String orgnisationName;
	
	@Column("campaign_title")
	private String campaignTitle;
	
	@Column("campaign_description")
	private String campaignDescription;
	
	@Column("tagged_categories")
	private String taggedCategories;
	
	/**
	 * 
	 */
	public OrganisationCampaign() {
		this.campaignId = (long) counter.incrementAndGet();
	}

	/**
	 * @param orgnisationName
	 * @param campainTitle
	 * @param campainDescription
	 * @param taggedCategories
	 */
	public OrganisationCampaign(String orgnisationName, String campainTitle, String campainDescription,
			String taggedCategories) {
		this.campaignId = (long) counter.incrementAndGet();
		this.orgnisationName = orgnisationName;
		this.campaignTitle = campainTitle;
		this.campaignDescription = campainDescription;
		this.taggedCategories = taggedCategories;
	}
	
}
