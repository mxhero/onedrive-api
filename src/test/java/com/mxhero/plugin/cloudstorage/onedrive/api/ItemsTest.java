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

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ItemsTest {

	@Test
	public void testCleanAndEncodePath(){
		assertEquals("Documents/one%20test/test%20this()",Items.cleanAndEncodePath(ReservedCharactersType.sharepoint_2013,"Documents/one test/ test this()"));
	}
	
	@Test
	public void testCleanAndEncodePathStartingWithDotRemoved(){
		assertEquals("Documents/one%20test/test%20this()",Items.cleanAndEncodePath(ReservedCharactersType.sharepoint_2013,"Documents/.one test/.test this()"));
		assertEquals("Documents/one%20test/test%20this()",Items.cleanAndEncodePath(ReservedCharactersType.sharepoint_2013,"Documents/~one test/.test this()"));
	}
	
	@Test
	public void testCleanAndEncodePathNotAllowedCharactersRemoved(){
		assertEquals("Documents/one%20%20test/test%20%20%20this()",Items.cleanAndEncodePath(ReservedCharactersType.sharepoint_2013,"Documents/.one &test/.test{} this()~"));
	}
	
	@Test
	public void testCleanAndEncodePath2() throws UnsupportedEncodingException{
		if(isWindows()){
			return;
		}
		assertEquals("Sharp%E2%80%99s%20Questions_20170630.pdf",Items.cleanAndEncodePath(ReservedCharactersType.sharepoint_2013,"Sharp’s Questions_20170630.pdf"));
	}
	
	 private boolean isWindows() {
	      return System.getProperty("os.name").startsWith("Windows");
	    }
}
