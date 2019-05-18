package vn.edu.topicanative.saas.api.grpc.client;

import java.util.ArrayList;
import java.util.List;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Banks;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.ContactId;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Devices;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.LearnTypes;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Levels;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Package;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.PaymentMethods;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Payments;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Places;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.Products;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.PromotionPackages;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.ResultTest;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.StudentHandOver;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.StudentHandOverResponse;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.StudentInfo;
import vn.edu.topicanative.saas.api.model.advisor.AdvisorModel.TvtsInfo;
import vn.edu.topicanative.saas.api.model.studentcare.StudentCareHistoryModel.StudentCareHistory;
import vn.edu.topicanative.saas.api.model.user.UserModel.Payment;
import vn.edu.topicanative.saas.grpc.service.GrpcHandOverServiceGrpc;
import vn.edu.topicanative.saas.grpc.service.GrpcHandOverServiceGrpc.GrpcHandOverServiceBlockingStub;
import vn.edu.topicanative.saas.grpc.service.GrpcStudentCareHistoryServiceGrpc;
import vn.edu.topicanative.saas.grpc.service.GrpcStudentCareHistoryServiceGrpc.GrpcStudentCareHistoryServiceBlockingStub;


public class Sample {

  public static void main(String args[]) {

    // Cap nhat gia tri cho Keycloak - Host
    String kcHost = "https://accountit.topicanative.edu.vn/auth";
    String kcRealm = "native-it";
    String kcClientId = "nvn-advisor-saas-client";
    String kcClientSecret = "2e7d5d29-1e7d-4418-bcf1-d32e7e2ad0ef";

    AuthzClient kcClient = getKcClient(kcHost, kcRealm, kcClientId, kcClientSecret);

    // Cap nhat gia tri cho Keycloak - Client
    String tenantId = "nvn";
    String clientId = "nvn-advisor-saas-client";
    String userName = "hungbv2@topica.edu.vn";
    String password = "topica@123";

    String accessToken = getToken(userName, password,
        kcClient);

    // Cap nhat lai gia tri grpcHost vs grpcPort theo config
    // config cho local host duoc khai bao trong saas-grpc yml file
    String grpcHost = "localhost";
    Integer grpcPort = 9082;

    SaasGrpcClient saasGrpcClient = new SaasGrpcClient(grpcHost, grpcPort, null);
    SaasServiceFactory saasServiceFactory = new SaasServiceFactory(saasGrpcClient);
    ClientRequest clientRequest = new ClientRequest(tenantId, clientId, accessToken);

    // CAM XOA

//        GrpcStudentCareHistoryServiceBlockingStub grpcStudentCareHistoryServiceBlockingStub = saasServiceFactory.getBlockingStubService(clientRequest,
//            GrpcStudentCareHistoryServiceGrpc.class, GrpcStudentCareHistoryServiceBlockingStub.class);
//        ContactId contactId = ContactId.newBuilder().setId("1").build();
//        StudentCareHistory studentCareHistory = grpcStudentCareHistoryServiceBlockingStub.getAllCareHistory(contactId);
//        System.out.println(studentCareHistory.toString());

    GrpcHandOverServiceBlockingStub handOver = saasServiceFactory
        .getBlockingStubService(clientRequest, GrpcHandOverServiceGrpc.class,
            GrpcHandOverServiceBlockingStub.class);

    StudentInfo studentInfo = StudentInfo.newBuilder()
        .setContactId("1")
        .setLastName("Bui")
        .setFirstName("Viet Hung")
        .setFullName("Bui Viet Hung")
        .setPhone("12345678")
        .setEmail("hungbv2@topica.edu.vn")
        .setDateOfBirth("01/01/1991")
        .build();

    Package handOverPackage = Package.newBuilder()
        .setProduct(Products.TOPMITO)
        .setPackage("package")
        .setLearnType(LearnTypes.TT)
        .setDayOfPackage(360)
        .setValueOfPackage(18000000)
        .setDevice(Devices.UNKNOWN_Devices)
        .build();
    Payments payment = Payments.newBuilder()
        .setMethod(PaymentMethods.BANKING)
        .setFromBank(Banks.Vietcombank)
        .setFromPlace(Places.HN)
        .setAmount(18000000)
        .setContentBilling("Hello from HungBV2")
        .build();

    List<Payments> payments = new ArrayList<Payments>();
    payments.add(payment);

    PromotionPackages promotionPackages = PromotionPackages.newBuilder()
        .setName("test")
        .setKey("key")
        .build();
    List<PromotionPackages> promo = new ArrayList<PromotionPackages>();
    promo.add(promotionPackages);

    ResultTest resultTest = ResultTest.newBuilder()
        .setLevel(Levels.basic)
        .setCacesPoint(100)
        .setInterviewPoint(100)
        .setToeicPoint(100)
        .build();

    TvtsInfo tvtsInfo = TvtsInfo.newBuilder()
        .setEmail("tvts@topica.edu.vn")
        .setID("tvts")
        .setUsername("tvts")
        .setPhone("01234545675")
        .build();

    StudentHandOver studentHandOver = StudentHandOver.newBuilder()
        .setStudentInfo(studentInfo)
        .setPackage(handOverPackage)
        .setIsCheckDevice(true)
        .setSsrsCode("123456789")
        .addAllPromotionPackage(promo)
        .setIsNativeTalk(true)
        .addAllPayments(payments)
        .setResultTest(resultTest)
        .setTvtsInfo(tvtsInfo)
        .setSblink("https://devoms.topica.vn/dasj")
        .build();

    StudentHandOverResponse studentHandOverResponse = handOver.provision(studentHandOver);

    System.out.println(studentHandOverResponse.toString());

  }

  public static AuthzClient getKcClient(String kcHost, String kcRealm, String kcClientId,
      String kcClientSecret) {
    Map<String, Object> secret = new HashMap<String, Object>() {{
      put("secret", kcClientSecret);
    }};
    Configuration kcConfig = new Configuration(kcHost, kcRealm, kcClientId, secret, null);
    AuthzClient kcClient = AuthzClient.create(kcConfig);
    return kcClient;
  }

  public static String getToken(String username, String password, AuthzClient kcClient) {
    try {
      AccessTokenResponse response = kcClient.obtainAccessToken(username, password);
      String token = response.getToken();
      return token;
    } catch (Exception e) {
      System.out.println(e.getClass());
      throw e;
    }
  }
}
