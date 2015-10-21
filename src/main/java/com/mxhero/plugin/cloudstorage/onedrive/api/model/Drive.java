package com.mxhero.plugin.cloudstorage.onedrive.api.model;

/*
 * #%L
 * com.mxhero.plugin.cloudstorage.onedrive
 * %%
 * Copyright (C) 2013 - 2015 mxHero Inc.
 * %%
 * MXHERO END USER LICENSE AGREEMENT
 * 
 * IMPORTANT-READ CAREFULLY:  BY DOWNLOADING, INSTALLING, OR USING THE SOFTWARE, YOU (THE INDIVIDUAL OR LEGAL ENTITY) AGREE TO BE BOUND BY THE TERMS OF THIS END USER LICENSE AGREEMENT (EULA).  IF YOU DO NOT AGREE TO THE TERMS OF THIS EULA, YOU MUST NOT DOWNLOAD, INSTALL, OR USE THE SOFTWARE, AND YOU MUST DELETE OR RETURN THE UNUSED SOFTWARE TO THE VENDOR FROM WHICH YOU ACQUIRED IT WITHIN THIRTY (30) DAYS AND REQUEST A REFUND OF THE LICENSE FEE, IF ANY, THAT YOU PAID FOR THE SOFTWARE.
 * 
 * READ LICENSE.txt file to see details of this agreement.
 * #L%
 */


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Drive.
 */
public class Drive{
	
	/** The id. */
	private String id;
	
	/** The drive type. */
	private String driveType;
	
	/** The quota. */
	private Quota quota;
	
	/** The owner. */
	private IdentitySet owner;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the drive type.
	 *
	 * @return the drive type
	 */
	public String getDriveType() {
		return driveType;
	}

	/**
	 * Sets the drive type.
	 *
	 * @param driveType the new drive type
	 */
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	/**
	 * Gets the quota.
	 *
	 * @return the quota
	 */
	public Quota getQuota() {
		return quota;
	}

	/**
	 * Sets the quota.
	 *
	 * @param quota the new quota
	 */
	public void setQuota(Quota quota) {
		this.quota = quota;
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public IdentitySet getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(IdentitySet owner) {
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
