package at.alphaplan.AlphaWeb.domain.product;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import at.alphaplan.AlphaWeb.domain.media.Media;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

@Getter
@ToString(callSuper = true)
@Document(collection = "product")
public class Product extends BaseEntity<String> {

  private final String name;
  private final int price;
  private @Nullable final String description;
  private @Nullable final Media productPic;

  @Builder
  public Product(
      String id, String name, int price, @Nullable String description, @Nullable Media productPic) {
    super(id);
    this.name = name;
    this.price = price;
    this.description = description;
    this.productPic = productPic;
  }
}
