package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class SearchResult.
 */
public class SearchResult {

	/** The on click telemetry url. */
	private String onClickTelemetryUrl;

	/**
	 * Gets the on click telemetry url.
	 *
	 * @return the on click telemetry url
	 */
	public String getOnClickTelemetryUrl() {
		return onClickTelemetryUrl;
	}

	/**
	 * Sets the on click telemetry url.
	 *
	 * @param onClickTelemetryUrl the new on click telemetry url
	 */
	public void setOnClickTelemetryUrl(String onClickTelemetryUrl) {
		this.onClickTelemetryUrl = onClickTelemetryUrl;
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
