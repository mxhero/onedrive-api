/* Copyright (C) 2015 mxHero Inc (mxhero@mxhero.com)
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BusinessCredentialTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBusinessCredentialAgainstCommon() {
		Credential credential = new Credential.Builder().accessToken("ACCESS_TOKEN").refreshToken("REFRESH_TOKEN").scope("SCPOPE").tokenType("Bearer").build();
		assertNotNull(credential);
		assertTrue(credential instanceof Credential);
		assertFalse(credential instanceof BusinessCredential);
		
		BusinessCredential credentialBusiness = (BusinessCredential) BusinessCredential.builder().sharepointEndpointUri("myuri").accessToken("ACCESS_TOKEN").refreshToken("REFRESH_TOKEN").scope("SCPOPE").tokenType("Bearer").build();
		assertNotNull(credentialBusiness);
		assertTrue(credentialBusiness instanceof Credential);
		assertTrue(credentialBusiness instanceof BusinessCredential);
		assertEquals("myuri",((BusinessCredential)credentialBusiness).getSharepointEndpointUri());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidationError() {
		BusinessCredential.builder().accessToken("ACCESS_TOKEN").refreshToken("REFRESH_TOKEN").scope("SCPOPE").tokenType("Bearer").build();
		fail();
	}


}
