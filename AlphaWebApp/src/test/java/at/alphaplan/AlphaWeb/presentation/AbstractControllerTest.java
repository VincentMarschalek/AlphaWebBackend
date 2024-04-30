package at.alphaplan.AlphaWeb.presentation;

import at.alphaplan.AlphaWeb.persistance.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
//Controller Integration Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //für beforeAll()
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest
{
    @LocalServerPort
    private int port;

    @BeforeAll
    public void serverSetup()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }
}
