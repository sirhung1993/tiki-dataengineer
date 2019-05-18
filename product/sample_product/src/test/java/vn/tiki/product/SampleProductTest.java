package vn.tiki.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import vn.tiki.grpc.model.product.ProductModel.Attributes;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Product;
import vn.tiki.grpc.model.product.ProductModel.Storage;


class SampleProductTest {

  private SampleProduct sampleProduct = new SampleProduct();
  private Product iphoneX1 = sampleProduct.getIphoneX1();
  private Product iphoneX2 = sampleProduct.getIphoneX2();

  @Test
  void getIphoneX1() {
    assertEquals("Iphone X 64Gb Black", iphoneX1.getName());
    assertEquals(999, iphoneX1.getPrice());
    assertEquals(1099, iphoneX1.getMarketPrice());

    Attributes attributes = iphoneX1.getAttribute();

    assertEquals("linkIphoneX1-1", attributes.getImagesURL(0));
    assertEquals("linkIphoneX1-2", attributes.getImagesURL(1));
    assertEquals("linkIphoneX1-3", attributes.getImagesURL(2));
    assertEquals(Colors.BLACK, attributes.getColor());
    assertEquals(Storage.SIX_FOUR_GB, attributes.getStorage());
    assertEquals("Chính hãng, Nguyên seal, Mới 100%, Chưa Active", attributes.getDescription());
    assertEquals("USA", attributes.getOrigin());
    assertEquals("2019-05-18T06:43:25.300-0700", attributes.getFactoryDatetime());
    assertEquals(174, attributes.getWeight());
  }

  @Test
  void getIphoneX2() {
    assertEquals("Iphone X 128Gb Yellow", iphoneX2.getName());
    assertEquals(950, iphoneX2.getPrice());
    assertEquals(1050, iphoneX2.getMarketPrice());

    Attributes attributes = iphoneX2.getAttribute();

    assertEquals("linkIphoneX2-1", attributes.getImagesURL(0));
    assertEquals("linkIphoneX2-2", attributes.getImagesURL(1));
    assertEquals("linkIphoneX2-3", attributes.getImagesURL(2));
    assertEquals(Colors.YELLOW, attributes.getColor());
    assertEquals(Storage.ONE_TWO_EIGHT_GB, attributes.getStorage());
    assertEquals("Chính hãng, Nguyên seal, Mới 100%, Chưa Active", attributes.getDescription());
    assertEquals("CHINA", attributes.getOrigin());
    assertEquals("2019-05-18T06:43:25.300-0700", attributes.getFactoryDatetime());
    assertEquals(174, attributes.getWeight());
  }
}