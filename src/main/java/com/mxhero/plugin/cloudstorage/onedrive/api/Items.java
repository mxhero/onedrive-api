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
package com.mxhero.plugin.cloudstorage.onedrive.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.ApiException;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.AuthenticationException;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.Command;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandFactory;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandHandler;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.RefreshBusinessCommand;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Item;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ItemList;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ItemReference;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Permission;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ThumbnailSetList;

/**
 * The Class Items.
 */
public class Items {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(Items.class);
	
	/** The Constant RESERVED_CHARACTERS_PATTERN. */
	public static final String RESERVED_CHARACTERS_PATTERN = "[/\\*<>?:|#%\"]";
	
	/** The Constant DRIVE_ITEMS. */
	public static final String DRIVE_ITEMS = "/drive/items/";
	
	/** The Constant CHILDREN. */
	public static final String CHILDREN = "/children";
	
	/** The Constant SEARCH. */
	public static final String SEARCH = "/view.search";
	
	/** The Constant ROOT_ID. */
	public static final String DRIVE_ROOT = "/drive/root";
	
	/** The Constant CONTENT. */
	public static final String CONTENT = "/content";
	
	/** The Constant COPY. */
	public static final String COPY = "/action.copy";
	
	/** The command factory. */
	private CommandFactory commandFactory;
	
	/**
	 * The Enum ConflictBehavior.
	 */
	public enum ConflictBehavior{
		
		/** The rename. */
		rename,
		
		/** The replace. */
		replace,
		
		/** The fail. */
		fail
	}

	/**
	 * Instantiates a new items.
	 *
	 * @param commandFactory the command factory
	 */
	public Items(CommandFactory commandFactory){
		this.commandFactory=commandFactory;
	}
	
	/**
	 * Metadata by id.
	 *
	 * @param id the id
	 * @return the item
	 */
	public Item metadataById(String id){
		return this.metadataById(id,null);
	}
	
	/**
	 * Metadata by id.
	 *
	 * @param id the id
	 * @param odata the odata
	 * @return the item
	 */
	public Item metadataById(String id, Parameters odata){
		return this.itemGet(DRIVE_ITEMS+id,odata);
	}
	
	/**
	 * Metadata by path.
	 *
	 * @param path the path
	 * @return the item
	 */
	public Item metadataByPath(String path){
		return this.metadataByPath(path,null);
	}
	
	/**
	 * Metadata by path.
	 *
	 * @param path the path
	 * @param odata the odata
	 * @return the item
	 */
	public Item metadataByPath(String path, Parameters odata){
		return this.itemGet(DRIVE_ROOT+((StringUtils.isNotBlank(path))?":/"+cleanAndEncodePath(path):""),odata);
	}
	
	/**
	 * Children by id.
	 *
	 * @param id the id
	 * @return the item list
	 */
	public ItemList childrenById(String id) {
		return this.childrenById(id,null);
	}
	
	/**
	 * Children by id.
	 *
	 * @param id the id
	 * @param odata the odata
	 * @return the item list
	 */
	public ItemList childrenById(String id, Parameters odata){
		return this.itemListGet(DRIVE_ITEMS+id+CHILDREN, odata);
	}
	
	/**
	 * Children by path.
	 *
	 * @param path the path
	 * @return the item list
	 */
	public ItemList childrenByPath(String path){
		return this.childrenByPath(path,null);
	}
	
	/**
	 * Children by path.
	 *
	 * @param path the path
	 * @param odata the odata
	 * @return the item list
	 */
	public ItemList childrenByPath(String path, Parameters odata){
		return this.itemListGet(DRIVE_ROOT+((StringUtils.isNotBlank(path))?(":/"+cleanAndEncodePath(path)+":"):"")+CHILDREN, odata);
	}
	
