/**
 * Copyright (C) 2015 mxHero Inc (mxhero@mxhero.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mxhero.plugin.cloudstorage.onedrive.api.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class Item.
 */
public class Item {

	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The e tag. */
	private String eTag;
	
	/** The c tag. */
	private String cTag;
	
	/** The created by. */
	private IdentitySet createdBy;
	
	/** The last modified by. */
	private IdentitySet lastModifiedBy;
	
	/** The created date time. */
	private String createdDateTime;
	
	/** The last modified date time. */
	private String lastModifiedDateTime;
	
	/** The size. */
	private Long size;
	
	/** The parent referenc. */
	private ItemReference parentReference;
	
	/** The children. */
	private List<Item> children;
	
	/** The web url. */
	private String webUrl;
	
	/** The description. */
	private String description;
	
	/** The folder. */
	private Folder folder;
	
	/** The file. */
	private File file;
	
	/** The file system info. */
	private FileSystemInfo fileSystemInfo;
	
	/** The image. */
	private Image image;
	
	/** The photo. */
	private Photo photo;
	
	/** The audio. */
	private Audio audio;
	
	/** The video. */
	private Video video;
	
	/** The location. */
	private Location location;
	
	/** The search result. */
	private SearchResult searchResult;
	
	/** The deleted. */
	private Deleted deleted;
	
	/** The download url. */
	@JsonProperty("@content.downloadUrl")
	private String downloadUrl;
	
	/** The thumbnails. */
	private List<ThumbnailSet> thumbnails;

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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the e tag.
	 *
	 * @return the e tag
	 */
	public String geteTag() {
		return eTag;
	}

	/**
	 * Sets the e tag.
	 *
	 * @param eTag the new e tag
	 */
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}

	/**
	 * Gets the c tag.
	 *
	 * @return the c tag
	 */
	public String getcTag() {
		return cTag;
	}

	/**
	 * Sets the c tag.
	 *
	 * @param cTag the new c tag
	 */
	public void setcTag(String cTag) {
		this.cTag = cTag;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public IdentitySet getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(IdentitySet createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the last modified by.
	 *
	 * @return the last modified by
	 */
	public IdentitySet getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * Sets the last modified by.
	 *
	 * @param lastModifiedBy the new last modified by
	 */
	public void setLastModifiedBy(IdentitySet lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * Gets the created date time.
	 *
	 * @return the created date time
	 */
	public String getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * Sets the created date time.
	 *
	 * @param createdDateTime the new created date time
	 */
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * Gets the last modified date time.
	 *
	 * @return the last modified date time
	 */
	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	/**
	 * Sets the last modified date time.
	 *
	 * @param lastModifiedDateTime the new last modified date time
	 */
	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * Gets the parent reference.
	 *
	 * @return the parent reference
	 */
	public ItemReference getParentReference() {
		return parentReference;
	}

	/**
	 * Sets the parent reference.
	 *
	 * @param parentReference the new parent reference
	 */
	public void setParentReference(ItemReference parentReference) {
		this.parentReference = parentReference;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<Item> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(List<Item> children) {
		this.children = children;
	}

	/**
	 * Gets the web url.
	 *
	 * @return the web url
	 */
	public String getWebUrl() {
		return webUrl;
	}

	/**
	 * Sets the web url.
	 *
	 * @param webUrl the new web url
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the folder.
	 *
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * Sets the folder.
	 *
	 * @param folder the new folder
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Gets the file system info.
	 *
	 * @return the file system info
	 */
	public FileSystemInfo getFileSystemInfo() {
		return fileSystemInfo;
	}

	/**
	 * Sets the file system info.
	 *
	 * @param fileSystemInfo the new file system info
	 */
	public void setFileSystemInfo(FileSystemInfo fileSystemInfo) {
		this.fileSystemInfo = fileSystemInfo;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	public Photo getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	/**
	 * Gets the audio.
	 *
	 * @return the audio
	 */
	public Audio getAudio() {
		return audio;
	}

	/**
	 * Sets the audio.
	 *
	 * @param audio the new audio
	 */
	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	/**
	 * Gets the video.
	 *
	 * @return the video
	 */
	public Video getVideo() {
		return video;
	}

	/**
	 * Sets the video.
	 *
	 * @param video the new video
	 */
	public void setVideo(Video video) {
		this.video = video;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the search result.
	 *
	 * @return the search result
	 */
	public SearchResult getSearchResult() {
		return searchResult;
	}

	/**
	 * Sets the search result.
	 *
	 * @param searchResult the new search result
	 */
	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Deleted getDeleted() {
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(Deleted deleted) {
		this.deleted = deleted;
	}

	/**
	 * Gets the download url.
	 *
	 * @return the download url
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}

	/**
	 * Sets the download url.
	 *
	 * @param downloadUrl the new download url
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * Gets the thumbnails.
	 *
	 * @return the thumbnails
	 */
	public List<ThumbnailSet> getThumbnails() {
		return thumbnails;
	}

	/**
	 * Sets the thumbnails.
	 *
	 * @param thumbnails the new thumbnails
	 */
	public void setThumbnails(List<ThumbnailSet> thumbnails) {
		this.thumbnails = thumbnails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
