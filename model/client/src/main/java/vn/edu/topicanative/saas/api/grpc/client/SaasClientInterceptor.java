package vn.edu.topicanative.saas.api.grpc.client;

import java.util.Map;
import java.util.UUID;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import vn.edu.topicanative.saas.api.utils.GrpcConstants;

public class SaasClientInterceptor implements ClientInterceptor {

  private final Map<String, String> additionalHeaders;

  public SaasClientInterceptor(Map<String, String> additionalHeaders) {
    this.additionalHeaders = additionalHeaders;
  }

  @Override
  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
      CallOptions callOptions, Channel next) {
    return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
      @Override
      public void start(Listener<RespT> responseListener, Metadata headers) {
        headers.put(GrpcConstants.REQUEST_ID_HEADER, UUID.randomUUID().toString());
        if (additionalHeaders != null) {
          for (String key : additionalHeaders.keySet()) {
            String value = additionalHeaders.get(key);
            if (value == null || value.isEmpty()) {
              continue;
            }
            headers.put(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER), value);
          }
        }
        super.start(responseListener, headers);
      }
    };
  }
}