	/**
	 * Search by path.
	 *
	 * @param path the path
	 * @param odata the odata
	 * @return the item list
	 */
	public ItemList searchByPath(String path, Parameters odata){
		return this.itemListGet(DRIVE_ROOT+((StringUtils.isNotBlank(path))?(":/"+cleanAndEncodePath(path)+":/"):"")+SEARCH, odata);		
	}
	
	/**
	 * Search by id.
	 *
	 * @param id the id
	 * @param odata the odata
	 * @return the item list
	 */
	public ItemList searchById(String id, Parameters odata){
		return this.itemListGet(DRIVE_ITEMS+id+SEARCH, odata);
	}
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	public Boolean deleteById(String id){
		return this.deleteItem(DRIVE_ITEMS+id);
	}
	
	/**
	 * Delete by path.
	 *
	 * @param path the path
	 * @return the boolean
	 */
	public Boolean deleteByPath(String path){
		return this.deleteItem(DRIVE_ROOT+":/"+cleanAndEncodePath(path));
	}
	
	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param item the item
	 * @return the item
	 */
	public Item updateById(String id, Item item){
		return this.updateItem(DRIVE_ITEMS+id, item);
	}
	
	/**
	 * Update by path.
	 *
	 * @param path the path
	 * @param item the item
	 * @return the item
	 */
	public Item updateByPath(String path, Item item){
		return this.updateItem(DRIVE_ROOT+":/"+cleanAndEncodePath(path), item);
	}
	
	/**
	 * Simple upload by path.
	 *
	 * @param path the path
	 * @param name the name
	 * @param inputStream the input stream
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	public Item simpleUploadByPath(String path, String name, InputStream inputStream, ConflictBehavior conflictBehavior){
		return simpleUploadStream(DRIVE_ROOT+":/"+cleanAndEncodePath(path)+"/"+cleanAndEncodeAndShortPath(name)+":"+CONTENT, inputStream, conflictBehavior);
	}
	
	/**
	 * Simple upload by path.
	 *
	 * @param path the path
	 * @param name the name
	 * @param file the file
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	public Item simpleUploadByPath(String path, String name, File file, ConflictBehavior conflictBehavior){
		return simpleUpload(DRIVE_ROOT+":/"+cleanAndEncodePath(path)+"/"+cleanAndEncodeAndShortPath(name)+":"+CONTENT, file, conflictBehavior);
	}
	
	/**
	 * Simple upload by id.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @param inputStream the input stream
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	public Item simpleUploadById(String parentId, String name, InputStream inputStream, ConflictBehavior conflictBehavior){
		return simpleUploadStream(DRIVE_ITEMS+parentId+CHILDREN+"/"+cleanAndEncodeAndShortPath(name)+CONTENT, inputStream, conflictBehavior);
	}
	
	/**
	 * Simple upload by id.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @param file the file
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	public Item simpleUploadById(String parentId, String name, File file, ConflictBehavior conflictBehavior){
		return simpleUpload(DRIVE_ITEMS+parentId+CHILDREN+"/"+cleanAndEncodeAndShortPath(name)+CONTENT, file, conflictBehavior);
	}

	/**
	 * Download url by id.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String downloadUrlById(String id){
		return getDownloadUrl(DRIVE_ITEMS+id+CONTENT);
	}
	
	/**
	 * Download url by path.
	 *
	 * @param path the path
	 * @return the string
	 */
	public String downloadUrlByPath(String path){
		return getDownloadUrl(DRIVE_ROOT+":/"+cleanAndEncodePath(path)+":"+CONTENT);
	}
	
	/**
	 * Copy by id.
	 *
	 * @param id the id
	 * @param parentReference the parent reference
	 * @return the string
	 */
	public String copyById(String id, ItemReference parentReference){
		return postCopy(DRIVE_ITEMS+id+COPY,parentReference,null);
	}

	/**
	 * Copy by id.
	 *
	 * @param id the id
	 * @param parentReference the parent reference
	 * @param name the name
	 * @return the string
	 */
	public String copyById(String id, ItemReference parentReference, String name){
		return postCopy(DRIVE_ITEMS+id+COPY,parentReference,name);
	}
	
