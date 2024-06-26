package at.alphaplan.AlphaWeb;

import at.alphaplan.AlphaWeb.domain.media.Media;
import at.alphaplan.AlphaWeb.domain.product.Product;
import at.alphaplan.AlphaWeb.persistance.ProductRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AlphaWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlphaWebApplication.class, args);
  }

  @Bean
  public CommandLineRunner run(ProductRepository repository) {
    return args -> {
      var p1 =
          new Product(
              "6a7ed2d7-71e2-4e02-8b48-52c0bb9f67e8",
              "Standard-Paket",
              200,
              "Standard Landing-Page, Impressum, Kontaktformular.",
              new Media("product-1.jpg"));

      var p2 =
          new Product(
              "6a7ed2d7-71e2-4e02-8b48-52c0bb9f67e9",
              "Advanced-Paket",
              400,
              "Standard Paket + Account-Funktion & Shopseite.",
              new Media("product-2.jpg"));

      var p3 =
          new Product(
              "6a7ed2d7-71e2-4e02-8b48-52c0bb9f67ea",
              "Enterprise-Paket",
              800,
              "Advanced Paket + individuell angepasste Funktionen nach Absprache.",
              new Media("product-3.jpg"));

      repository.deleteAll();
      repository.saveAll(List.of(p1, p2, p3));
    };
  }
}
