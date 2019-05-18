package vn.edu.topicanative.saas.api.grpc.client;

import java.util.concurrent.Executor;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import vn.edu.topicanative.saas.api.utils.GrpcConstants;

public class SaasCallCredentials extends CallCredentials {

  private final String tenantId;
  private final String clientId;
  private final String accessToken;

  public SaasCallCredentials(String tenantId, String clientId, String accessToken) {
    if (tenantId == null || tenantId.isEmpty()) {
      throw new IllegalArgumentException("tenantId is null or empty");
    }
    this.tenantId = tenantId;
    if (clientId == null || clientId.isEmpty()) {
      throw new IllegalArgumentException("clientId is null or empty");
    }
    this.clientId = clientId;
    if (accessToken == null || accessToken.isEmpty()) {
      throw new IllegalArgumentException("accessToken is null or empty");
    }
    this.accessToken = accessToken;
  }

  @Override
  public void applyRequestMetadata(RequestInfo requestInfo, Executor appExecutor, MetadataApplier applier) {
    appExecutor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Metadata headers = new Metadata();
          headers.put(GrpcConstants.TENANT_ID_HEADER, tenantId);
          headers.put(GrpcConstants.CLIENT_ID_HEADER, clientId);
          headers.put(GrpcConstants.ACCESS_TOKEN_HEADER, accessToken);
          applier.apply(headers);
        } catch (Throwable e) {
          applier.fail(Status.UNAUTHENTICATED.withCause(e));
        }
      }
    });
  }

  @Override
  public void thisUsesUnstableApi() {
  }
}