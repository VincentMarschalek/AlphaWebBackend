package at.alphaplan.AlphaWeb.presentation;

import at.alphaplan.AlphaWeb.domain.product.Product;
import at.alphaplan.AlphaWeb.presentation.commands.Commands.ProductCommand;
import at.alphaplan.AlphaWeb.service.ProductService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable String id) {
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody ProductCommand command
      // @RequestParam MultipartFile[] mediaFiles,
      // @RequestParam MediaMetaCommand[] mediaMetas
      ) {
    // Product createdProduct = productService.createProduct(command, mediaFiles, mediaMetas);
    Product createdProduct = productService.createProduct(command);
    URI uri = URI.create("/api/products/" + createdProduct.getId());
    return ResponseEntity.created(uri).body(createdProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
