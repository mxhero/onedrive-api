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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mxhero.plugin.cloudstorage.onedrive.api.Items.ConflictBehavior;
import com.mxhero.plugin.cloudstorage.onedrive.api.command.ApiException;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Drive;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Item;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ItemList;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ItemReference;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.Permission;
import com.mxhero.plugin.cloudstorage.onedrive.api.model.ThumbnailSetList;

public class OneDriveBusinessTest {
	
	private static BusinessTestEnviroment testEnviroment;
	private OneDrive api;
	
	@BeforeClass
	public static void beforeClass() throws IOException{
		if(testEnviroment==null){
			try{
				testEnviroment=OneDrive.JACKSON.readValue(new URL(System.getProperty("test.enviroment.business.json.url")), BusinessTestEnviroment.class);
			}catch(Exception e){
				fail(" couldnt read BusinessCredential fro url in SystemProperty test.enviroment.business.json.url with value "+System.getProperty("test.enviroment.json.url"));
				e.printStackTrace();
			}
		}
	}
	
	@Before
	public void before(){
		assumeNotNull(testEnviroment);
		api = createApi();
	}
	
	private File getFile(){
		return new File(Thread.currentThread().getContextClassLoader().getResource("logo_96x96.png").getFile());
	}
	
	private OneDrive createApi(){
		return new OneDrive.Builder()
				.businessCredential(testEnviroment.getCredential())
				.application(testEnviroment.getApplication())
				.build();
	}
	
	@Test
	public void testDriveUserDefault(){
		Drive drive = api.drives().userDefault();
		assertNotNull(drive);
		System.out.println(drive.toString());
	}

	@Test
	public void testDriveById(){
		try {
			api.drives().get("b!GuF7GLZVSECYDcI0TtAwICr_E1mM2xhLsCNaup9ktWgI5iYvyQbSTbYvei8qjBnS");
			fail();
		} catch (NotImplementedException e) {
		}
	}
	
	@Test
	public void listRootChildrens(){
		ItemList itemList = api.items().childrenByPath("");
		assertNotNull(itemList);
		System.out.println(itemList.toString());
	}
	
	@Test
	public void listChildrenByPath(){
		ItemList itemList = api.items().childrenByPath("Documents");
		assertNotNull(itemList);
		assertTrue(itemList.getValue().size()>0);
		System.out.println(itemList.toString());
	}
	
	@Test
	public void getItemById(){
		Item item = api.items().metadataById("01WHV54E2PY7KVF2FPRJHZHA6QXQIXMJVH");
		assertNotNull(item);
		System.out.println(item.toString());
	}
	
	@Test
	public void getItemByIdWithParameters(){
		Items items = api.items();
		Item item =items.metadataById("01WHV54E2PY7KVF2FPRJHZHA6QXQIXMJVH", new Parameters().select("id,name"));
		assertNotNull(item.getName());
		assertNull(item.getcTag());
		System.out.println(item.toString());		
	}

	@Test
	public void getItemByPath(){
		Items items = api.items();
		Item item = items.metadataByPath("demo");
		assertNotNull(item);
		System.out.println(item.toString());
		item = items.metadataByPath("demo/saveAndShare");
		assertNotNull(item);
		System.out.println(item.toString());
	}

	@Test
	public void searchByPath(){
		ItemList searchResult = api.items().searchByPath("", new Parameters().query("asset1.png"));	
		assertTrue(searchResult.getValue().size()>0);
		System.out.println(searchResult);
	}
	
	@Test
	public void thumbnails(){
		ThumbnailSetList list = api.items().thumbnails("01WHV54E7EEYHNL7TERRHJBZKESEO3C7GI", null);	
		System.out.println(list);
		assertTrue(list.getValue().size()>0);
	}
	
	@Test
	public void simpleUpload(){
		Items items = api.items();
		Item simpleUploadByPath = items.simpleUploadByPath("Documents"
				, "uploadtest.png"
				, getFile()
				, ConflictBehavior.replace);
		assertNotNull(simpleUploadByPath);
		Item simpleUploadById = items.simpleUploadById(items.metadataByPath("Documents").getId()
				, "uploadtest.png"
				, getFile()
				, ConflictBehavior.replace);
		assertNotNull(simpleUploadById);
	}
	
	@Test
	public void delete() throws FileNotFoundException{
		Items items = api.items();
		items.simpleUploadByPath("Documents"
				, "delete.png"
				, getFile()
				, ConflictBehavior.replace);
		items.deleteByPath("Documents/delete.png");
		assertNull(items.metadataByPath("Documents/delete.png"));
	}
	
	@Test
	public void apiException() throws FileNotFoundException{
		Items items = api.items();
		items.deleteByPath("Documents/delete.png");
		items.simpleUploadByPath("Documents"
				, "delete.png"
				, getFile()
				, ConflictBehavior.replace);
		try{
		items.simpleUploadByPath("Documents"
				, "delete.png"
				, getFile()
				, ConflictBehavior.fail);
		fail();
		}catch(ApiException e){
			assertNotNull(e.getReasonPhrase());
			assertNotNull(e.getStatusCode());
			assertNotNull(e.getError());
			System.out.println("error found "+e.getError().toString());
		}
	}
	
	@Test
	public void copy(){
		Items items = api.items();
		items.deleteByPath("Documents/copy_1.png");
		items.deleteByPath("Documents/copy.png");
		Item parent = items.metadataByPath("Documents");
		Item copyItem = items.simpleUploadByPath("Documents"
				, "copy.png"
				, getFile()
				, ConflictBehavior.replace);
		items.copyById(copyItem.getId(), 
				new ItemReference.Builder().id(parent.getId()).build()
				, "copy_1.png");
		long timestamp = System.currentTimeMillis();
		while(items.metadataByPath("Documents/copy_1.png")== null && (timestamp+5000)<System.currentTimeMillis()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		assertTrue(items.deleteByPath("Documents/copy_1.png"));
		assertTrue(items.deleteByPath("Documents/copy.png"));
	}
	
	@Test
	public void move(){
		Items items = api.items();
		items.deleteByPath("Documents/move.png");
		items.deleteByPath("demo/move.png");
		Item parent = items.metadataByPath("Documents");
		Item moveItem = items.simpleUploadByPath("demo"
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
		assertTrue(items.deleteByPath("Documents/move.png"));
		assertFalse(items.deleteByPath("demo/move.png"));
	}
	
	@Test
	public void createFolder(){
		Items items = api.items();
		System.out.println(items.deleteByPath("folder"));
		System.out.println(items.createFolder(items.createFolder("root", "folder", ConflictBehavior.rename).getId(), "subfolder", ConflictBehavior.fail));
		System.out.println(items.deleteByPath("folder"));
		Item createFolder = items.createFolder("root", "marcelo@gmail.com", ConflictBehavior.rename);
		assertNotNull(createFolder);
	}
	
	@Test
	public void createLink(){
		Items items = api.items();
		items.deleteByPath("Documents/link.png");
		Item item = items.simpleUploadByPath("Documents"
				, "link.png"
				, getFile()
				, ConflictBehavior.replace);
		Permission permission = items.createLinkById(item.getId(), "view");
		assertNotNull(permission);
		System.out.println(permission);
	}
	
}
