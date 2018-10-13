package com.alert.news.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is model class for organization campaign.
 *
 * @author Shivaji Pote
 */
@Getter
@Setter
@ToString
@Table("Organisation_Campaign")
public class OrganisationCampaign {

    /**
     * Campaign status
     */
    private static final String STATUS_NEW = "new";

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
    @NotNull(message = "Organisation name cannot be null")
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
    @NotNull(message = "Campaign description cannot be null")
    @Column("campaign_description")
    private String campaignDescription;

    /**
     * Campaign categories
     */
    @NotNull(message = "Tagged categories cannot be null")
    @Column("tagged_categories")
    private List<String> taggedCategories;

    /**
     * Status of the campaign
     */
    private String status;

    /**
     * Default constructor. This will initialize campaign if to 1.
     */
    public OrganisationCampaign() {
        this.campaignId = (long) counter.incrementAndGet();
        this.status = STATUS_NEW;
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
                                final String campainDescription, final List<String> taggedCategories) {
        this.campaignId = (long) counter.incrementAndGet();
        this.orgnisationName = orgnisationName;
        this.campaignTitle = campainTitle;
        this.campaignDescription = campainDescription;
        this.taggedCategories = taggedCategories;
        this.status = STATUS_NEW;
    }

}
