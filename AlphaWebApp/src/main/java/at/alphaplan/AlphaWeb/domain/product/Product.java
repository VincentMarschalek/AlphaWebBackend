package at.alphaplan.AlphaWeb.domain.product;

import at.alphaplan.AlphaWeb.domain.media.Media;
import org.springframework.data.annotation.Id;

public class Product {

    @Id private Id productId;

    private int price;

    private String description;

    private Media productPic;

}
