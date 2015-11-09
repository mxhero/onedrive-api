package com.mxhero.plugin.cloudstorage.onedrive.api;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mxhero.plugin.cloudstorage.onedrive.api.Items.ConflictBehavior;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Drive;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Item;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ItemList;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ItemReference;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Permission;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ThumbnailSetList;


public class OneDriveTest {
	
	private TestEnviroment testEnviroment;
	
	@Before
	public void beforeClass() throws IOException{
		if(testEnviroment==null){
			try{
				testEnviroment=OneDrive.JACKSON.readValue(new URL(System.getProperty("test.enviroment.json.url")), TestEnviroment.class);
			}catch(Exception e){
				System.out.println(" couldnt read TestEnviroment fro url in SystemProperty test.enviroment.json.url with value "+System.getProperty("test.enviroment.json.url"));
				e.printStackTrace();
			}
		}
	}
	
	private File getFile(){
		return new File(Thread.currentThread().getContextClassLoader().getResource("logo_96x96.png").getFile());
	}
	
	private OneDrive createApi(){
		return new OneDrive.Builder()
				.credential(testEnviroment.getCredential())
				.application(testEnviroment.getApplication())
				.build();
	}
	
	@Ignore
	@Test
	public void testRedeem() {
		assumeNotNull(testEnviroment);

		Map<String, Object> response = OneDrive.redeem("YOUR_REDEEM_CODE"
				, testEnviroment.getApplication().getClientId()
				, testEnviroment.getApplication().getRedirectUri()
				, testEnviroment.getApplication().getClientSecret());
		assertNotNull(response);
		System.out.println(response.toString());
	}
	
	@Ignore
	@Test
	public void emails() {
		assumeNotNull(testEnviroment);

		Map<String, Object> response = OneDrive.emails(testEnviroment.getCredential().getAccessToken());
		assertNotNull(response);
		System.out.println(response.toString());
	}
	
	@Test
	public void testDriveUserDefault(){
		assumeNotNull(testEnviroment);

		Drive drive = createApi().drives().userDefault();
		assertNotNull(drive);
		System.out.println(drive.toString());
	}
	
	@Test
	public void testDriveById(){
		assumeNotNull(testEnviroment);

		Drive drive = createApi().drives().get("bf667a0c62207823");
		assertNotNull(drive);
		System.out.println(drive.toString());
	}
	
	@Test
	public void listRootChildrens(){
		assumeNotNull(testEnviroment);

		ItemList itemList = createApi().items().childrenByPath("");
		assertNotNull(itemList);
		System.out.println(itemList.toString());
	}
	
	@Test
	public void listChildrenByPath(){
		assumeNotNull(testEnviroment);

		ItemList itemList = createApi().items().childrenByPath("Documents");
		assertNotNull(itemList);
		assertTrue(itemList.getValue().size()>0);
		System.out.println(itemList.toString());
	}
	
	@Test
	public void getItemById(){
		assumeNotNull(testEnviroment);

		Item item =createApi().items().metadataById("BF667A0C62207823!105");
		assertNotNull(item);
		System.out.println(item.toString());
	}
	
	@Test
	public void getItemByIdWithParameters(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		Item item =items.metadataById("BF667A0C62207823!105", new Parameters().select("id,name"));
		assertNotNull(item.getName());
		assertNull(item.getcTag());
		System.out.println(item.toString());		
		item=items.metadataById("BF667A0C62207823!105", new Parameters().expand("children(select=id,name)").select("id,name,children"));
		assertNotNull(item.getChildren());
		System.out.println(item.toString());	
	}

	@Test
	public void getItemByPath(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		Item item = items.metadataByPath("Documents/richard_shapiro_001_1_1_1.pst");
		assertNotNull(item);
		System.out.println(item.toString());
		item = items.metadataByPath("Documents/one test");
		assertNotNull(item);
		System.out.println(item.toString());
	}

	@Test
	public void searchByPath(){
		assumeNotNull(testEnviroment);

		ItemList searchResult = createApi().items().searchByPath("Pictures", new Parameters().query("uploadtest.png"));	
		assertTrue(searchResult.getApproximateCount()>0);
		assertTrue(searchResult.getValue().size()>0);
		System.out.println(searchResult);
	}
	
	@Test
	public void thumbnails(){
		assumeNotNull(testEnviroment);

		ThumbnailSetList list = createApi().items().thumbnails("BF667A0C62207823!115", null);	
		assertTrue(list.getValue().size()>0);
		System.out.println(list);
	}
	
	@Test
	public void simpleUpload(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		items.simpleUploadByPath("Pictures"
				, "uploadtest.png"
				, getFile()
				, ConflictBehavior.replace);
		items.simpleUploadById(items.metadataByPath("Pictures").getId()
				, "uploadtest.png"
				, getFile()
				, ConflictBehavior.replace);
	}
	
	@Test
	public void delete() throws FileNotFoundException{
		assumeNotNull(testEnviroment);
		Items items = createApi().items();
		items.simpleUploadByPath("Pictures"
				, "delete.png"
				, getFile()
				, ConflictBehavior.replace);
		items.deleteByPath("Pictures/delete.png");
		assertNull(items.metadataByPath("Pictures/delete.png"));
	}
	
	@Test
	public void copy(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		items.deleteByPath("Pictures/copy_1.png");
		items.deleteByPath("Pictures/copy.png");
		Item parent = items.metadataByPath("Pictures");
		Item copyItem = items.simpleUploadByPath("Pictures"
				, "copy.png"
				, getFile()
				, ConflictBehavior.replace);
		items.copyById(copyItem.getId(), 
				new ItemReference.Builder().id(parent.getId()).build()
				, "copy_1.png");
		long timestamp = System.currentTimeMillis();
		while(items.metadataByPath("Pictures/copy_1.png")== null && (timestamp+5000)<System.currentTimeMillis()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		items.deleteByPath("Pictures/copy_1.png");
		items.deleteByPath("Pictures/copy.png");
	}
	
	@Test
	public void move(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		items.deleteByPath("Documents/move.png");
		items.deleteByPath("Pictures/move.png");
		Item parent = items.metadataByPath("Documents");
		Item moveItem = items.simpleUploadByPath("Pictures"
				, "move.png"
				, getFile()
				, ConflictBehavior.replace);
		items.moveById(moveItem.getId(), 
				new ItemReference.Builder().id(parent.getId()).build());
		long timestamp = System.currentTimeMillis();
		while(items.metadataByPath("Documents/move.png")== null && (timestamp+5000)<System.currentTimeMillis()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		items.deleteByPath("Documents/move.png");
		items.deleteByPath("Pictures/move.png");
	}
	
	@Test
	public void createFolder(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		System.out.println(items.deleteByPath("folder"));
		System.out.println(items.createFolder(items.createFolder("root", "folder", ConflictBehavior.rename).getId(), "subfolder", ConflictBehavior.fail));
		System.out.println(items.deleteByPath("folder"));
	}
	
	@Test
	public void createLink(){
		assumeNotNull(testEnviroment);

		Items items = createApi().items();
		items.deleteByPath("Pictures/link.png");
		Item item = items.simpleUploadByPath("Pictures"
				, "link.png"
				, getFile()
				, ConflictBehavior.replace);
		Permission permission = items.createLinkById(item.getId(), "view");
		System.out.println(permission);
	}
	
}
