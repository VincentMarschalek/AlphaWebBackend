package at.alphaplan.AlphaWeb.service;

import at.alphaplan.AlphaWeb.domain.media.Media;
import at.alphaplan.AlphaWeb.domain.product.Product;
import at.alphaplan.AlphaWeb.persistance.ProductRepository;
import at.alphaplan.AlphaWeb.presentation.commands.Commands.MediaMetaCommand;
import at.alphaplan.AlphaWeb.presentation.commands.Commands.ProductCommand;
import at.alphaplan.AlphaWeb.service.media.MediaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final MediaService mediaService;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(String id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found!"));
  }

  public Product createProduct(
      ProductCommand command, MultipartFile[] mediaFiles, MediaMetaCommand[] mediaMetas) {
    List<Media> mediaList = mediaService.saveMedias(mediaFiles, mediaMetas);

    Product product =
        Product.builder()
            .id(null)
            .name(command.name())
            .price(command.price())
            .description(command.description())
            .productPic(mediaList)
            .build();
    return productRepository.save(product);
  }

  public void deleteProduct(String id) {
    Product product = getProductById(id);
    List<Media> productPics = product.getProductPic();

    if (productPics != null && !productPics.isEmpty()) {
      mediaService.deleteMedias(productPics);
    }

    productRepository.deleteById(id);
  }
}
