package vn.tiki.product.sample;

import vn.tiki.grpc.model.product.ProductModel.Product;

public abstract class ConfigurableProductAbstract implements ConfigurableProductInterface{

  public ConfigurableProductAbstract(Product... products) {
    this.products = products;
  }

  protected Product[] products;
}
