package vn.edu.topicanative.saas.api.auth;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.keycloak.authorization.client.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vn.edu.topicanative.saas.api.model.security.SecurityModel.KeyCloakAuthInfo;

@RunWith(MockitoJUnitRunner.Silent.class)
class KeycloakClientTest {
  private Configuration kcConfig;
  private String username = "hungbv2@topica.edu.vn";
  private String password = "topica@123";
  private String token;
  private String authUri, authRealm, authClientId, authClientSecret;

  private AuthzClient kcClient;
  public String getToken(String username, String password) {
    try {
      AccessTokenResponse response = kcClient.obtainAccessToken(username, password);
      this.token = response.getToken();
      return this.token;
    } catch (Exception e) {
      System.out.println(e.getClass());
      throw e;
    }
  }

  @Test
  void getTokenInfo() {
    authUri = "https://accountit.topicanative.edu.vn";
    authRealm= "native-it";
    authClientId = "backend-saas-client";
    authClientSecret = "38eab4ac-bd6e-45b5-8b33-1d250c05f027";

    Map<String, Object> secret = new HashMap<String, Object>() {{
      put("secret", authClientSecret);
    }};

    kcConfig = new Configuration(authUri + "/auth", authRealm, authClientId, secret, null);
    kcClient = AuthzClient.create(kcConfig);

    AuthInfo authInfo = new AuthInfo(authClientId, authRealm, authClientSecret, authUri);
    KeycloakClient oAuth = new KeycloakClient(authInfo);
    token = getToken(username, password);
    KeyCloakAuthInfo jsonTokenInfo =  oAuth.verifyAccessToken(token);

    assertEquals(username, jsonTokenInfo.getUsername());
    assertEquals(true, jsonTokenInfo.getActive());
  }
}