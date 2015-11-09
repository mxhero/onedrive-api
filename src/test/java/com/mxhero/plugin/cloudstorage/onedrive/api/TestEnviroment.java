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

public class TestEnviroment {

	private Credential credential;
	private Application application;
	
	public Credential getCredential() {
		return credential;
	}
	
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	
	public Application getApplication() {
		return application;
	}
	
	public void setApplication(Application application) {
		this.application = application;
	}
	
}
