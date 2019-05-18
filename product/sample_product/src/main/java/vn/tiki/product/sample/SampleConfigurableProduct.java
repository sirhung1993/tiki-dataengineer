package vn.tiki.product.sample;

import java.util.List;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Product;
import vn.tiki.grpc.model.product.ProductModel.Storage;

public class SampleConfigurableProduct extends ConfigurableProductAbstract {

//  private SampleProduct sampleProduct = new SampleProduct();
//  private Product iphoneX1 = sampleProduct.getIphoneX1();
//  private Product iphoneX2 = sampleProduct.getIphoneX2();

  public SampleConfigurableProduct(Product... products) {
    super(products);
  }

  @Override
  public void setColor() {

  }

  @Override
  public void setStorage() {

  }

  @Override
  public boolean validateConfigurableProduct() {
    return false;
  }

  @Override
  public List<Colors> getListColor() {
    return null;
  }

  @Override
  public List<Storage> getListStorage() {
    return null;
  }
}
