package vn.tiki.product.base;

import java.util.List;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Product;
import vn.tiki.grpc.model.product.ProductModel.Storage;

public interface ConfigurableProductInterface {
  List<Colors> getListColor();
  List<Storage> getListStorage();
  Integer validateConfigurableProduct(Product product);
  Product getConfigurableProduct(Colors colors, Storage storage);
}
