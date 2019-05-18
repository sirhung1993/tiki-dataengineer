package vn.tiki.product;

import java.util.ArrayList;
import java.util.List;
import vn.tiki.grpc.model.product.ProductModel.Attributes;
import vn.tiki.grpc.model.product.ProductModel.Colors;
import vn.tiki.grpc.model.product.ProductModel.Product;
import vn.tiki.grpc.model.product.ProductModel.Storage;
import vn.tiki.product.base.ConfigurableProductAbstract;

public class SampleConfigurableProduct extends ConfigurableProductAbstract {

  public SampleConfigurableProduct(Product... products) {
    super(products);
  }

  @Override
  public Integer validateConfigurableProduct(Product product) {
    Integer index = 0;
    for (Product p : this.products) {
      if (p.getAttribute().getColor().equals(product.getAttribute().getColor())
          && p.getAttribute().getStorage().equals(product.getAttribute().getStorage())) {
        return  index;
      } else {
        index++;
      }
    }
    return null;
  }

  @Override
  public Product getConfigurableProduct(Colors colors, Storage storage) {
    Product p = Product.newBuilder()
        .setAttribute(Attributes.newBuilder()
            .setColor(colors)
            .setStorage(storage)
            .build())
        .build();
    Integer productIndex = validateConfigurableProduct(p);
    return productIndex != null ? this.products[productIndex] : null;
  }

  @Override
  public List<Colors> getListColor() {
    List<Colors> listColor = new ArrayList<>();
    for (Product p : this.products) {
      listColor.add(p.getAttribute().getColor());
    }
    return this.removeDuplicate(listColor);
  }

  @Override
  public List<Storage> getListStorage() {
    List<Storage> listStorage = new ArrayList<>();
    for (Product p : this.products) {
      listStorage.add(p.getAttribute().getStorage());
    }
    return this.removeDuplicate(listStorage);
  }
}
