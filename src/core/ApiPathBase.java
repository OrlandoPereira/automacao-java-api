package core;

import io.restassured.http.ContentType;

public interface ApiPathBase {

    String APP_BASE_URL = "http://localhost";
    Integer APP_PORT = 8080;

    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 3000l;

}
