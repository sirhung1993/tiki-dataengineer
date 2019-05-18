package vn.edu.topicanative.saas.api.auth;

public class AuthInfo {
  private String clientId;
  private String realm;
  private String clientSecret;
  private String authHost;

  public AuthInfo(String clientId, String realm, String clientSecret, String authHost) {
    this.clientId = clientId;
    this.realm = realm;
    this.clientSecret = clientSecret;
    this.authHost = authHost;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getRealm() {
    return realm;
  }

  public void setRealm(String realm) {
    this.realm = realm;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getAuthHost() {
    return authHost;
  }

  public void setAuthHost(String authHost) {
    this.authHost = authHost;
  }
}
