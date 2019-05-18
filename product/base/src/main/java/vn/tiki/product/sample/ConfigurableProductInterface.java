package vn.tiki.product.sample;

import java.util.List;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Storage;

public interface ConfigurableProductInterface {
  public List<Colors> getListColor();
  public List<Storage> getListStorage();
  void setColor();
  void setStorage();
  boolean validateConfigurableProduct();
}