	/**
	 * Copy by path.
	 *
	 * @param path the path
	 * @param parentReference the parent reference
	 * @return the string
	 */
	public String copyByPath(String path, ItemReference parentReference){
		return postCopy(DRIVE_ROOT+":/"+cleanAndEncodePath(path)+":"+COPY,parentReference,null);
	}
	
	/**
	 * Copy by path.
	 *
	 * @param path the path
	 * @param parentReference the parent reference
	 * @param name the name
	 * @return the string
	 */
	public String copyByPath(String path, ItemReference parentReference, String name){
		return postCopy(DRIVE_ROOT+":/"+cleanAndEncodePath(path)+":"+COPY,parentReference,name);
	}
	
	/**
	 * Move by id.
	 *
	 * @param id the id
	 * @param parentReference the parent reference
	 * @return the item
	 */
	public Item moveById(String id, ItemReference parentReference){
		return patchMove(DRIVE_ITEMS+id, parentReference);
	}
	
	/**
	 * Move by path.
	 *
	 * @param path the path
	 * @param parentReference the parent reference
	 * @return the item
	 */
	public Item moveByPath(String path, ItemReference parentReference){
		return patchMove(DRIVE_ROOT+":/"+cleanAndEncodePath(path), parentReference);
	}

	/**
	 * Creates the link by id.
	 *
	 * @param id the id
	 * @param type the type
	 * @return the permission
	 */
	public Permission createLinkById(String id, String type){
		return postCreateLink(DRIVE_ITEMS+id+"/action.createLink", type);
	}
	
	/**
	 * Creates the link by path.
	 *
	 * @param path the path
	 * @param type the type
	 * @return the permission
	 */
	public Permission createLinkByPath(String path, String type){
		return postCreateLink(DRIVE_ROOT+":/"+cleanAndEncodePath(path)+":/action.createLink", type);
	}
	
	/**
	 * Thumbnails.
	 *
	 * @param id the id
	 * @return the thumbnail set list
	 */
	public ThumbnailSetList thumbnails(String id){
		return this.thumbnails(id, null);
	}
	
