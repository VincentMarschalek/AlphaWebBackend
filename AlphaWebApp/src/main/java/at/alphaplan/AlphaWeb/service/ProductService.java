package at.alphaplan.AlphaWeb.service;

import at.alphaplan.AlphaWeb.domain.product.Product;
import at.alphaplan.AlphaWeb.persistance.ProductRepository;
import at.alphaplan.AlphaWeb.presentation.commands.Commands;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(String id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found!"));
  }

  public Product createProduct(
      Commands.ProductCommand
          command /*MultipartFile[] mediaFiles, Commands.MediaMetaCommand[] mediaMetas*/) {
    // List<Media> mediaList = mediaService.saveMedias(mediaFiles, mediaMetas);

    Product product =
        Product.builder()
            .id(null)
            .name(command.name())
            .price(command.price())
            .description(command.description())
            // .productPic(mediaList)
            .build();

    return productRepository.save(product);
  }

  public void deleteProduct(String id) {
    Product product = getProductById(id);
    // Media productPics = product.getProductPic();

    // if (productPics != null && !productPics.isEmpty()) {
    //  mediaService.deleteMedias(productPics);
    // }

    productRepository.deleteById(id);
  }
}
