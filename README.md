# OneDrive-Api

OneDrive-Api is a http client for java backends that want to use OneDrive Api from Microsoft (https://dev.onedrive.com/README.htm)

  - Has an auto-refresh mechanism, is activated when refreshToken is provided
  - Amount of retries, timeouts, and more are easy configurable using either environment variables or system properties.

## OneDrive for Business Api

We have just implemented Support for Business Api. In particular we have implemented some of the restrictions this Api has compared with Individual Users Api. Check [here](https://dev.onedrive.com/odb-preview/release-notes.htm#uploading-items)

  - [Authentication for Business Api](https://dev.onedrive.com/odb-preview/release-notes.htm#authentication) it is implemented completed in this API SDK. You just have to call ```OneDrive.redeemBusiness()``` method and the SDK will cover all steps documented in Business API: Get first pair of Tokens, discover Sharepoint URL, redeem for that Sharepoint URL. We have added another extra step which is retriever email address of the user who is redeeming the API. If the user doesn't have OneDrive for Business enable SDK will throw AuthenticationExcpetion.
  - Handshaking for obtain first code OAuth2 param should be let by implementations. Check here for more information go [here](https://dev.onedrive.com/auth/aad_oauth.htm#register-your-app-with-azure-active-directory)


Usage
----

#### Redeem

    OneDrive.redeem("REDEEM_CODE", "YOUR_CLIENT_ID", "YOUR_REDIRECT_URI", "YOUR_CLIENT_SECRETE");

Will return:

    {
      "token_type":"bearer",
      "expires_in": 3600,
      "scope":"wl.basic onedrive.readwrite",
      "access_token":"EwCo...AA==",
      "refresh_token":"eyJh...9323"
    }

##### Business Redeem

    OneDrive.redeemBusiness("REDEEM_CODE", "YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET", "YOUR_REDIRECT_URI");

Will return:

    BusinessCredential.java object


#### Create Instance

    new OneDrive.Builder()
    				.application(new Application("YOUR_CLIENT_ID", "YOUR_REDIRECT_URI", "YOUR_CLIENT_SECRETE"))
    				.credential(new Credential.Builder()
    					.accessToken("ACCESS_TOKEN")
    					.refreshToken("REFRESH_TOKEN") //optional, if not present wont try to refresh access tokens
    					.user("USER_EMAIL")
    					.userId("USER_ID")
    					.build())
    				.build();

##### Create Instance for OneDrive for Business
Instantiating a new OneDrive Client for using Business API only differs from Credential type to be used, because we have to tell SDK which specific Sharepoint URL and Resource Id must be used.

    new OneDrive.Builder()
    				.application(new Application("YOUR_CLIENT_ID", "YOUR_REDIRECT_URI", "YOUR_CLIENT_SECRETE"))
    				.businessCredential(BusinessCredential.builder()
    					.sharepointEndpointUri("sharepointUriReturnedByRedeem")
    					.sharepointResourceId("resourceidReturnedByRedeem")
    					.accessToken("ACCESS_TOKEN")
    					.refreshToken("REFRESH_TOKEN") //optional, if not present wont try to refresh access tokens
    					.user("USER_EMAIL")
    					.build())
    				.build();

Or you could just send the BusinessCredential response returned by OneDrive.redeemBusiness method.

    BusinessCredential businesCredResponse = OneDrive.redeemBusiness("REDEEM_CODE", "YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET", "YOUR_REDIRECT_URI");

    OneDrive api = new OneDrive.Builder()
    				.application(new Application("YOUR_CLIENT_ID", "YOUR_REDIRECT_URI", "YOUR_CLIENT_SECRETE"))
    				.businessCredential(businesCredResponse)
    				.build();

    api.items().childrenByPath("");


#### Lisent to Token refresh
The lib has a listener that can be set when creating the OneDrive instance. This one has basically two methods that allow to take proper actions when a token is successfuly refreshed (like persist the new token to a database for example) or when you an error ocurred (send and email to the user asking for new tokens).

    com.mxhero.plugin.cloudstorage.onedrive.api.command.TokenRefreshListener

#### Usage examples

    OneDrive oneDrive = new OneDrive.Builder()
                        /*Example creation*/
                        .build();

    //metadata by id call
    Item item = oneDrive.items().metadataById("BF667A0C62207823!105");
    //using parameters on the call
    item = oneDrive.items().metadataById("BF667A0C62207823!105", new Parameters().select("id,name"));
    //metadata by path
    item = oneDrive.items().metadataByPath("Documents/FILE.txt");
    //search call
    ItemList searchResult = oneDrive.items().searchByPath("Pictures", new Parameters().query("uploadtest.png"));
    //Thumbnails call
    ThumbnailSetList list = oneDrive.items().thumbnails("BF667A0C62207823!115", null);
    //delete call
    oneDrive.items().deleteByPath("Pictures/delete.png");
    //upload
    oneDrive.items().simpleUploadByPath("Pictures"
    				, "uploadtest.png"
    				, FILE_INPUT_STREAM
    				, ConflictBehavior.replace);
    //copy
    oneDrive.items().copyById(ITEM_ID_TO_COPY,
    				new ItemReference.Builder().id(DESTINATION_PARENT_ID).build()
    				, FILE_NAME);
    //move
    oneDrive.items().moveById(ITEM_ID_TO_MOVE,
    				new ItemReference.Builder().id(DESTINATION_PARENT_ID).build());



Dependencies
-----
	<dependencies>
		<dependency>
			<groupId>commons-lang</groupId>
			<artifactId>commons-lang</artifactId>
			<version>2.6</version>
		</dependency>
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.9</version>
		</dependency>
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-core-asl</artifactId>
			<version>1.9.9</version>
		</dependency>
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient-osgi</artifactId>
			<version>4.3.1</version>
		</dependency>
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpcore-osgi</artifactId>
			<version>4.3.1</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.5.8</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>1.5.8</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.14</version>
		</dependency>
	</dependencies>


Development
----

Want to contribute? Great!

If you have any doubt please contact mmarmol@mxhero.com


Todos
----

 - Add all upload calls
 - Implement SyncChanges backend
 - Extend configuration out of environment and system properties only
 - Add Code Comments


License
----

Apache License http://www.apache.org/licenses/LICENSE-2.0


Maven Repo
----
    <repository>
		<repository>
			<id>org.mxhero.releases</id>
			<url>http://repository.mxhero.com/nexus/content/repositories/org.mxhero.releases</url>
		</repository>
    </repository>

    <dependency>
    	<groupId>org.mxhero.plugin.cloudstorage</groupId>
    	<artifactId>org.mxhero.plugin.cloudstorage.onedriveapi</artifactId>
    	<version>0.3.5</version>
    </dependency>

Building SDK
----

In order to build this SDK we advice to run ```mvn clean install``` command which are going to run all Integration test.
Integration Tests run against real OneDrive accounts both, end users and Business Account.

To configure those accounts you should pass *maven* command a json configuration file with Access Tokens, Client ids and secrets of your applications.

### Json Configuration Format
Upload the following files to a public location or just leave them into your local disk.

#### OneDrive for End users.

    {
        "credential": {
            "tokenType": "bearer",
            "accessToken": "YOUR_ACCESS_TOKEN",
            "refreshToken": "YOUR_REFRESH_TOKEN",
            "user": "EMAIL_ACCOUNT"
        },
        "application": {
            "clientId": "YOUR_CLIENT_ID",
            "redirectUri": "YOUR_REDIRECT_URI",
            "clientSecret": "YOUR_CLIENT_SECRET"
        }
    }


#### OneDrive for Business API.

    {
        "credential": {
            "tokenType": "bearer",
            "accessToken": "YOUR_ACCESS_TOKEN",
            "refreshToken": "YOUR_REFRESH_TOKEN",
            "user": "EMAIL_ACCOUNT",
            "sharepointEndpointUri": "https://{tenant}-my.sharepoint.com/_api/v2.0/me",
            "sharepointResourceId": "https://{tenant}-my.sharepoint.com/"
        },
        "application": {
            "clientId": "YOUR_CLIENT_ID",
            "redirectUri": "YOUR_REDIRECT_URI",
            "clientSecret": "YOUR_CLIENT_SECRET"
        }
    }

### Calling maven to build project

    mvn clean install -Dtest.enviroment.json.url=URL_TO_MY_JSON_USER -Dtest.enviroment.business.json.url=URL_TO_MY_JSON_BUSINESS

    URL_TO_MY_JSON_USER could be file:///home/mymachine/myfile_user.json

    URL_TO_MY_JSON_BUSINESS could be file:///home/mymachine/myfile_business.json
