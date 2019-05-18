package vn.edu.topicanative.saas.api.auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.io.CharStreams;

import vn.edu.topicanative.saas.api.model.security.SecurityModel.KeyCloakAuthInfo;

public class KeycloakClient {

  private static final String INTROSPECT_URI_FORMAT = "%s/auth/realms/%s/protocol/openid-connect/token/introspect";
  private final AuthInfo authInfo;

  public KeycloakClient(AuthInfo authInfo) {
    this.authInfo = authInfo;
  }

  public KeyCloakAuthInfo verifyAccessToken(String accessToken) {
    Map<String, String> requestBody = new HashMap<>();
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/x-www-form-urlencoded");
    headers.put("Accept", "application/x-www-form-urlencoded");
    requestBody.put("client_id", authInfo.getClientId());
    requestBody.put("client_secret", authInfo.getClientSecret());
    requestBody.put("token", accessToken);
    String introspect = String.format(INTROSPECT_URI_FORMAT, authInfo.getAuthHost(), authInfo.getRealm());
    try {
      HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
      JsonFactory JSON_FACTORY = new JacksonFactory();
      HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory((HttpRequest request) -> {
        request.setParser(new JsonObjectParser(JSON_FACTORY));
      });

      HttpContent httpContent = new UrlEncodedContent(requestBody);
      HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(introspect), httpContent);
      request.getHeaders().putAll(headers);
      HttpResponse response = request.execute();
      String tokenInfo = convertStreamToString(response.getContent());

      return KeyCloakAuthInfo.newBuilder().setUserId(getUserId(tokenInfo)).setUsername(getUsername(tokenInfo))
          .addAllRoles(getRoles(tokenInfo)).setActive(getActive(tokenInfo)).setClientId(getClientId(tokenInfo)).build();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public String convertStreamToString(InputStream inputStream) {
    try {
      return CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  public List<String> getRoles(String tokenInfo) {
    JSONObject infoJson = new JSONObject(tokenInfo);
    List<String> listString = new ArrayList<>();

    List<Object> listObject = infoJson.getJSONObject("realm_access").getJSONArray("roles")
        .toList();
    Iterator i = listObject.iterator();
    while (i.hasNext()) {
      listString.add(i.next().toString());
    }
    return listString;
  }

  private String getUserId(String tokenInfo) {
    JSONObject infoJson = new JSONObject(tokenInfo);
    return infoJson.getString("sub");
  }

  private String getUsername(String tokenInfo) {
    JSONObject infoJson = new JSONObject(tokenInfo);
    return infoJson.getString("username");
  }

  private Boolean getActive(String tokenInfo) {
    JSONObject infoJson = new JSONObject(tokenInfo);
    return infoJson.getBoolean("active");
  }

  private String getClientId(String tokenInfo) {
    JSONObject infoJson = new JSONObject(tokenInfo);
    return infoJson.getString("client_id");
  }
}