	/**
	 * Post create link.
	 *
	 * @param path the path
	 * @param type the type
	 * @return the permission
	 */
	private Permission postCreateLink(final String path, final String type){
		final Command<Permission> command = this.commandFactory.create();
		return command.excecute(new CommandHandler<Permission>() {
			
			@Override
			public Permission response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_CREATED
							|| response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Permission.class);
					}
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response body",e);
				}
				throw new IllegalArgumentException("error reading response with code "+response.getStatusLine().getStatusCode());
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPost httpPost = new HttpPost(command.baseUrl()+path);
				httpPost.setHeader("Content-type", "application/json");
				try{
					Map<String,Object> body = new HashMap<>();
					body.put("type", type);
					httpPost.setEntity(new StringEntity(OneDrive.JACKSON.writeValueAsString(body), "UTF-8"));
					return httpPost;
				} catch (Exception e) {
					throw new IllegalArgumentException("error sending path for "+httpPost, e);
				}
			}
		});
	}
	
	/**
	 * Thumbnails.
	 *
	 * @param id the id
	 * @param parameters the parameters
	 * @return the thumbnail set list
	 */
	public ThumbnailSetList thumbnails(final String id, final Parameters parameters){
		final Command<ThumbnailSetList> command = this.commandFactory.create();
		return command.excecute(new CommandHandler<ThumbnailSetList>() {
			
			@Override
			public ThumbnailSetList response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), ThumbnailSetList.class);
					}
					if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try {
					URIBuilder builder = new URIBuilder(command.baseUrl()+DRIVE_ITEMS+id+"/thumbnails");
					if(parameters!=null){
						builder.addParameters(parameters.list());
					}
					return new HttpGet(builder.build().toString());
				} catch (Exception e) {
					throw new IllegalArgumentException("couldn't create query with "+parameters,e);
				}
			}
		});
	}
	
	/**
	 * Patch move.
	 *
	 * @param path the path
	 * @param parentReference the parent reference
	 * @return the item
	 */
	private Item patchMove(final String path, final ItemReference parentReference){
		final Command<Item> command = this.commandFactory.create();
		return command.excecute(new CommandHandler<Item>() {
			
			@Override
			public Item response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Item.class);
					}
					if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPatch httpPatch = new HttpPatch(command.baseUrl()+path);
				httpPatch.setHeader("Content-type", "application/json");
				StringEntity entity;
				try {
					Map<String, Object> body = new HashMap<>();
					body.put("parentReference", parentReference);
					entity = new StringEntity(OneDrive.JACKSON.writeValueAsString(body), "UTF-8");
				} catch (Exception e) {
					throw new IllegalArgumentException("error sending path for "+path, e);
				}
				httpPatch.setEntity(entity);
				return httpPatch;
			}
		});
	}
	
	/**
	 * Post copy.
	 *
	 * @param path the path
	 * @param parentReference the parent reference
	 * @param name the name
	 * @return the string
	 */
	private String postCopy(final String path, final ItemReference parentReference, final String name){
		final Command<String> command = this.commandFactory.create();
		return command.excecute(new CommandHandler<String>() {
			
			@Override
			public String response(HttpResponse response) {
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_ACCEPTED){
					return response.getFirstHeader("Location").getValue();
				}
				if(response.getStatusLine().getStatusCode()>399){
					throw new ApiException(response);
				}
				throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPost httpPost = new HttpPost(command.baseUrl()+path);
				httpPost.setHeader("Content-type", "application/json");
				httpPost.setHeader("Prefer","respond-async");
				try{
					Map<String,Object> body = new HashMap<>();
					if(StringUtils.isNotBlank(parentReference.getPath())
							&& !parentReference.getPath().startsWith("/drive/root:/")){
						parentReference.setPath(DRIVE_ROOT+":/"+parentReference.getPath());
					}
					body.put("parentReference", parentReference);
					if(StringUtils.isNotBlank(name)){
						body.put("name",name.replaceAll(RESERVED_CHARACTERS_PATTERN, " ").trim());
					}
					httpPost.setEntity(new StringEntity(OneDrive.JACKSON.writeValueAsString(body), "UTF-8"));
					return httpPost;
				} catch (Exception e) {
					throw new IllegalArgumentException("error sending path for "+httpPost, e);
				}
			}
		});
	}
	
	
	/**
	 * Gets the download url.
	 *
	 * @param path the path
	 * @return the download url
	 */
	private String getDownloadUrl(final String path){
		final Command<String> command = this.commandFactory.create();
		return command.excecute(new CommandHandler<String>() {
			
			@Override
			public String response(HttpResponse response) {
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_MOVED_TEMPORARILY){
					return response.getFirstHeader("Location").getValue();
				}
				if(response.getStatusLine().getStatusCode()>399){
					throw new ApiException(response);
				}
				throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
			}
			
			@Override
			public HttpUriRequest request() {
				return new HttpGet(command.baseUrl()+path);
			}
		});
	}
	
	/**
	 * Simple upload.
	 *
	 * @param path the path
	 * @param file the file
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	private Item simpleUpload(final String path, final File file, final ConflictBehavior conflictBehavior){
		final Command<Item> command = this.commandFactory.create();
		validateImplementation(command, file.getName());
		return command.excecute(new CommandHandler<Item>() {
			
			@Override
			public Item response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_CREATED
							|| response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Item.class);
					}
					if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			/* (non-Javadoc)
			 * @see com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandHandler#request()
			 */
			@Override
			public HttpUriRequest request() {
				try{
					URIBuilder builder = new URIBuilder(command.baseUrl()+path);
					builder.addParameter("@name.conflictBehavior", conflictBehavior.name());
					HttpPut httpPut = new HttpPut(builder.build().toString());
					httpPut.setHeader("Content-Type", "text/plain");
					httpPut.setEntity(new FileEntity(file, ContentType.TEXT_PLAIN));
					return httpPut;
				} catch (Exception e) {
					throw new IllegalArgumentException("couldn't create query",e);
				}
			}
		});
	}
	
	private void validateImplementation(Command<Item> command, String fileName) {
		if(command instanceof RefreshBusinessCommand){
			String regex = "ashx|asmx|json|soap|svc|xamlx";
			Pattern compile = Pattern.compile(regex);
			if(compile.matcher(fileName).find()){				
				throw new AuthenticationException("The following extensions are not allowed to upload for Business API. "+regex);
			}
		}		
	}

	/**
	 * Creates the folder.
	 *
	 * @param parentId the parent id
	 * @param name the name
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	public Item createFolder(final String parentId, final String name, final ConflictBehavior conflictBehavior){
		final Command<Item> command = commandFactory.create();
		return command.excecute(new CommandHandler<Item>() {
			
			@Override
			public Item response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_CREATED){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Item.class);
					}
					if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				String postUrl = command.baseUrl();
				if(StringUtils.isBlank(parentId)|| "root".equalsIgnoreCase(parentId)){
					postUrl+=DRIVE_ROOT+CHILDREN;
				}else{
					postUrl+=DRIVE_ITEMS+parentId+CHILDREN;
				}
				HttpPost httpPost = new HttpPost(postUrl);
				httpPost.setHeader("Content-type", "application/json");
				Map<String, Object> item = new HashMap<>();
				item.put("name", cleanAndShortName(name).replaceAll("\\.*$", ""));
				item.put("folder", new HashMap<>());
				item.put("@name.conflictBehavior", conflictBehavior.name());
				try {
					httpPost.setEntity(new StringEntity(OneDrive.JACKSON.writeValueAsString(item), "UTF-8"));
				} catch (Exception e) {
					throw new IllegalArgumentException("error sending path for "+httpPost, e);
				}
				return httpPost;
			}
		});
	}
	
	/**
	 * Update item.
	 *
	 * @param path the path
	 * @param item the item
	 * @return the item
	 */
	private Item updateItem(final String path, final Item item){
		final Command<Item> command = commandFactory.create();
		return command.excecute(new CommandHandler<Item>() {
			
			@Override
			public Item response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Item.class);
					}
					if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPatch httpPatch = new HttpPatch(command.baseUrl()+path);
				httpPatch.setHeader("Content-type", "application/json");
				StringEntity entity;
				try {
					entity = new StringEntity(OneDrive.JACKSON.writeValueAsString(item), "UTF-8");
				} catch (Exception e) {
					throw new IllegalArgumentException("error sending path for "+path, e);
				}
				httpPatch.setEntity(entity);
				return httpPatch;
			}
		});
	}
	
	/**
	 * Delete item.
	 *
	 * @param path the path
	 * @return the boolean
	 */
	private Boolean deleteItem(final String path){
		final Command<Boolean> command = commandFactory.create();
		return command.excecute(new CommandHandler<Boolean>() {
			
			@Override
			public Boolean response(HttpResponse response) {
				if(response.getStatusLine().getStatusCode()==204){
					return Boolean.TRUE;
				}
				return Boolean.FALSE;
			}
			
			@Override
			public HttpUriRequest request() {
				return new HttpDelete(command.baseUrl()+path);
			}
		});
	}
	
	/**
	 * Item list get.
	 *
	 * @param path the path
	 * @param parameters the parameters
	 * @return the item list
	 */
	private ItemList itemListGet(final String path, final Parameters parameters){
		final Command<ItemList> command = commandFactory.create();
		return command.excecute(new CommandHandler<ItemList>() {
			
			@Override
			public ItemList response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_NOT_FOUND){
						return null;
					}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), ItemList.class);
					}else if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try {
					URIBuilder builder = new URIBuilder(command.baseUrl()+path);
					if(parameters!=null){
						builder.addParameters(parameters.list());
					}
					return new HttpGet(builder.build().toString());
				} catch (Exception e) {
					throw new IllegalArgumentException("couldn't create query with "+parameters,e);
				}
			}
		});
	}
	
	/**
	 * Item get.
	 *
	 * @param path the path
	 * @param parameters the parameters
	 * @return the item
	 */
	private Item itemGet(final String path, final Parameters parameters){
		final Command<Item> command = commandFactory.create();
		logger.debug("called item by ", path);
		
		return command.excecute(new CommandHandler<Item>() {
			
			@Override
			public Item response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_NOT_FOUND){
						return null;
					}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						String value = EntityUtils.toString(response.getEntity());
						return OneDrive.JACKSON.readValue(value, Item.class);
					}else if(response.getStatusLine().getStatusCode()>399){
						throw new ApiException(response);
					}
					throw new IllegalArgumentException("error reading response body with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try {
					URIBuilder builder = new URIBuilder(command.baseUrl()+path);
					if(parameters!=null){
						builder.addParameters(parameters.list());
					}
					return new HttpGet(builder.build().toString());
				} catch (Exception e) {
					throw new IllegalArgumentException("couldn't create query with "+parameters,e);
				}
			}
		});
	}
	
	/**
	 * Clean and encode path.
	 *
	 * @param path the path
	 * @return the string
	 */
	public static String cleanAndEncodePath(String path){
		if(path!=null){
			String cleanPath="";
			for(String segment : path.split("/")){
				try {
					cleanPath=cleanPath+URLEncoder.encode(segment.replaceAll(RESERVED_CHARACTERS_PATTERN, " ").trim(),"UTF-8")+"/";
				} catch (UnsupportedEncodingException e) {
				}
			}
			return cleanPath.substring(0,cleanPath.length()-1);
		}
		return null;
	}
	
	/**
	 * Clean and short name.
	 *
	 * @param path the path
	 * @return the string
	 */
	public static String cleanAndShortName(String path){
		if(path!=null){
			String processedPath = path.replaceAll(RESERVED_CHARACTERS_PATTERN, " ").trim();
			if(processedPath.length()>Integer.parseInt(ApiEnviroment.maxFileAndFolderLenght.getValue())){
				processedPath = processedPath.substring(0, Integer.parseInt(ApiEnviroment.maxFileAndFolderLenght.getValue()));
			}
			return processedPath;
		}
		return null;
	}
	
	/**
	 * Clean and encode and short path.
	 *
	 * @param path the path
	 * @return the string
	 */
	public static String cleanAndEncodeAndShortPath(String path){
		if(path!=null){
			String processedPath = cleanAndEncodePath(path);
			if(processedPath.length()>Integer.parseInt(ApiEnviroment.maxFileAndFolderLenght.getValue())){
				processedPath = processedPath.substring(0, Integer.parseInt(ApiEnviroment.maxFileAndFolderLenght.getValue()));
			}
			return processedPath;
		}
		return null;
	}
	
	/**
	 * Simple upload stream.
	 *
	 * @param path the path
	 * @param inputStream the input stream
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	private Item simpleUploadStream(final String path, final InputStream inputStream, final ConflictBehavior conflictBehavior){
		File tmpFile = null;
		try{
			tmpFile = Files.createTempFile(new File(ApiEnviroment.tempUploadFolder.getValue()).toPath(), "odtmp",".data").toFile();
			FileUtils.copyInputStreamToFile(inputStream, tmpFile);
			return simpleUpload(path, tmpFile, conflictBehavior);
		} catch (IOException e) {
			throw new IllegalArgumentException("couldnt read or store inputstream");
		}
		finally{
			if(tmpFile!=null){
				FileUtils.deleteQuietly(tmpFile);
			}
		}
	}
}
