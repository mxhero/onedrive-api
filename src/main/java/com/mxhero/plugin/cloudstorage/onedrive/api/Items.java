package com.mxhero.plugin.cloudstorage.onedrive.api;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxhero.plugin.cloudstorage.onedrive.api.command.Command;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandFactory;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.CommandHandler;
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
	
	/** The Constant DRIVE_ITEMS. */
	public static final String DRIVE_ITEMS = "/drive/items/";
	
	/** The Constant CHILDREN. */
	public static final String CHILDREN = "/children";
	
	/** The Constant SEARCH. */
	public static final String SEARCH = "/view.search";
	
	/** The Constant ROOT_ID. */
	public static final String DRIVE_ROOT = "/drive/root";
	
	public static final String CONTENT = "/content";
	
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
		return this.itemGet(DRIVE_ROOT+((StringUtils.isNotBlank(path))?":/"+path:""),odata);
	}
	
	/**
	 * Children by id.
	 *
	 * @param id the id
	 * @return the item list
	 */
	public ItemList childrenById(String id){
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
		return this.itemListGet(DRIVE_ROOT+((StringUtils.isNotBlank(path))?(":/"+path+":"):"")+CHILDREN, odata);
	}
	
	/**
	 * Search by path.
	 *
	 * @param path the path
	 * @param odata the odata
	 * @return the item list
	 */
	public ItemList searchByPath(String path, Parameters odata){
		return this.itemListGet(DRIVE_ROOT+((StringUtils.isNotBlank(path))?(":/"+path+":/"):"")+SEARCH, odata);		
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
		return this.deleteItem(DRIVE_ROOT+":/"+path);
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
		return this.updateItem(DRIVE_ROOT+":/"+path, item);
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
		return simpleUpload(DRIVE_ROOT+":/"+path+"/"+name+":"+CONTENT, inputStream, conflictBehavior);
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
		return simpleUpload(DRIVE_ITEMS+parentId+CHILDREN+"/"+name+":"+CONTENT, inputStream, conflictBehavior);
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
		return getDownloadUrl(DRIVE_ROOT+":/"+path+":"+CONTENT);
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
		return postCopy(DRIVE_ROOT+":/"+path+":"+COPY,parentReference,null);
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
		return postCopy(DRIVE_ROOT+":/"+path+":"+COPY,parentReference,name);
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
		return patchMove(DRIVE_ROOT+":/"+path, parentReference);
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
		return postCreateLink(DRIVE_ROOT+":/"+path+":/action.createLink", type);
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
				return null;
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPost httpPost = new HttpPost(ApiEnviroment.baseUrl.getValue()+path);
				httpPost.setHeader("Content-type", "application/json");
				try{
					Map<String,Object> body = new HashMap<>();
					body.put("type", type);
					httpPost.setEntity(new StringEntity(OneDrive.JACKSON.writeValueAsString(body), "UTF-8"));
					return httpPost;
				} catch (Exception e) {
					throw new RuntimeException("error sending path for "+httpPost, e);
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
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try {
					URIBuilder builder = new URIBuilder(ApiEnviroment.baseUrl.getValue()+DRIVE_ITEMS+id+"/thumbnails");
					if(parameters!=null){
						builder.addParameters(parameters.list());
					}
					return new HttpGet(builder.build().toString());
				} catch (Exception e) {
					throw new RuntimeException("couldn't create query with "+parameters,e);
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
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPatch httpPatch = new HttpPatch(ApiEnviroment.baseUrl.getValue()+path);
				httpPatch.setHeader("Content-type", "application/json");
				StringEntity entity;
				try {
					Map<String, Object> body = new HashMap<>();
					body.put("parentReference", parentReference);
					entity = new StringEntity(OneDrive.JACKSON.writeValueAsString(body), "UTF-8");
				} catch (Exception e) {
					throw new RuntimeException("error sending path for "+path, e);
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
				throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPost httpPost = new HttpPost(ApiEnviroment.baseUrl.getValue()+path);
				httpPost.setHeader("Content-type", "application/json");
				try{
					Map<String,Object> body = new HashMap<>();
					if(StringUtils.isNotBlank(parentReference.getPath())
							&& !parentReference.getPath().startsWith("/drive/root:/")){
						parentReference.setPath(DRIVE_ROOT+":/"+parentReference.getPath());
					}
					body.put("parentReference", parentReference);
					if(StringUtils.isNotBlank(name)){
						body.put("name",name);
					}
					httpPost.setEntity(new StringEntity(OneDrive.JACKSON.writeValueAsString(body), "UTF-8"));
					return httpPost;
				} catch (Exception e) {
					throw new RuntimeException("error sending path for "+httpPost, e);
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
				throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
			}
			
			@Override
			public HttpUriRequest request() {
				return new HttpGet(ApiEnviroment.baseUrl.getValue()+path);
			}
		});
	}
	
	/**
	 * Simple upload.
	 *
	 * @param path the path
	 * @param inputStream the input stream
	 * @param conflictBehavior the conflict behavior
	 * @return the item
	 */
	private Item simpleUpload(final String path, final InputStream inputStream, final ConflictBehavior conflictBehavior){
		final Command<Item> command = this.commandFactory.create();
		return command.excecute(new CommandHandler<Item>() {
			
			@Override
			public Item response(HttpResponse response) {
				try {
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_CREATED
							|| response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), Item.class);
					}
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try{
					URIBuilder builder = new URIBuilder(ApiEnviroment.baseUrl.getValue()+path);
					builder.addParameter("@name.conflictBehavior", conflictBehavior.name());
					HttpPut httpPut = new HttpPut(builder.build().toString());
					httpPut.setHeader("Content-Type", "text/plain");
					httpPut.setEntity(new InputStreamEntity(inputStream));
					return httpPut;
				} catch (Exception e) {
					throw new RuntimeException("couldn't create query",e);
				}
			}
		});
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
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				String postUrl = ApiEnviroment.baseUrl.getValue();
				if(StringUtils.isBlank(parentId)|| "root".equalsIgnoreCase(parentId)){
					postUrl+=DRIVE_ROOT+CHILDREN;
				}else{
					postUrl+=DRIVE_ITEMS+parentId+CHILDREN;
				}
				HttpPost httpPost = new HttpPost(postUrl);
				httpPost.setHeader("Content-type", "application/json");
				Map<String, Object> item = new HashMap<>();
				item.put("name", name);
				item.put("folder", new HashMap<>());
				item.put("@name.conflictBehavior", conflictBehavior.name());
				try {
					httpPost.setEntity(new StringEntity(OneDrive.JACKSON.writeValueAsString(item), "UTF-8"));
				} catch (Exception e) {
					throw new RuntimeException("error sending path for "+httpPost, e);
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
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				HttpPatch httpPatch = new HttpPatch(ApiEnviroment.baseUrl.getValue()+path);
				httpPatch.setHeader("Content-type", "application/json");
				StringEntity entity;
				try {
					entity = new StringEntity(OneDrive.JACKSON.writeValueAsString(item), "UTF-8");
				} catch (Exception e) {
					throw new RuntimeException("error sending path for "+path, e);
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
				return new HttpDelete(ApiEnviroment.baseUrl.getValue()+path);
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
					if(response.getStatusLine().getStatusCode()==404){
						return null;
					}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						return OneDrive.JACKSON.readValue(EntityUtils.toString(response.getEntity()), ItemList.class);
					}
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try {
					URIBuilder builder = new URIBuilder(ApiEnviroment.baseUrl.getValue()+path);
					if(parameters!=null){
						builder.addParameters(parameters.list());
					}
					return new HttpGet(builder.build().toString());
				} catch (Exception e) {
					throw new RuntimeException("couldn't create query with "+parameters,e);
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
					if(response.getStatusLine().getStatusCode()==404){
						return null;
					}else if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						String value = EntityUtils.toString(response.getEntity());
						return OneDrive.JACKSON.readValue(value, Item.class);
					}
					throw new RuntimeException("error reading response with code "+response.getStatusLine().getStatusCode());
				} catch (Exception e) {
					throw new IllegalArgumentException("error reading response",e);
				}
			}
			
			@Override
			public HttpUriRequest request() {
				try {
					URIBuilder builder = new URIBuilder(ApiEnviroment.baseUrl.getValue()+path);
					if(parameters!=null){
						builder.addParameters(parameters.list());
					}
					return new HttpGet(builder.build().toString());
				} catch (Exception e) {
					throw new RuntimeException("couldn't create query with "+parameters,e);
				}
			}
		});
	}
}
