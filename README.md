# OneDrive-Api

OneDrive-Api is a http client for java backends that want to use OneDrive Api from Microsoft (https://dev.onedrive.com/README.htm)

  - Has an auto-refresh mechanism implemented on every call
  - Amount of retries, timeouts, and more are easy configurable using either enviroment variables or system properties.
  - Suports OneDrive for indivual but for business is coming soon.

Usage
----

#### Redeem

```
OneDrive.redeem("REDEEM_CODE", "YOUR_CLIENT_ID", "YOUR_REDIRECT_URI", "YOUR_CLIENT_SECRETE");
```
Will return:
```
{
  "token_type":"bearer",
  "expires_in": 3600,
  "scope":"wl.basic onedrive.readwrite",
  "access_token":"EwCo...AA==",
  "refresh_token":"eyJh...9323"
}
```

#### Create Instance
```
new OneDrive.Builder()
				.application(new Application("YOUR_CLIENT_ID", "YOUR_REDIRECT_URI", "YOUR_CLIENT_SECRETE"))
				.credential(new Credential.Builder()
					.accessToken("ACCESS_TOKEN")
					.refreshToken("REFRESH_TOKEN")
					.user("USER_EMAIL")
					.userId("USER_ID")
					.build())
				.build();
```
#### Lisent to Token refresh
The lib has a listener that can be set when creating the OneDrive instance. This one has basically two methods that allow to take proper actions when a token is successfuly refreshed (like persist the new token to a database for example) or when you an error ocurred (send and email to the user asking for new tokens).

    com.mxhero.plugin.cloudstorage.onedrive.api.command.TokenRefreshListener

#### Usage examples
```
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


```


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

If you have any doubt please contact mmarmol at mxhero.com


Todos
----

 - Add other all upload calls
 - Implement Sync Changes backend
 - Api for Business implementation
 - Extend configuration out of enviroment and system properties only
 - Add Code Comments
 - Create public tests (We only have internal only)

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
    	<version>0.2.13</version>
    </dependency>
