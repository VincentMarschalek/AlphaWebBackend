package at.alphaplan.AlphaWeb.domain.user;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.hasMaxText;

import lombok.Getter;

@Getter
public class Profile {

  private String firstName;
  private String lastName;

  //  private Address address;

  public Profile(String firstname, String lastName) {
    this.firstName = hasMaxText(firstname, 255, "firstName");
    this.lastName = hasMaxText(lastName, 255, "lastName");
    //    this.address = isNotNull(address, "Adresse");
  }
}
