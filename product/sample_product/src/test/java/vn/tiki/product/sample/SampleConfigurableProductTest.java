package vn.tiki.product.sample;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vn.tiki.grpc.model.product.ProductModel.Attributes;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Product;
import vn.tiki.grpc.model.product.ProductModel.Storage;

class SampleConfigurableProductTest {

  private SampleProduct sampleProduct = new SampleProduct();
  private Product iphoneX1 = sampleProduct.getIphoneX1();
  private Product iphoneX2 = sampleProduct.getIphoneX2();

  private SampleConfigurableProduct sampleConfigurableProduct =
      new SampleConfigurableProduct(iphoneX1, iphoneX2);

  @Test
  void validateConfigurableProduct() {
    Product noExistProduct = Product.newBuilder()
        .setAttribute(Attributes.newBuilder()
            .setColor(Colors.BLACK)
            .setStorage(Storage.ONE_TWO_EIGHT_GB)
            .build())
        .build();
    assertEquals(null,
        sampleConfigurableProduct.validateConfigurableProduct(noExistProduct),
        "The product does not exist!");
    assertEquals(new Integer(0),
        sampleConfigurableProduct.validateConfigurableProduct(iphoneX1), "The product exist!");
    assertEquals(new Integer(1),
        sampleConfigurableProduct.validateConfigurableProduct(iphoneX2), "The product exist!");
  }

  @Test
  void getCofigurableProduct() {
    assertEquals(null,
        sampleConfigurableProduct.getConfigurableProduct(Colors.BLACK, Storage.ONE_TWO_EIGHT_GB),
        "The product is out of order!");
    assertEquals(null,
        sampleConfigurableProduct.getConfigurableProduct(Colors.YELLOW, Storage.SIX_FOUR_GB),
        "The product is out of order!");
    assertEquals(iphoneX1,
        sampleConfigurableProduct.getConfigurableProduct(Colors.BLACK, Storage.SIX_FOUR_GB),
        "The configurable product is correct!");
    assertEquals(iphoneX2,
        sampleConfigurableProduct.getConfigurableProduct(Colors.YELLOW, Storage.ONE_TWO_EIGHT_GB),
        "The configurable product is correct!");
  }

  @Test
  void getListColor() {
    SampleConfigurableProduct configurableProduct =
        new SampleConfigurableProduct(iphoneX1, iphoneX2, iphoneX1, iphoneX2);
    List<Colors> listColors = new ArrayList<>();
    listColors.add(Colors.BLACK);
    listColors.add(Colors.YELLOW);
    assertEquals(listColors, configurableProduct.getListColor(),
        "return correct list color to drop down buttong!");
  }

  @Test
  void getListStorage() {
    SampleConfigurableProduct configurableProduct =
        new SampleConfigurableProduct(iphoneX1, iphoneX2, iphoneX1, iphoneX2);
    List<Storage> listCorrectStorage = new ArrayList<>();
    listCorrectStorage.add(Storage.SIX_FOUR_GB);
    listCorrectStorage.add(Storage.ONE_TWO_EIGHT_GB);
    assertEquals(listCorrectStorage, configurableProduct.getListStorage(),
        "return correct list color to drop down buttong!");
  }
}