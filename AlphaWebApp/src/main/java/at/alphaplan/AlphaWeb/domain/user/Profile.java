package at.alphaplan.AlphaWeb.domain.user;

import lombok.Getter;
import org.springframework.security.core.parameters.P;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.hasMaxText;
import static at.alphaplan.AlphaWeb.foundation.AssertUtil.isNotNull;


@Getter
public class Profile {

    private String firstname;
    private String lastname;
    private Address address;

    public Profile(String firstname, String lastname, Address address)
    {
        this.firstname= hasMaxText(firstname, 255,"firstName");
        this.lastname= hasMaxText(lastname,255,"lastName");
        this.address= isNotNull(address,"Adresse");
    }
}
