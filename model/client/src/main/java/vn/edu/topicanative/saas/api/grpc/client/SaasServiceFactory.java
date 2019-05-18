package vn.edu.topicanative.saas.api.grpc.client;

import java.lang.reflect.Method;

import io.grpc.Channel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.AbstractStub;

public class SaasServiceFactory {

  private final SaasGrpcClient saasGrpcClient;

  public SaasServiceFactory(SaasGrpcClient saasGrpcClient) {
    this.saasGrpcClient = saasGrpcClient;
  }

  @SuppressWarnings("unchecked")
  public <S, BS extends AbstractStub<BS>> BS getBlockingStubService(ClientRequest clientRequest, Class<S> grpcService,
      Class<BS> grpcBlockingStub) {
    try {
      Method blockingStubMethod = grpcService.getMethod("newBlockingStub", Channel.class);
      BS bs = (BS) blockingStubMethod.invoke(null, saasGrpcClient.getManagedChannel());
      return bs.withCallCredentials(clientRequest.getCallCredentials());
    } catch (Exception e) {
      throw new StatusRuntimeException(Status.INTERNAL.withDescription(e.getMessage()));
    }
  }
}
