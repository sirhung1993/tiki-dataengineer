package vn.edu.topicanative.saas.api.grpc.client;

import io.grpc.CallCredentials;

public class ClientRequest {

  private final String tenantId;
  private final String clientId;
  private final String accessToken;

  public ClientRequest(String tenantId, String clientId, String accessToken) {
    this.tenantId = tenantId;
    this.clientId = clientId;
    this.accessToken = accessToken;
  }

  public CallCredentials getCallCredentials() {
    return new SaasCallCredentials(tenantId, clientId, accessToken);
  }

  public String getTenantId() {
    return tenantId;
  }

  public String getClientId() {
    return clientId;
  }

  public String getAccessToken() {
    return accessToken;
  }
}
