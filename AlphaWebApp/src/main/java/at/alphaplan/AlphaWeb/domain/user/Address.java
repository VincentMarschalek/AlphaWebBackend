 package at.alphaplan.AlphaWeb.domain.user;

 import lombok.AllArgsConstructor;
 import lombok.Data;

 @Data
 @AllArgsConstructor
 public class Address {

  private String street;
  private String number;
  private String city;
  private int postalCode;
  private String region;
  private String country;

 }
