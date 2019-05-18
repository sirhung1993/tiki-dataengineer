package vn.tiki.product;

import vn.tiki.grpc.model.product.ProductModel.Attributes;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Product;
import vn.tiki.grpc.model.product.ProductModel.Storage;

public class SampleProduct {

  private Product iphoneX1 = Product.newBuilder()
      .setName("Iphone X 64Gb Black")
      .setPrice(999)
      .setMarketPrice(1099)
      .setAttribute(Attributes.newBuilder()
          .addImagesURL("linkIphoneX1-1")
          .addImagesURL("linkIphoneX1-2")
          .addImagesURL("linkIphoneX1-3")
          .setColor(Colors.BLACK)
          .setStorage(Storage.SIX_FOUR_GB)
          .setDescription("Chính hãng, Nguyên seal, Mới 100%, Chưa Active")
          .setOrigin("USA")
          .setFactoryDatetime("2019-05-18T06:43:25.300-0700")
          .setWeight(174)
          .build())
      .build();

  private Product iphoneX2 = Product.newBuilder()
      .setName("Iphone X 128Gb Yellow")
      .setPrice(950)
      .setMarketPrice(1050)
      .setAttribute(Attributes.newBuilder()
          .addImagesURL("linkIphoneX2-1")
          .addImagesURL("linkIphoneX2-2")
          .addImagesURL("linkIphoneX2-3")
          .setColor(Colors.YELLOW)
          .setStorage(Storage.ONE_TWO_EIGHT_GB)
          .setDescription("Chính hãng, Nguyên seal, Mới 100%, Chưa Active")
          .setOrigin("CHINA")
          .setFactoryDatetime("2019-05-18T06:43:25.300-0700")
          .setWeight(174)
          .build())
      .build();

  public Product getIphoneX1() {
    return iphoneX1;
  }

  public Product getIphoneX2() {
    return iphoneX2;
  }
}
