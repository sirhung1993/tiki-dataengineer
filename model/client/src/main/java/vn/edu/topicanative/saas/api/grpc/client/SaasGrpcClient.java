package vn.edu.topicanative.saas.api.grpc.client;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SaasGrpcClient {

  private final ManagedChannel channel;

  public SaasGrpcClient(String grpcHost, int grpcPort, Map<String, String> additionalHeaders) {
    channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
        .intercept(new SaasClientInterceptor(additionalHeaders)).usePlaintext().build();
  }

  public ManagedChannel getManagedChannel() {
    return channel;
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(15, TimeUnit.SECONDS);
  }
}
