package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Photo.
 */
public class Photo {

	/** The taken date time. */
	private String takenDateTime;
	
	/** The camera make. */
	private String cameraMake;
	
	/** The camera model. */
	private String cameraModel;
	
	/** The number. */
	private Double fNumber;
	
	/** The exposure denominator. */
	private Double exposureDenominator;
	
	/** The exposure numerator. */
	private Double exposureNumerator;
	
	/** The focal length. */
	private Double focalLength;
	
	/** The iso. */
	private Double iso;
	
	/**
	 * Gets the taken date time.
	 *
	 * @return the taken date time
	 */
	public String getTakenDateTime() {
		return takenDateTime;
	}

	/**
	 * Sets the taken date time.
	 *
	 * @param takenDateTime the new taken date time
	 */
	public void setTakenDateTime(String takenDateTime) {
		this.takenDateTime = takenDateTime;
	}

	/**
	 * Gets the camera make.
	 *
	 * @return the camera make
	 */
	public String getCameraMake() {
		return cameraMake;
	}

	/**
	 * Sets the camera make.
	 *
	 * @param cameraMake the new camera make
	 */
	public void setCameraMake(String cameraMake) {
		this.cameraMake = cameraMake;
	}

	/**
	 * Gets the camera model.
	 *
	 * @return the camera model
	 */
	public String getCameraModel() {
		return cameraModel;
	}

	/**
	 * Sets the camera model.
	 *
	 * @param cameraModel the new camera model
	 */
	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

	/**
	 * Gets the f number.
	 *
	 * @return the f number
	 */
	public Double getfNumber() {
		return fNumber;
	}

	/**
	 * Sets the f number.
	 *
	 * @param fNumber the new f number
	 */
	public void setfNumber(Double fNumber) {
		this.fNumber = fNumber;
	}

	/**
	 * Gets the exposure denominator.
	 *
	 * @return the exposure denominator
	 */
	public Double getExposureDenominator() {
		return exposureDenominator;
	}

	/**
	 * Sets the exposure denominator.
	 *
	 * @param exposureDenominator the new exposure denominator
	 */
	public void setExposureDenominator(Double exposureDenominator) {
		this.exposureDenominator = exposureDenominator;
	}

	/**
	 * Gets the exposure numerator.
	 *
	 * @return the exposure numerator
	 */
	public Double getExposureNumerator() {
		return exposureNumerator;
	}

	/**
	 * Sets the exposure numerator.
	 *
	 * @param exposureNumerator the new exposure numerator
	 */
	public void setExposureNumerator(Double exposureNumerator) {
		this.exposureNumerator = exposureNumerator;
	}

	/**
	 * Gets the focal length.
	 *
	 * @return the focal length
	 */
	public Double getFocalLength() {
		return focalLength;
	}

	/**
	 * Sets the focal length.
	 *
	 * @param focalLength the new focal length
	 */
	public void setFocalLength(Double focalLength) {
		this.focalLength = focalLength;
	}

	/**
	 * Gets the iso.
	 *
	 * @return the iso
	 */
	public Double getIso() {
		return iso;
	}

	/**
	 * Sets the iso.
	 *
	 * @param iso the new iso
	 */
	public void setIso(Double iso) {
		this.iso = iso;
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
