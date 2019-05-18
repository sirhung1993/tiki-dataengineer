package vn.tiki.product.sample;

import java.util.ArrayList;
import java.util.List;
import vn.tiki.grpc.model.product.ProductModel.Product;

public abstract class ConfigurableProductAbstract implements ConfigurableProductInterface{

  public ConfigurableProductAbstract(Product... products) {
    this.products = products;
  }

  protected Product[] products;

  public <T> List<T> removeDuplicate(List<T> list) {
    List<T> removedDuplicate = new ArrayList<>();
    for (T e : list) {
      if (!removedDuplicate.contains(e)) {
        removedDuplicate.add(e);
      }
    }
    return removedDuplicate;
  }
}
